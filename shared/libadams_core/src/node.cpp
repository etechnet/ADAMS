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
                          nodo.cpp  -  description
                             -------------------
    begin                : Wed Sep 27 2000
    copyright            : (C) 2000 by Salvatore Sannino
    email                : salvatore.sannino@j-linux.it
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "nodo.h"
#include "applogger.h"

#include <stdlib.h>
#include <qthread.h>



static int num_ints = 0;
static int num_dbls = 0;
static int preAlloc_Size = 0;
static int preAlloc_Elements = 0;

// static QMutex mtx_fa, mtx_sc;

static bool userData = false;
static int usrDataSize = 0;

Nodo::Nodo()
: m_reentrant(false)
{
	ListaParametri.int_counters = 0;
	ListaParametri.dbl_counters = 0;
	ListaParametri.usr_data = 0;
	unallocated = true;
	m_num_ints = num_ints;
	m_num_dbls = num_dbls;
	m_preAlloc_Size = preAlloc_Size;
	m_userData = userData;
	m_usrDataSize = usrDataSize;
}

Nodo::Nodo(ParameterList LParametri)
: m_reentrant(false)
{
	unallocated = true;
	m_num_ints = num_ints;
	m_num_dbls = num_dbls;
	m_preAlloc_Size = preAlloc_Size;
	m_userData = userData;
	m_usrDataSize = usrDataSize;
	setupCounters();
	if (userData) {
		memcpy(ListaParametri.usr_data, LParametri.usr_data, usrDataSize);
	}
	else {
		for (int i = 0; i < num_ints; i++)
			ListaParametri.int_counters[i] = LParametri.int_counters[i];
		for (int i = 0; i < num_dbls; i++)
			ListaParametri.dbl_counters[i] = LParametri.dbl_counters[i];
	}
}

Nodo::Nodo(int n_int, int n_dbl)
: m_reentrant(true)
{
	unallocated = true;
	m_num_ints = n_int;
	m_num_dbls = n_dbl;
	m_userData = false;
	m_usrDataSize = 0;
	ListaParametri.int_counters = 0;
	ListaParametri.dbl_counters = 0;
	ListaParametri.usr_data = 0;
	m_preAlloc_Size = (n_int * sizeof(unsigned long)) + (n_dbl * sizeof(double));
}

Nodo::Nodo(int size)
: m_reentrant(true)
{
	if (size > 0) {
		m_userData = true;
		m_usrDataSize = size;
	}
}

Nodo::~Nodo()
{
	if (ListaParametri.int_counters)
		delete [] ListaParametri.int_counters;
	if (ListaParametri.dbl_counters)
		delete [] ListaParametri.dbl_counters;
}

void Nodo::setReentrant()
{
	m_reentrant = true;
	if (ListaParametri.int_counters)
		delete [] ListaParametri.int_counters;
	if (ListaParametri.dbl_counters)
		delete [] ListaParametri.dbl_counters;
	ListaParametri.int_counters = 0;
	ListaParametri.dbl_counters = 0;
	ListaParametri.usr_data = 0;
	m_userData = false;
	m_usrDataSize = 0;
	unallocated = true;
}

void Nodo::setCountersSize_r ( int n_int, int n_dbl )
{
	if (!m_reentrant) {
		lout << "Reentrant counters size setup on NON-reentrant nodo !!" << endl;
		setReentrant();
	}
	m_num_ints = n_int;
	m_num_dbls = n_dbl;
	m_userData = false;
	m_usrDataSize = 0;
	m_preAlloc_Size = (n_int * sizeof(unsigned long)) + (n_dbl * sizeof(double));

}


Nodo& Nodo::operator=(const Nodo& NodoN)
{
	if (NodoN.isUnallocated())
		return *this;

	if (unallocated)
		setupCounters();

	if (m_userData) {
		if (this->ListaParametri.usr_data) {
			memcpy(ListaParametri.usr_data, NodoN.ConstGetLista()->usr_data, m_usrDataSize);
		}
		else
			lout << "Nodo(" << (unsigned long)this << ") operating on unallocated user data space" << endl;
	}
	else {
		int i;
		const ParameterList * fval = NodoN.ConstGetLista();
		if (this->ListaParametri.int_counters) {
			for (i = 0; i < m_num_ints; i++)
				this->ListaParametri.int_counters[i] = fval->int_counters[i];
		}
		else
			lout << "Nodo(" << (unsigned long)this << ") operating on unallocated integer counters" << endl;
		if (this->ListaParametri.dbl_counters) {
			for (i = 0; i < m_num_dbls; i++)
				this->ListaParametri.dbl_counters[i] = fval->dbl_counters[i];
		}
		else
			lout << "Nodo(" << (unsigned long)this << ") operating on unallocated double counters" << endl;
	}

	return *this;
}

Nodo& Nodo::operator=(Nodo& NodoN)
{
	return operator=((const Nodo &)NodoN);
}

Nodo& Nodo::operator+=(Nodo& NodoN)
{
	int i;
	ParameterList * fval = NodoN.GetLista();
	if (this->ListaParametri.int_counters) {
		for (i = 0; i < m_num_ints; i++)
			this->ListaParametri.int_counters[i] += fval->int_counters[i];
	}
	else
		lout << "Nodo(" << (unsigned long)this << ") operating on unallocated integer counters" << endl;
	if (this->ListaParametri.dbl_counters) {
		for (i = 0; i < m_num_dbls; i++)
			this->ListaParametri.dbl_counters[i] += fval->dbl_counters[i];
	}
	else
		lout << "Nodo(" << (unsigned long)this << ") operating on unallocated double counters" << endl;

	return *this;
}

void Nodo::reset()
{
	int i;
	if (this->ListaParametri.int_counters) {
		for (i = 0; i < m_num_ints; i++)
			this->ListaParametri.int_counters[i] = 0;
	}
	else
		lout << "Nodo(" << (unsigned long)this << ") operating on unallocated integer counters" << endl;
	if (this->ListaParametri.dbl_counters) {
		for (i = 0; i < m_num_dbls; i++)
			this->ListaParametri.dbl_counters[i] = 0.0;
	}
	else
		lout << "Nodo(" << (unsigned long)this << ") operating on unallocated double counters" << endl;
}

void Nodo::setupCounters_fast(NodoFastMem * NFM, int nel)
{
// 	mtx_sc.lock();
	if (nel)
		preAlloc_Elements = nel;
	if (NFM->preAllocCount == 0) {
		NFM->mem_cursor = new char [m_preAlloc_Size * preAlloc_Elements];
		if (NFM->mem_cursor == 0) {
			lout << "Nodo::setupCounters : FATAL : no memory available... ABORTING." << endl;
			::exit(1);
		}
		memset(NFM->mem_cursor, 0, m_preAlloc_Size * preAlloc_Elements);
		NFM->preAllocCount = preAlloc_Elements;
// lout << "PASSO 3: preallocazione contatori di ciascun nodo: " << preAlloc_Size * preAlloc_Elements << endl;
	}
	ListaParametri.int_counters = (unsigned long *) NFM->mem_cursor; NFM->mem_cursor += (m_num_ints * sizeof(unsigned long));
	ListaParametri.dbl_counters = (double *) NFM->mem_cursor; NFM->mem_cursor += (m_num_dbls * sizeof(double));
	--NFM->preAllocCount;
// 	mtx_sc.unlock();
}

void Nodo::setupCounters()
{
	if (m_userData) {
		ListaParametri.usr_data = new unsigned char [m_usrDataSize];
		unallocated = false;
		return;
	}
	ListaParametri.int_counters = new unsigned long [m_num_ints];
	ListaParametri.dbl_counters = new double [m_num_dbls];
	memset(ListaParametri.int_counters, 0, sizeof(unsigned long)*m_num_ints);
	memset(ListaParametri.dbl_counters, 0, sizeof(double)*m_num_dbls);
	if (ListaParametri.int_counters && ListaParametri.dbl_counters)
		unallocated = false;
}

void Nodo::setCountersSize(int n_int, int n_dbl)
{
	num_ints = n_int;
	num_dbls = n_dbl;
	preAlloc_Size = (n_int * sizeof(unsigned long)) + (n_dbl * sizeof(double));
}

void Nodo::getCountersSize(int & n_int, int & n_dbl)
{
	n_int = num_ints;
	n_dbl = num_dbls;
}

void Nodo::getCountersSize_r(int & n_int, int & n_dbl)
{
	n_int = m_num_ints;
	n_dbl = m_num_dbls;
}

void Nodo::setNodePreAllocBuffer(NodoFastMem * NFM, int bufsiz)
{
// 	mtx_fa.lock();
	if (NFM->nodePreAllocDone)
		return;
	NFM->nodePreAllocCount = NFM->nodePreAlloc_Size = bufsiz;
	NFM->nodePreAllocDone = true;
// 	mtx_fa.unlock();
}

void Nodo::setPreAllocElements(int nel)
{
	if (preAlloc_Elements > 0)
		return;
	preAlloc_Elements = nel;
}

Nodo * Nodo::fastAlloc(NodoFastMem * NFM)
{
	Nodo * p;

	if (NFM->nodePreAlloc_Size == 0)
		return new Nodo;

// 	mtx_fa.lock();
	if (NFM->nodePreAllocCount >= NFM->nodePreAlloc_Size) {
		NFM->node_mem_cursor = new Nodo [NFM->nodePreAlloc_Size];
		if (NFM->node_mem_cursor == 0) {
			lout << "Nodo::fastAlloc : FATAL : no memory available... ABORTING." << endl;
			::exit(1);
		}
		NFM->nodePreAllocCount = 0;
/*lout << "PASSO 2: preallocazione nodi contatori n. " << NFM->nodePreAlloc_Size << endl;
sleep(10);*/
	}
	p = &NFM->node_mem_cursor[NFM->nodePreAllocCount++];
// 	mtx_fa.unlock();

	return p;

}

void Nodo::setupUserDataSize(int size)
{
	if (size > 0) {
		userData = true;
		usrDataSize = size;
	}
}

bool Nodo::haveUserData()
{
	return userData;
}

int Nodo::userDataSize()
{
	return usrDataSize;
}

bool Nodo::haveUserData_r()
{
	return m_userData;
}

int Nodo::userDataSize_r()
{
	return m_usrDataSize;
}



