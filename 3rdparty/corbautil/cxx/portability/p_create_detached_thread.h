//-----------------------------------------------------------------------
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
// File:	p_create_detached_thread.h
//
// Description:	Utility function to start a detached thread.
//----------------------------------------------------------------------


#ifndef P_CREATE_DETACHED_THREAD_H_
#define P_CREATE_DETACHED_THREAD_H_





#if defined(WIN32)
//----------------------------------------------------------------------
// Windows version
//----------------------------------------------------------------------

#include <process.h>

typedef void *(*util_func_ptr)(void *);

inline void
create_detached_thread(util_func_ptr f, void * arg)
{
	int	tid;
	tid = (int)_beginthread((void(*)(void *))f, 0, (void *)arg);
}
#else /* assume a POSIX system */
//----------------------------------------------------------------------
// POSIX version
//----------------------------------------------------------------------
#include <pthread.h>
#include <assert.h>

typedef void *(*util_func_ptr)(void *);

inline void
create_detached_thread(util_func_ptr f, void * arg)
{
	int			status;
	pthread_t		tid;
	pthread_attr_t		attr;

	pthread_attr_init(&attr);
	status = pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
	assert(status == 0);

	status = pthread_create(&tid, &attr, f, arg);
	assert(status == 0);
}
#endif





#endif /* P_CREATE_DETACHED_THREAD_H_ */
