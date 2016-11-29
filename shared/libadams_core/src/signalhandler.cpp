/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */

// This class was orgininally developed for the ASSA library by Vladislav Grinchenko,
// who owns the copyright.
// Slightly modified to integrate into libadams_core


//------------------------------------------------------------------------------
//  Copyright (C) 1997-2002,2005  Vladislav Grinchenko
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the GNU Library General Public
//  License as published by the Free Software Foundation; either
//  version 2 of the License, or (at your option) any later version.
//---------------------------------------------------------------------------

#include <signalhandler.h>
#include <applogger.h>


SignalEvent * SignalHandler::m_signal_handlers [NSIG];

int SignalHandler::in_range ( int signum_ )
{
	if ( signum_ >= 1 && signum_ < NSIG ) {
		return 0;
	}
	else {
		lout << "signum_ " << signum_ << " is out of range [1;" << NSIG << "]" << endl;
		return -1;
	}
}

SignalEvent * SignalHandler::handler ( int signum_, SignalEvent* newh_ )
{
	if ( in_range ( signum_ ) == -1 )
		return 0;

	SignalEvent* oh = m_signal_handlers[signum_];
	m_signal_handlers[signum_] = newh_;

	return oh;
}

SignalEvent * SignalHandler::handler ( int signum_ )
{
	if ( in_range ( signum_ ) == -1 )
		return 0;

	return m_signal_handlers[signum_];
}

int SignalHandler::install ( int signum_, SignalEvent *new_hand_, SigAction *new_disp_, SignalEvent **old_hand_, SigAction *old_disp_ )
{
	if ( in_range ( signum_ ) == -1 )
		return -1;

	/*--- replace old Event Handler ptr with new one in my internal
	  dispatch table, returning the old one.
	  ---*/
	SignalEvent * eh = handler ( signum_, new_hand_ );

	/*--- if I am given place to store, save old handler ---*/
	if ( old_hand_ != 0 )
		*old_hand_ = eh;

	/*--- retrieve old disposition ---*/
	if ( old_disp_ != 0 ) {
		old_disp_->retrieve_action ( signum_ );
		old_disp_->handler ( ( C_SIG_HANDLER ) SIG_DFL );
	}

	/*--- if new disposition is NULL, use null action instead ---*/
	SigAction null_sa;

	if ( new_disp_ == 0 )
		new_disp_ = &null_sa;

	/*--- install my dispatcher ---*/
	new_disp_->handler ( ( C_SIG_HANDLER ) SignalHandler::dispatch );

	return new_disp_->register_action ( signum_, old_disp_ );
}

int SignalHandler::remove ( int signum_, SignalEvent * /* eh_ */, SigAction *new_disp_,  SigAction *old_disp_ )
{
	if ( in_range ( signum_ ) == -1 )
		return -1;
	/*---
	  We need default disposition here if user forgot to give us one.
	  ---*/
	SigAction sa ( ( C_SIG_HANDLER ) SIG_DFL );

	if ( new_disp_ == 0 ) {
		new_disp_ = &sa;
	}

	m_signal_handlers[signum_] = 0;

	return new_disp_->register_action ( signum_, old_disp_ );
}

void SignalHandler::dispatch ( int signum_ )
{
	/*--- save errno ---*/
	int my_errno = errno;

	SignalEvent * eh = m_signal_handlers[signum_];

	if ( eh != 0 && eh->handle_signal ( signum_ ) == -1 ) {
		/*---
		  we are in trouble, fall back to defaults
		  ---*/
		SigAction defact ( ( C_SIG_HANDLER ) SIG_DFL );
		m_signal_handlers[signum_] = 0;
		defact.register_action ( signum_ );
	}
	/*--- restore errno ---*/
	errno = my_errno;
}


