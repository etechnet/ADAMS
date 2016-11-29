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

//-----------------------------------------------------------------------
// File:	win_gsp_rw.h
//
// Policy:	RW[ReadOp, WriteOp]	// readers-writer lock
//
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
//	The above copyright notice and this permission notice shall be
//	included in all copies or substantial portions of the Software.  
// 
//	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
//	OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//	NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
//	HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
//	WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
//	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//	OTHER DEALINGS IN THE SOFTWARE.
//----------------------------------------------------------------------

#ifndef WIN_GSP_RW_H_
#define WIN_GSP_RW_H_





//--------
// #include's
//--------
#include <windows.h>
#include <process.h>
#include <assert.h>





//--------
// Forward declarations.
//--------
class GSP_RW;





class GSP_RW {
public:
	inline GSP_RW();
	inline ~GSP_RW();

	class ReadOp {
	public:
		inline ReadOp(GSP_RW &);
		inline ~ReadOp();

	protected:
		GSP_RW      &m_sync;
	};

	class WriteOp {
	public:
		inline WriteOp(GSP_RW &);
		inline ~WriteOp();

	protected:
		GSP_RW      &m_sync;
	};

protected:
	friend  class ::GSP_RW::ReadOp;
	friend  class ::GSP_RW::WriteOp;
	HANDLE	m_mutex;	// mutex
	HANDLE	m_sem;		// semaphore
	int	m_reader_count;
};





//--------
// Inline implementation of class GSP_RW
//--------

inline GSP_RW::GSP_RW()
{
	SECURITY_ATTRIBUTES	attr;

	attr.nLength              = sizeof(SECURITY_ATTRIBUTES);
	attr.lpSecurityDescriptor = NULL;
	attr.bInheritHandle       = TRUE;

	m_mutex = CreateMutex(&attr, FALSE, 0);
	assert(m_mutex != 0);

	m_sem = CreateSemaphore(&attr, 1, 1, 0);
	assert(m_sem != 0);

	m_reader_count = 0;
}



inline GSP_RW::~GSP_RW()
{
	int	status;

	status = CloseHandle(m_mutex);
	assert(status == TRUE);

	status = CloseHandle(m_sem);
	assert(status == TRUE);
}





//--------
// Inline implementation of class GSP_RW::ReadOp
//--------

inline GSP_RW::ReadOp::ReadOp(GSP_RW &sync_data)
        : m_sync(sync_data)
{
	int	status;

	status = WaitForSingleObject(m_sync.m_mutex, INFINITE);
	assert(status == WAIT_OBJECT_0);

	m_sync.m_reader_count ++;

	if (m_sync.m_reader_count == 1) {
		status = WaitForSingleObject(m_sync.m_sem, INFINITE);
		assert(status == WAIT_OBJECT_0);
	}

	status = ReleaseMutex(m_sync.m_mutex);
	assert(status == TRUE);
}



inline GSP_RW::ReadOp::~ReadOp()
{
	int	status;
	LONG	sem_prev_val;

	status = WaitForSingleObject(m_sync.m_mutex, INFINITE);
	assert(status == WAIT_OBJECT_0);

	m_sync.m_reader_count --;

	if (m_sync.m_reader_count == 0) {
		status = ReleaseSemaphore(m_sync.m_sem, 1, &sem_prev_val);
		assert(status == TRUE);
		assert(sem_prev_val == 0);
	}

	status = ReleaseMutex(m_sync.m_mutex);
	assert(status == TRUE);
}





//--------
// Inline implementation of class GSP_RW::WriteOp
//--------

inline GSP_RW::WriteOp::WriteOp(GSP_RW &sync_data)
        : m_sync(sync_data)
{
	int	status;

	status = WaitForSingleObject(m_sync.m_sem, INFINITE);
	assert(status == WAIT_OBJECT_0);
}



inline GSP_RW::WriteOp::~WriteOp()
{
	int	status;
	LONG	sem_prev_val;

	status = ReleaseSemaphore(m_sync.m_sem, 1, &sem_prev_val);
	assert(status == TRUE);
	assert(sem_prev_val == 0);
}





#endif
