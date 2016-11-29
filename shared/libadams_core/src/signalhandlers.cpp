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

#include <signalhandlers.h>
#include <applogger.h>

SigHandlersList * SigHandlersList::m_instance[NSIG];

void SignalHandlers::signalhandlers_dispatcher ( int signum_ )
{
	lout3 << "==> Received signal # " << signum_ << endl;
	dispatch ( signum_ );
}

int SignalHandlers::install ( int signum_, SignalEvent * new_hand_, SigAction * new_disp_, SignalEvent ** old_hand_, SigAction * old_disp_ )
{
	/*
	  Retrieve current signal disposition. If 3rd party handler has
	  already been istalled, make CFUNC_Handler out of it, and put it in
	  the list with id=0.

	  Add new_hand_ to the list. Has global signalhandlers_dispatcher not
	  been installed yet, install it too.
	*/

	if ( !in_range ( signum_ ) == -1 ) {
		return -1;
	}

	CFUNC_Handler * cfhp = 0;
	SigHandlersList * handlist = 0;

	handlist = SigHandlersList::instance ( signum_ );

	/*--- Retrieve current signal disposition ---*/

	SigAction cd;
	cd.retrieve_action ( signum_ );

	/*
	  Check whether 3rd party software has already installed
	  signal handler.
	*/
	if ( cd.handler() != ( C_SIG_HANDLER ) signalhandlers_dispatcher &&
	                cd.handler() != SIG_IGN &&
	                cd.handler() != SIG_DFL ) {
		/*
		  Looks like some other code got ahead of me and installed C-function
		  signal handler. Make a note of it.

		  Create SignalEvent to hold 3rd party handler. This handler will be
		  deleted only by SignalHandlers::remove (0), when application demanded
		  to remove all of the handlers.
		*/
		cfhp = new CFUNC_Handler ( cd.handler () );
		handlist->cfunc_handler ( cfhp );

		/*
		  Insert 3rd party handler in list of handlers
		  for this signal.
		*/
		if ( handlist->insert ( cfhp ) == false ) {
			lout << "Failed to insert c_func_handler for signum " << signum_ << endl;
			delete ( cfhp );
			handlist->cfunc_handler ( 0 );
			return -1;
		}
	}
	/*--- Add new_hand_ to the list of handlers for signum_. ---*/

	if ( handlist->insert ( new_hand_ ) == false ) {
		/*---
		  I failed to install new handler and might have already
		  added 3rd party CFUNC_Handler to the list without altering
		  disposition - if that's true, clean up the list.
		  ---*/
		lout << "failed to add new_hand_ to handlers list" << endl;

		if ( handlist->seen_cfunc_handler () &&
		                handlist->size() == 1 ) {
			handlist->erase ();
			handlist->cfunc_handler ( 0 );
		}
		return -1;
	}

	/*--- Has signalhandlers_dispatcher been already installed? ---*/

	if ( cd.handler() == ( C_SIG_HANDLER ) signalhandlers_dispatcher ) {
		return 0;
	}
// 	lout3 << "Installing 'signalhandlers_dispatcher'" << endl;

	/*
	  Installing new disposition; if user forgot to give me one
	  then default will be used.
	*/
	SigAction sa ( ( C_SIG_HANDLER ) SIG_DFL );

	if ( new_disp_ == 0 ) {
		new_disp_ = &sa;
	}

	new_disp_->handler ( ( C_SIG_HANDLER ) signalhandlers_dispatcher );

	if ( new_disp_->register_action ( signum_, old_disp_ ) == -1 ) {
		/*---
		  I failed to install signalhandlers_dispatcher. Up to this
		  point, if application had conventional C handler installed,
		  it still remains active. Handlers list built so far is
		  meaningless - get rid of it. ---*/

		lout << "register_action() error for " << signum_ << endl;

		if ( handlist->seen_cfunc_handler () ) {
			handlist->erase ();
			handlist->cfunc_handler ( 0 );
			delete cfhp;
		}
		handlist->erase ( new_hand_ );
		return -1;
	}
	return 0;
}

int SignalHandlers::remove ( int signum_, SignalEvent* eh_, SigAction * new_disp_, SigAction * old_disp_ )
{
	if ( in_range ( signum_ ) ) {
		lout << "sigum_ " << signum_ << " is out of range" << endl;
		return -1;
	}

	CFUNC_Handler * Cfhp = 0;	// pointer to C-function event handler
	SignalEvent * ehp = 0;		// pointer to current event handler

	SigHandlersList & handlist = * ( SigHandlersList::instance ( signum_ ) );

	if ( eh_ == 0 ) {
		/*--- Erase an entire list. ---*/
		handlist.erase ();
	}
	else {
		/*
			  Note: I cannot do erasure in the same loop for following  reason:

			  According to Stroustrup (Section 17.4.1.7):
			  "After erase(), the iterator cannot be used again because
			  the element to which it pointed is no longer there."

			  According to STL Tutorial and Ref. Guide:
			  "The erase function invalidates all iterators to all
			  positions past the point of erasure."

			  That's why here we first take care of id recycling and heap memory
			  deallocation, and only then clean() the map all at once.
			*/
		SigHandlersList::iterator it;

		if ( ( it = handlist.find ( eh_ ) ) != handlist.end () ) {
			ehp = ( *it );
			handlist.erase ( it );
		}
	}
	/*--- If set is not empty, we're done ---*/
	if ( handlist.size () ) return 0;

	/* If map was emptied out, install new disposition
	   with the 3rd party "C" function handler, if we had it.
	*/
	SigAction null_sa;
	if ( new_disp_ == 0 ) new_disp_ = &null_sa;

	lout3 << "Handlers List is empty" << endl;

	if ( handlist.seen_cfunc_handler () ) {
		/*--- Put 3rd party handler into disposition  ---*/
		Cfhp = handlist.cfunc_handler ( 0 );
		new_disp_->handler ( Cfhp->handler () );
		delete Cfhp;
	}
	/*--- Install new disposition ---*/
	return new_disp_->register_action ( signum_, old_disp_ );
}

void SignalHandlers::dispatch ( int signum_ )
{
	/*---
	  For every element in the set that holds all SignalEvents for
	  given signum, call its respective handle_signal() member function.
	  ---*/

	/*--- save errno ---*/
	int errno_saved = errno;

	SigHandlersList& handlist = * ( SigHandlersList::instance ( signum_ ) );
	SigHandlersList::iterator it;
	SignalEvent* ehp;

	for ( it = handlist.begin(); it != handlist.end(); it++ ) {
		ehp = *it;
		if ( ehp->handle_signal ( signum_ ) == -1 ) {
			/*---
			  this event handler reported error when handling
			  signum - remove it from the set
			  ---*/
			handlist.erase ( it );
		}
	}
	/*--- restore errno ---*/
	errno = errno_saved;
}

