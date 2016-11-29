#ifdef __370__
#pragma nomargins
#endif

//-----------------------------------------------------------------------
// Copyright IONA Technologies 2002-2003. All rights reserved.
// This software is provided "as is".
//
// File:	gsp_timed_prodcons.h
//
// Policy:	ProdCons[PutOp, GetOp, OtherOp]
//
// Description:	The producer-consumer synchronisation policy.
//----------------------------------------------------------------------


#ifndef GSP_TIMED_PRODCONS_H_
#define GSP_TIMED_PRODCONS_H_





#if defined(P_USE_WIN32_THREADS)
#	error "timed prodcons not currently defined for windows threads"
#elif defined(P_USE_POSIX_THREADS)
#	include "posix_gsp_timed_prodcons.h"
#elif defined(P_USE_DCE_THREADS)
#	error "timed prodcons not currently defined for dce threads"
#elif defined(P_USE_SOLARIS_THREADS)
#	error "timed prodcons not currently defined for solaris threads"
#elif defined(P_USE_TPF_THREADS)
#	error "timed prodcons not currently defined for tpf threads"
#elif defined(P_USE_NO_THREADS)
#	error "timed prodcons not currently defined for dummy"
#else
#	error "You must #define a P_USE_<platform>_THREADS symbol"
#endif





#endif
