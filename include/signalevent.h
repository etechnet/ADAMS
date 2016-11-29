/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

// This class was orgininally developed for the ASSA library by Vladislav Grinchenko,
// who owns the copyright.
// Slightly modified to integrate into libadams_core


//------------------------------------------------------------------------------
//                            SignalEvent.h
//------------------------------------------------------------------------------
//  Copyright (C) 1997-2002,2005  Vladislav Grinchenko
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the GNU Library General Public
//  License as published by the Free Software Foundation; either
//  version 2 of the License, or (at your option) any later version.
//---------------------------------------------------------------------------

#ifndef SIGNALEVENT_H
#define SIGNALEVENT_H

#include <Qt/qstring.h>

/** SignalEvent class.

SignalEvent is a pure virtual class. It is the base class for signal handlers

*/

class SignalEvent
{
public:
	/// Constructor
	SignalEvent();

	/// Virtual destructor
	virtual ~SignalEvent () {
		/* no-op */
	}

	/** Signal handler callback.
	    @return 0 on success, -1 on error
	*/
	virtual int handle_signal ( int signum_ );

	virtual void resetState ( void );

	/** Set SignalEvent ID.
	*/
	void set_id ( const QString & id_ ) {
		m_id = id_;
	}

	/** Retrieve SignalEvent ID.
	 */
	QString get_id () const {
		return m_id;
	}

protected:
	QString m_id;			/// SignalEvent ID.
};

inline SignalEvent::SignalEvent() : m_id ( "SignalEvent" )
{
}

inline int SignalEvent::handle_signal ( int /* signum_ */ )
{
	return -1;
}

inline void SignalEvent::resetState ( void )
{
}

#endif // SIGNALEVENT_H
