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

/***************************************************************************
                          timeout.h  -  description
                             -------------------
    begin                : Mon Sep 3 2001
    copyright            : (C) 2001 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
   20120522 P. Betti              Add check for a pending (external) thread
 ***************************************************************************/

#ifndef TIMEOUT_H
#define TIMEOUT_H

#include <Qt/qthread.h>
#include <p_orb.h>

class TimeOut : public QThread
{
public:
	TimeOut();
	~TimeOut();
	/**
	*Metodo run del thread.
	*/
	virtual void run();

	inline void setProcess ( int tmp ) {
		iProcessKilled = tmp;
	};

	inline void setiActive ( bool tmp ) {
		iActive = tmp;
	};

	inline void setTimeOut ( int tmp ) {
		iTimeOut = tmp;
	};

	inline void setORB ( CORBA::ORB_var tmp ) {
		orb = tmp;
	};

	inline void setThreadCheck ( QThread * p ) {
		a_thread = p;
	};

	inline void resetThreadCheck () {
		a_thread = 0;
	};

	inline void setExitNow() {
		exitNow = true;
	}

	inline void setExitEnforced() {
		exitEnforced = true;
	}

private:
	int	iProcessKilled;
	bool	iActive;
	int 	iTimeOut;
	bool	exitNow;
	bool	exitEnforced;
	QThread * a_thread;
	CORBA::ORB_var orb;
};

#endif



