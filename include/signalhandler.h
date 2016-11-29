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

#ifndef SIGNALHANDLER_H
#define SIGNALHANDLER_H

// System includes

#include <signal.h>
#include <errno.h>

#include <signalevent.h>

/**
 *
 * SigSet is a wrapper for UNIX sigset_t structure that provides
 * all manipulators on this structure as well.
 *
 * The conversoin operator that converts SigSet to a pointer to the
 * internal sigset_t data member for direct use with C-library
 * functions can be used as follows:
 *
 *   SigSet  source;
 *   sigset_t*  targetp;
 *
 *   targetp = source;
 *
 * Because lvalue is expected to be of the type sigset_t*, and there is
 * a conversion operator defined for SigSet, the conversion happens
 * automatically.
 *
 * Another example would be to use it in conjunction with 'struct sigaction':
 *
 *   struct sigaction action;
 *   SigSet siganls_to_block;
 *
 *   // manipulat signal set in some meaningful way
 *
 *   action.sa_mask = *signals_to_block;
 */

class SigSet
{
public:
	/** Default constructor creates SigSet object with
	 *	    an empty signal set.
	 */
	SigSet();

	/** Copy constructor from <tt>source_</tt>.
	 */
	SigSet ( sigset_t* source_ );

	/** Destructor
	 */
	~SigSet();

	/** This function initializes a signal set to be empty,
	 *	    no signals in it.
	 *
	 *	    @return 0 on success, -1 on error, with errno set to
	 *	    error number.
	 */
	int empty ( void );

	/** This function initializes a signal set to be full;
	 *	    all the signals defined by POSIX will be in the set.
	 *
	 *	    @return 0 on success, -1 on error, with errno set to
	 *	    error number.
	 */
	int fill ( void );

	/** This function adds the signal numbered <tt>signo_</tt>
	 *	    to the set.
	 *
	 *	    @return 0 on success, -1 on error, with errno set to
	 *	    error number.
	 */
	int add ( int signo_ );

	/** This function removes the signal <tt>signo_</tt> from
	 *	    the set.
	 *	    @return 0 on success, -1 on error, with errno set to
	 *	    error number.
	 */
	int del ( int signo_ );

	/** Use this function to tell whether the signal <tt>signo_</tt>
	 *	    is in the set.
	 *	    @return 0 on success, -1 on error, with errno set to
	 *	    error number.
	 */
	int is_member ( int signo_ );

	/** Conversion operator to <tt>sigset_t</tt> structure.
	 *	    @return pointer to the internal <tt>sigset_t</tt> structure.
	 */
	operator sigset_t *();

private:
	/** POSIX signal set */
	sigset_t m_sigset;
};

inline SigSet::SigSet()
{
	( int ) sigemptyset ( &m_sigset );
}

inline SigSet::SigSet ( sigset_t* s_ )
{
	m_sigset = *s_;
}

inline SigSet::~SigSet()
{
	/* no-op */
}

inline int SigSet::empty ( void )
{
	return sigemptyset ( &m_sigset );
}

inline int SigSet::fill ( void )
{
	return sigfillset ( &m_sigset );
}

inline int SigSet::add ( int signo_ )
{
	return sigaddset ( &m_sigset, signo_ );
}

inline int SigSet::del ( int signo_ )
{
	return sigdelset ( &m_sigset, signo_ );
}

inline int SigSet::is_member ( int signo_ )
{
	return sigismember ( &m_sigset, signo_ );
}

inline SigSet::operator sigset_t *()
{
	return &m_sigset;
}

extern "C" {
	typedef struct sigaction SIGACTION;
	typedef void ( *C_SIG_HANDLER ) ( int );
}

/**

SigAction is a C++ wrapper around sigaction structure

Class SigAction implements a C++ wrapper around
struct sigaction. It class also provides a range of all possible
operation that can be performed on it, including sigaction(2) system call.

struct sigaction is defined as:

  struct sigaction {
      void      (*sa_handler) ();
      sigset_t  sa_mask;
      int       sa_flags;
      void      (*sa_sigaction) (int, siginfo_t*, void*);
  };

It is used to set all the details of what your process should do when
a signal arrives. It encapsulates the action to be taken on receipt
of a particular signal.

The most important member is sa_handler, which takes a pointer
to a function. This function will be invoked whenever the process gets
a particular POSIX.1 signal.

Some of the member function of SigAction class take a parameter
of type C_SIG_HANDLER.

It can be one of:
- "C" signal handling function (sa_handler above):
void sig_handler (int signum)
- SIG_ERR
- SIG DFL takes default signal action when caught
- SIG_IGN ignores signal (for those that can be ignored)

The sa_mask mask for the signal action specifies a set of signals
to be blocked while the signal handler is active.
On the entry into the signal handler,
that set of signals is added to a set of signals already being
blocked from delivery when the signal is delivered to the process.
In addition, the signal that caused the handler to be executed will also
be blocked (SIGSTOP and SIGKILL cannot be blocked - this is enforced
by the underlying OS).

Flags sa_flags in the set of flags ORed that allows to modify
the delivery of the signal.
POSIX.1 spec. defines only SA_NOCLDSTOP flag. All other
flags are system-dependent. Consult your local sigaction(2)
man page for details.

Because SigAction is a wrapper around sigaction(2),
after sig_handler returns (and before anything else), the OS
will reinstall current disposition for the signal. To reset signal's
disposition to SIG_DFL, SUN Solaris OS uses flag SA_RESETHAND, and Linux OS
uses SA_ONESHOT.
*/

class SigAction
{
public:
	/** Default constructor creates SigAction object
	 *	    with null-action.
	 */
	SigAction();

	/** Construct a SigAction object with "C" signal
	 *	    handler function. This constructor doesn't install any
	 *	    actions - it is merely a shell for actiono to be installed
	 *	    for any signal(s). Thus, you can reuse the same object for
	 *	    number of differen signals.
	 *
	 *	    @param handler_ "C" signal handler function to call.
	 *	    @param sig_mask_ Set of signals to block while handler_
	 *	    is active.
	 *	    @param flags_ Controls behavior of signal handler (OS-specific:
	 *	    see Available Options: section of documentation).
	 */
	SigAction ( C_SIG_HANDLER handler_,
	            SigSet*       sig_mask_ = 0,
	            int           flags_ = 0 );

	/** Construct a SigAction with "C" signal handler function
	 *	    and change program's disposition for signum_
	 *	    immediately.<p>
	 *	    First argument is the "C" function. It cannot be a non-static
	 *	    C++ class member function. This function pretty much simulates
	 *	    C-like approach the the signal handling. For C++ member
	 *	    function approach, see SigHandler & Co.
	 *
	 *	    @param handler_ "C" signal handler function to call.
	 *	    @param signum_  Signal which disposition is to change.
	 *	    @param sig_mask_ Set of signals to block while handler_
	 *	    is active.
	 *	    @param flags_ Controls behavior of signal handler (OS-specific:
	 *	    see Available Options: section of documentation).
	 */
	SigAction ( C_SIG_HANDLER handler_,
	            int           signum_,
	            SigSet*       sig_mask_ = 0,
	            int           flags_ = 0 );

	/** Register this object as current disposition for signal
	 *	    signum_, and store old disposition in oaction_,
	 *	    if not NULL. This function installs C_SIG_HANDLER this
	 *	    object represents, thus simulating C-like approach to signal
	 *	    handling.
	 *
	 *	    @param signum_ Signal which disposition to install.
	 *	    @param oaction_ Placeholder for the old disposition.
	 *	    @return 0 on success, -1 on error, with errno indicating
	 *	    the error.
	 */
	int register_action ( int signum_, SigAction* oaction_ = 0 );

	/** Change object's disposition to oaction_, and install
	 *	    it as current disposition for the signal signum_.
	 *
	 *	    @param signum_ Signal which disposition to restore.
	 *	    @param oaction_ Disposition to restore.
	 *	    @return 0 on success, -1 on error, with errno indicating
	 *	    the error.
	 */
	int restore_action ( int signum_, SigAction& oaction_ );

	/** Retrieve current disposition for the signal signum_
	 *	    into this object.
	 *	    @param signum_ Signal number
	 *	    @return 0 on success, -1 on error, with errno indicating
	 *	    the error.
	 */
	int retrieve_action ( int signum_ );

	/** Set sigaction structure to sa_
	 *	    @param sa_ New value for internal struct sigaction.
	 */
	void action ( SIGACTION * sa_ );

	/** Retrieve current sigaction.
	 *	    @return Pointer to an internal struct sigaction.
	 */
	SIGACTION * action ();

	/** Set signal flags to new_flags_.
	 *	    @param new_flags_ New flags for this action.
	 */
	void flags ( int new_flags_ );

	/** Retrieve current flags.
	 *	    @return Value of current flags for this action.
	 */
	int flags ();

	/** Set new signal mask mask_set_.
	 */
	void mask ( SigSet & mask_set_ );

	/** Retrieve current signal mask.
	 */
	SigSet mask ();

	/** Set new signal handler to function pointer sha_.
	 */
	void handler ( C_SIG_HANDLER sha_ );

	/** Retrieve current signal handler function.
	 */
	C_SIG_HANDLER handler ();

	/** Conversion operator that converts SigAction to a
	 *	    pointer to the internal struct sigaction data
	 *	    member for direct use with C-library functions.
	 */
	operator SIGACTION *();

private:
	/// sigaction structure itself
	SIGACTION m_sa;
};

inline SigAction::SigAction ()
{
	m_sa.sa_flags = 0;
	sigemptyset ( &m_sa.sa_mask );
	* ( C_SIG_HANDLER* ) &m_sa.sa_handler = ( C_SIG_HANDLER ) 0;
}

inline SigAction::SigAction ( C_SIG_HANDLER handler_, SigSet * sig_mask_, int flags_ )
{
	m_sa.sa_flags = flags_;
	if ( sig_mask_ == NULL ) {
		sigemptyset ( &m_sa.sa_mask );
	}
	else {
		m_sa.sa_mask = **sig_mask_;
	}
	* ( C_SIG_HANDLER* ) &m_sa.sa_handler = ( C_SIG_HANDLER ) handler_;
}

inline SigAction::SigAction ( C_SIG_HANDLER handler_, int signum_, SigSet * sig_mask_, int flags_ )
{
	m_sa.sa_flags = flags_;
	if ( sig_mask_ == NULL ) {
		sigemptyset ( &m_sa.sa_mask );
	}
	else {
		m_sa.sa_mask = **sig_mask_;
	}
	* ( C_SIG_HANDLER* ) &m_sa.sa_handler = ( C_SIG_HANDLER ) handler_;

	/*--- installing disposition... ---*/
	sigaction ( signum_, &m_sa, 0 );
}

inline void SigAction::action ( SIGACTION* sa_ )
{
	m_sa = *sa_;
}

inline SIGACTION * SigAction::action ()
{
	return &m_sa;
}

inline void SigAction::flags ( int new_flags_ )
{
	m_sa.sa_flags = new_flags_;
}

inline int SigAction::flags ()
{
	return m_sa.sa_flags;
}

inline void SigAction::mask ( SigSet & mask_set_ )
{
	m_sa.sa_mask = *mask_set_;
}

inline SigSet SigAction::mask ()
{
	SigSet tmpset ( &m_sa.sa_mask );
	return tmpset;
}

inline void SigAction::handler ( C_SIG_HANDLER sha_ )
{
	* ( C_SIG_HANDLER* ) &m_sa.sa_handler = ( C_SIG_HANDLER ) sha_;
}

inline C_SIG_HANDLER SigAction::handler ()
{
	return ( C_SIG_HANDLER ) m_sa.sa_handler;
}

inline SigAction::operator SIGACTION * ()
{
	return &m_sa;
}

inline int SigAction::register_action ( int signum_, SigAction* oaction_ )
{
	struct sigaction *osa = oaction_ == 0 ? 0 : oaction_->action();
	return sigaction ( signum_, &m_sa, osa );
}


inline int SigAction::restore_action ( int signum_, SigAction& oaction_ )
{
	m_sa = *oaction_.action();
	return sigaction ( signum_, &m_sa, 0 );
}

inline int SigAction::retrieve_action ( int signum_ )
{
	return sigaction ( signum_, 0, &m_sa );
}


class SignalHandler
{
public:
	/** No-op virtual destructor
	 */
	virtual ~SignalHandler () {
		/* no-op */
	}

	/** Add new signal handler and new disposition for the signal.
	    Note that although new_disp_ might keep C-like
	    handler for the action, new_hand_ will really be
	    handling delivered signal. In other words,
	    new_disp_.sa_handler is ignored.

	    @param signum_ signal number new disposition is installed for.
	    @param new_hand_ pointer to new SignalEvent that will be
	    handling the signal
	    @param new_disp_ new disposition to use in handling the signal
	    @param old_hand_ return old handler for the signal
	    @param old_disp_ return old disposition for the signal

	    @return 0 on success, -1 on error
	*/
	virtual int install ( int            signum_,
	                      SignalEvent*  new_hand_,
	                      SigAction*     new_disp_ = 0,
	                      SignalEvent** old_hand_ = 0,
	                      SigAction*     old_disp_ = 0 );

	/** Remove SignalEvent associated with signum_. Also,
	    install new disposition and return an old one (if given).

	    @param signum_ signal number new disposition is installed for.
	    @param eh_ pointer to SignalEvent that is uninstalled.
	    If eh_ is 0 (by default), all event handlers associated with
	    signum_ will be removed.
	    @param new_disp_ new disposition to use in handling the signal
	    @param old_disp_ return old disposition for the signal

	    @return 0 on success, -1 on error
	*/
	virtual int remove ( int signum_,
			     SignalEvent * eh_,
	                     SigAction*    new_disp_ = 0,
	                     SigAction*    old_disp_ = 0 );

	/** Here is the heart of SignalHandler class. This callback function
	    is really registered with OS to catch all of the signals
	    SignalEvents have been installed for. dispatch ()
	    catches the signal and then calls sends the signal to
	    the appropriate SignalEvent object.

	    @param signum_ signal delivered by OS.
	    @return 0 on success, -1 on error
	*/
	static void dispatch ( int signum_ );

	/** Set new event handler for signal signum_ and return
	    an existing one.

	    @return Pointer to the old event handler, or 0 if signum_
	    is out of range.
	*/
	SignalEvent* handler ( int signum_, SignalEvent* new_ );

	/** Retrieve current event handler for signum_.
	 */
	SignalEvent* handler ( int signum_ );

protected:
	/** Check that signum_ is in valid range.
	    @return 0 if in range; -1 otherwise.
	*/
	int in_range ( int signum_ );

private:
	/** Static array that stores one user-defined event handler pointer
	    for every signal.
	*/
	static SignalEvent* m_signal_handlers [NSIG];
};


#endif /* SIGNALHANDLER_H */
