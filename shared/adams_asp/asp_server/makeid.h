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

#ifndef MAKEID_H
#define MAKEID_H

/**
 * MAX_ID definisce il valore massimo che un id può assumere.
 */
#define MAX_ID		9999

/**
  * File header della classe makeID
  */
class makeID
{
public:
	makeID();
	~makeID();
	bool ordinaIndici();
	int generaIndice();
	void stampaIndici();
	void addIndice ( int );
	void delIndice ( int );
	inline void delAll() {
		len = 0;
		delete ( indici );
		delete ( contIndici );
	};
	int isPresentID ( int );
	inline int numIndici() {
		return len;
	};
	int getIndex ( int );
	int getOccor ( int );
	void reset();
private:
	int *indici;			// array di indici.
	int *contIndici;		// array delle occorrenze degli indici.
	int len;			// numero di indici.
};

#endif


