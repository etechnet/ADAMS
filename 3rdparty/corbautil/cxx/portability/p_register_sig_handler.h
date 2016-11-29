//----------------------------------------------------------------------
// Copyright 2008 Ciaran McHale.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// 	The above copyright notice and this permission notice shall be
// 	included in all copies or substantial portions of the Software.
//
// 	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// 	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// 	OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// 	NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// 	HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// 	WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// 	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// 	OTHER DEALINGS IN THE SOFTWARE.
//
// File:	p_register_sig_handler.h
//
// Description:	A portability wrapper for registering signal handlers
//		that will be called when a signal (such as Control-C)
//		that normally kills an application occurs.
//
// Example of usage:
//
//	#include "p_register_sig_handler.h"
//	...
//	void sig_handler()
//	{
//		... code to handle the signal
//	}
//	...
//	int main(int argc, char ** argv)
//	{
//		p_register_signal_handler(sig_handler);
//		...
//	}
//
//
// Note:	Ensure that one of the following macros is defined
//		before including this file:
//
//			P_USE_ORBIX	(for Orbix)
//			WIN32		(for Windows)
//			/* nothing */	(for UNIX)
//----------------------------------------------------------------------

#ifndef P_REG_SIG_HANDLER_H_
#define P_REG_SIG_HANDLER_H_


typedef void (*p_sig_handler_func_type)();
static p_sig_handler_func_type p_sig_handler;





#if defined(P_USE_ORBIX)
//--------
// Orbix-proprietary (but cross-platform portable) approach
//--------
#include <omg/orb.hh>
#include <it_ts/termination_handler.h>

IT_TerminationHandler * p_it_term_handler;

static void p_orbix_sig_handler(long)
{
	(*p_sig_handler)();
}

static void p_register_signal_handler(p_sig_handler_func_type f)
{
	p_sig_handler = f;
	p_it_term_handler = new IT_TerminationHandler(p_orbix_sig_handler);
}





#elif defined(WIN32)
//--------
// Windows-specific control-C handling
//--------
#include <wincon.h>

static BOOL __stdcall p_win_sig_handler(DWORD arg)
{
	(*p_sig_handler)();
	return TRUE;
}

static void p_register_signal_handler(p_sig_handler_func_type f)
{
	BOOL		rc;

	p_sig_handler = f;
	rc = SetConsoleCtrlHandler(p_win_sig_handler, TRUE);
	if (!rc) { abort(); }
}





#else
//--------
// UNIX-specific signal handling
//--------
#include <signal.h>
#include <stdlib.h>

extern "C" void p_unix_sig_handler(int)
{
	struct sigaction		ignore;

	//--------
	// Ignore further signals
	//--------
	ignore.sa_handler = SIG_IGN;
	sigemptyset(&ignore.sa_mask);
	ignore.sa_flags = 0;
	if (sigaction(SIGINT, &ignore, 0) == -1) { abort(); }
	if (sigaction(SIGTERM, &ignore, 0) == -1) { abort(); }
	if (sigaction(SIGHUP, &ignore, 0) == -1) { abort(); }

	(*p_sig_handler)();
}

static void p_register_signal_handler(p_sig_handler_func_type f)
{
	struct sigaction		ignore;

	ignore.sa_handler = p_unix_sig_handler;
	sigfillset(&ignore.sa_mask);
	ignore.sa_flags = 0 |  SA_RESTART;
	if (sigaction(SIGINT, &ignore, 0) == -1) { abort(); }
	if (sigaction(SIGTERM, &ignore, 0) == -1) { abort(); }
	if (sigaction(SIGHUP, &ignore, 0) == -1) { abort(); }

	p_sig_handler = f;
}


#endif /* P_USE_ORBIX/WIN32/else */


#endif /* P_REG_SIG_HANDLER_H_ */
