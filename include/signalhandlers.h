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


#ifndef SIGNALHANDLERS_H
#define SIGNALHANDLERS_H

// System includes
//
#include <signal.h>
#include <errno.h>
#include <sys/time.h>
#include <sys/types.h>
#include <set>
#include <signalhandler.h>

// #include <signaleventhandlers.h>


using std::set;

/**
 *
 *    SigHandlersList is a Singleton class that maps signal number to a set of
 *	SignalEvents listening for the delivery of the signal.
 */

/** CFUNC_Handler  class.
 *
 *    CFUNC_Handler is a wrapper around C signal handler function.
 *	It wraps C signal handler function into SignalEvent interface.
 */

class CFUNC_Handler : public SignalEvent
{
public:
	CFUNC_Handler ( C_SIG_HANDLER csigh_ );

	int           handle_signal ( int signum_ );
	C_SIG_HANDLER handler () {
		return m_c_sig_hand;
	}

private:
	C_SIG_HANDLER m_c_sig_hand;
};

/** SigHandlersList class.
 *
 *
 *	SigHandlersList class is used by SigHandlers class to keep track of
 *	SignalEvents installed to be called on signal's delivery. It is sort of
 *	global process map of signal numbers into corresponding sets of
 *	SignalEvents that are listening for signal delivery.
 */

class SigHandlersList
{
public:
	typedef SignalEvent * key_type;
	typedef SignalEvent * data_type;

	struct CompSHL {
		bool operator () ( const key_type c1_, const key_type c2_ ) const {
			// This wouldn't fly on 64-bit machines, 'cause ptr size there is 8 bytes long
			//			return int(c1_) < int(c2_);
			//
			return ( c1_ < c2_ );
		}
	};

	typedef set< key_type, CompSHL > set_t;
	typedef set< key_type, CompSHL >::iterator iterator;

	/** Retrieve a pointer to the list of event handlers
	 *	    listening to signum_ signal delivery.
	 */
	static SigHandlersList * instance ( int signum_ );

	/// Destructor
	~SigHandlersList ();

	/// Is list empty
	bool empty () const;

	/// Size of the list
	size_t size () const;

	/** Add an event handler data_ to the list.
	 *	    @return TRUE on success, FALSE on error.
	 */
	bool insert ( data_type data_ );

	/** Find and remove event handler  key_  from the list.
	 */
	void erase ( const key_type key_ );

	/** Remove an event handler pointed by iterator  it_
	 *	    from the list.
	 */
	void erase ( iterator it_ );

	/** Empty event handlers' list.
	 */
	void erase ();

	/** Return an iterator pointing to the beginning of the list.
	 */
	iterator begin ();

	/** Return an iterator pointing to the end of the list.
	 */
	iterator end ();

	/** Find event handler by its pointer key_.
	 *	    @return Iterator to the element.
	 */
	iterator find ( const key_type key_ );

	/** Save 3rd party C function handler to remember.
	 *	    @param cfp_ New 3rd party C function handler. If it is NULL,
	 *	    then seen_cfh flag is set to FALSE.
	 *	    @return old 3rd party C function handler.
	 */
	CFUNC_Handler* cfunc_handler ( CFUNC_Handler* cfp_ );

	/** Retrieve pointer to 3rd party C function handler.
	 *	    @return 3rd party C function handler.
	 */
	CFUNC_Handler* cfunc_handler () const;

	/** Indicate whether 3rd party C function handler was installed.
	 *	    @param ft_ TRUE if was, FALSE if not.
	 */
	void seen_cfunc_handler ( bool ft_ );

	/** @return TRUE if we've seen 3rd party C function handler;
	 *	    FALSE otherwise.
	 */
	bool seen_cfunc_handler () const;

protected:
	SigHandlersList ();		// Singleton
	SigHandlersList ( const SigHandlersList& map_ ); // prohibit copying
	SigHandlersList& operator= ( const SigHandlersList& map_ );

public:
	/** Static map of signal numbers to SigHandlerLists.
	 */
	static SigHandlersList * m_instance[NSIG];

private:
	/// Set of all event handlers registered for this signal.
	set_t * m_set;

	/** If true this flag indicates that 3rd party event handler
	 *	    has already been installed prior taking control by SigHandlers
	 *	    manager.
	 */
	int m_seen_cfh;

	/** Pointer to the 3rd party signal handler in the set
	 */
	CFUNC_Handler * m_cfhp;
};

inline SigHandlersList::SigHandlersList ()
	: m_seen_cfh ( false ), m_cfhp ( NULL )
{
	m_set = new set_t;
}

inline SigHandlersList::~SigHandlersList ()
{
	erase ();
	delete m_set;
	m_set = 0;
}

inline SigHandlersList * SigHandlersList::instance ( int signum_ )
{
	if ( SigHandlersList::m_instance[signum_] == 0 ) {
		SigHandlersList::m_instance[signum_] = new SigHandlersList();
	}
	return SigHandlersList::m_instance[signum_];
}

inline bool SigHandlersList::empty () const
{
	// true if map is empty, false otherwise
	return m_set->empty ();
}

inline size_t SigHandlersList::size () const
{
	// return number of elements in the map
	return m_set->size ();
}

inline bool SigHandlersList::insert ( data_type eh_ )
{
	/*---
	 *	  Insert 'eh_' into the set. set::insert() returns a 'pair' object.
	 *
	 *	  If the set doesn't contain an element that matches 'eh_', insert a
	 *	  copy of 'eh_' and returns a 'pair' whose first element is an
	 *	  iterator positioned at the new element and second element is
	 *	  'true'.
	 *
	 *	  If the set already contains an element that matches 'eh_', returns
	 *	  a pair whose first element is an iterator positioned at the
	 *	  existing element and second element is false!
	 *	  ---*/

	set_t::const_iterator it = m_set->find ( eh_ );

	/*--- Not in the set ---*/
	if ( it == m_set->end () ) {
		return ( m_set->insert ( eh_ ) ).second;
	}
	/*--- Already in the set ---*/
	return true;
}

inline void SigHandlersList::erase ( const key_type key_ )
{
	m_set->erase ( key_ );
}

inline void SigHandlersList::erase ()
{
	// empty the map
	m_set->erase ( m_set->begin(), m_set->end() );
}

inline void SigHandlersList::erase ( iterator it_ )
{
	// erase element pointed by iterator
	m_set->erase ( it_ );
}

inline SigHandlersList::iterator SigHandlersList::begin ()
{
	return m_set->begin ();
}

inline SigHandlersList::iterator SigHandlersList::end ()
{
	return m_set->end ();
}

inline SigHandlersList::iterator SigHandlersList::find ( const key_type key_ )
{
	return m_set->find ( key_ );
}


inline CFUNC_Handler * SigHandlersList::cfunc_handler ( CFUNC_Handler* cfhp_ )
{
	CFUNC_Handler* old_cfhp = m_cfhp;
	m_cfhp = cfhp_;
	m_seen_cfh = cfhp_ == NULL ? false : true;
	return old_cfhp;
}

inline CFUNC_Handler * SigHandlersList::cfunc_handler () const
{
	return m_cfhp;
}

inline void SigHandlersList::seen_cfunc_handler ( bool ft_ )
{
	m_seen_cfh = ft_;
}

inline bool SigHandlersList::seen_cfunc_handler () const
{
	return m_seen_cfh;
}

inline CFUNC_Handler::CFUNC_Handler ( C_SIG_HANDLER csigh_ )
	: m_c_sig_hand ( csigh_ )
{
}

inline int CFUNC_Handler::handle_signal ( int signum_ )
{
	if ( m_c_sig_hand ) {
		( *m_c_sig_hand ) ( signum_ );
	}
	return 1;
}




/**

    SignalHandlers is a signal handlers manager.

	SignalHandlers class extends SigHandler class by allowing
	user to register multiple SignalEvents per signal. It also preserves
	signal handlers installed by the third-party software.

	Initially the state of SignalHandlers object is passive. No special
	dispatching is done. User can use other means (such as @see SigAction) to
	register C-function as signal handler.

	When register() method is called for the first SignalEvent,
	the state of SignalHandlers object changes to active, and SignalHandlers
	dispatcher takes over the control on how signals are dispatched. Each
	SignalEvent is added to the list of handlers. When signal is delivered,
	all handlers on that list will be notified.

	If third party library (or application code) has installed its own
	signal handler (C-function) prior to the first SignalEvent registration,
	it will be placed among all other registered SignalEvents and notified too
	when signal is delivered to the process by OS.
*/

class SignalHandlers : public SignalHandler
{
public:
	/** A wrapper around static SignalHandlers::dispatch(). It is needed for
	    the purpose of differentiation it with other signal handlers that
	    might be installed by user's code.
	    @param signum_ Dispatch event handlers for this signal.
	*/
	static void signalhandlers_dispatcher ( int signum_ );

	/** Register SignalEvent with dispatching system.

	    @param signum_   (In   ) Signal number.
	    @param new_hand_ (In   ) Pointer to new event handler to install.
	    @param new_disp_ (In=0 ) New disposition to use to handle signal.
	    @param old_hand_ (Out=0) Placeholder for old event handler.
	    @param old_disp_ (Out=0) Placeholder for old disposition.

	    @return 0 on success; -1 on error.
	 */
	virtual int install ( int            signum_,
	                      SignalEvent*  new_hand_,
	                      SigAction*     new_disp_ = 0,
	                      SignalEvent** old_hand_ = 0,
	                      SigAction*     old_disp_ = 0 );

	/** Remove SignalEvent from the list of registered handler
	    for signum_. If eh_ is NULL, then all
	    SignalEvent(s) will be removed from the list, and object
	    will go back to passive mode in which no signal
	    handling is done via SignalHandlers class dispatcher. If
	    new_disp_ is omitted, SIG_DFL will be used
	    instead.

	    @param signum_   (In   ) Signal number.
	    @param eh_       (In   ) Event handler to remove.
	    @param new_disp_ (In=0 ) New disposition to use to handle signal.
	    @param old_disp_ (Out=0) Placeholder for old disposition.

	    @return 0 on success; -1 on error.
	*/

	virtual int remove ( int           signum_,
	                     SignalEvent* eh_,
	                     SigAction*    new_disp_ = 0,
	                     SigAction*    old_disp_ = 0 );
private:
	/** The heart of SignalHandlers class - this callback function
	    is really registered with OS to catch all of the signals
	    for which event handler has been installed. Appropriate
	    SignalEvent(s) are then notified.
	*/
	static void dispatch ( int signum_ );
};


#endif /* SIGNALHANDLERS_H */
