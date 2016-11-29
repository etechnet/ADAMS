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
// Copyright IONA Technologies 2002-2003. All rights reserved.
// This software is provided "as is".
//
// File:	posix_gsp_timed_prodcons.h
//
// Policy:	ProdCons[PutOp, GetOp, OtherOp]
//
// Description:	The producer-consumer synchronisation policy.
//
// Note:	The algorithm is taken from "Programming with Threads"
//		by Kleiman, Shah and Smaalders and adapted with a
//   		time condition wait
//----------------------------------------------------------------------


#ifndef POSIX_GSP_TIMED_PRODCONS_H_
#define POSIX_GSP_TIMED_PRODCONS_H_





//--------
// #include's
//--------
#include <pthread.h>
#include <assert.h>

// temporary includes - JC
#include <sys/time.h>
#include <string>
using std::string;


#include <strstream>

using namespace std;

//--------
// Forward declarations.
//--------
class GSP_Timed_ProdCons;

class GSP_Exception {
public:
	inline GSP_Exception();
	inline ~GSP_Exception();

	inline virtual string reason() const;
	inline virtual void reason(const string& s);

protected:
	string	  m_reason;
};



class GSP_Timeout: public GSP_Exception {
public:
	inline GSP_Timeout();
	inline ~GSP_Timeout();

};



class GSP_Timed_ProdCons {
public:
	inline GSP_Timed_ProdCons();
	inline ~GSP_Timed_ProdCons();

	class PutOp {
	public:
		inline PutOp(GSP_Timed_ProdCons &);
		inline ~PutOp();

	protected:
		GSP_Timed_ProdCons	&m_sync;
	};

	class GetOp {
	public:
		inline GetOp(GSP_Timed_ProdCons &, int timeout)  throw (GSP_Timeout);
		inline ~GetOp();

	protected:
		GSP_Timed_ProdCons	&m_sync;
	};

	class OtherOp {
	public:
		inline OtherOp(GSP_Timed_ProdCons &);
		inline ~OtherOp();

	protected:
		GSP_Timed_ProdCons	&m_sync;
	};

protected:
	friend class	::GSP_Timed_ProdCons::PutOp;
	friend class	::GSP_Timed_ProdCons::GetOp;
	friend class	::GSP_Timed_ProdCons::OtherOp;

	pthread_mutex_t	m_mutex;
	pthread_cond_t	m_notEmpty;
	long		m_count;
};


//--------
// Inline implementation of class GSP_Exception
//--------

inline GSP_Exception::GSP_Exception()
{
}

inline GSP_Exception::~GSP_Exception()
{
}

inline string GSP_Exception::reason() const
{
	return m_reason;
}

inline void GSP_Exception::reason(const string& s)
{
	m_reason = s;
}
//--------
// Inline implementation of class GSP_Timeout
//--------
inline GSP_Timeout::GSP_Timeout()
{
}

inline GSP_Timeout::~GSP_Timeout()
{
}



//--------
// Inline implementation of class GSP_Timed_ProdCons
//--------

inline GSP_Timed_ProdCons::GSP_Timed_ProdCons()
{
	int status;

	status = pthread_mutex_init(&m_mutex, (pthread_mutexattr_t *)0);
	assert(status == 0);

	m_count = 0;

	status = pthread_cond_init(&this->m_notEmpty, (pthread_condattr_t *)0);
	assert(status == 0);
}


inline GSP_Timed_ProdCons::~GSP_Timed_ProdCons()
{
	int status;

	status = pthread_mutex_destroy(&m_mutex);
	assert(status == 0);

	status = pthread_cond_destroy(&m_notEmpty);
	assert(status == 0);
}





//--------
// Inline implementation of class GSP_Timed_ProdCons::PutOp
//--------

inline GSP_Timed_ProdCons::PutOp::PutOp(GSP_Timed_ProdCons &sync_data)
	: m_sync(sync_data)
{
	int	status;

	status = pthread_mutex_lock(&m_sync.m_mutex);
	assert(status == 0);
}


inline GSP_Timed_ProdCons::PutOp::~PutOp()
{
	int	status;

	m_sync.m_count ++;

	status = pthread_mutex_unlock(&m_sync.m_mutex);
	assert(status == 0);

	status = pthread_cond_signal(&m_sync.m_notEmpty);
	assert(status == 0);
}





//--------
// Inline implementation of class GSP_Timed_ProdCons::GetOp
//--------

inline GSP_Timed_ProdCons::GetOp::GetOp(GSP_Timed_ProdCons &sync_data, int timeout) throw (GSP_Timeout)
	: m_sync(sync_data)
{
	int	status;

	status = pthread_mutex_lock(&m_sync.m_mutex);
	assert(status == 0);

	struct timespec   abstime;
	struct timeval    tp;

	// calculate time delta
	int rc = gettimeofday(&tp,NULL);
		assert(rc == 0);

	// Convert from timeval to timespec
	abstime.tv_sec  = tp.tv_sec;
	abstime.tv_nsec = tp.tv_usec * 1000;
	abstime.tv_sec += timeout;

	while (m_sync.m_count == 0) {
		status = pthread_cond_timedwait(&m_sync.m_notEmpty, &m_sync.m_mutex, &abstime);
		if (status == ETIMEDOUT) {
			//release mutex as dtor will now never be called

			status = pthread_mutex_unlock(&m_sync.m_mutex);
			assert(status == 0);
			ostrstream message;

			message << "timed out waiting for server after " << timeout << " seconds " << endl;

                        GSP_Timeout gsptime;
			gsptime.reason(message.str() );
			throw gsptime;
		}

		assert(status == 0);
	}
}


inline GSP_Timed_ProdCons::GetOp::~GetOp()
{
	int	status;

	m_sync.m_count --;

	status = pthread_mutex_unlock(&m_sync.m_mutex);
	assert(status == 0);
}





//--------
// Inline implementation of class GSP_Timed_ProdCons::OtherOp
//--------

inline GSP_Timed_ProdCons::OtherOp::OtherOp(GSP_Timed_ProdCons &sync_data)
	: m_sync(sync_data)
{
	int	status;

	status = pthread_mutex_lock(&m_sync.m_mutex);
	assert(status == 0);
}


inline GSP_Timed_ProdCons::OtherOp::~OtherOp()
{
	int	status;

	status = pthread_mutex_unlock(&m_sync.m_mutex);
	assert(status == 0);
}





#endif
