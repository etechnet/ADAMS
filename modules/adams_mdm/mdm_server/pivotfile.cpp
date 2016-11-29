//
// C++ Implementation: pivotfile
//
// Description:
//
//
// Author: Piergiorgio Betti <pbetti@lpconsul.net>, (C) 2006
//
// Copyright: See COPYING file that comes with this distribution
//
//
#include "pivotfile.h"

#include <unistd.h>

#include <Qt/qstringlist.h>
#include <Qt/qfile.h>
#include <Qt/qdatastream.h>
#include <Qt/qmutex.h>
#include <Qt/qdir.h>

#include <nodo.h>
#include <applogger.h>


PivotFile::PivotFile ( const QString& datadir, const QString& prfx )
		: data_dir ( datadir )
		, file_prefix ( prfx )
		, scan_direction ( forward )
		, btree_rel_size ( 0 )
{
	for ( int i = 0; i < MAX_PIVOT_KEYS; i++ ) user_enabled_keys[i] = true;
	clearFocusFilter();
	clearAllSorts();
}

PivotFile::~PivotFile()
{
}

/*!
    \fn PivotFile::writeNode()
 */
void PivotFile::writeNode ( const QStringVector & nodkeys, Nodo * noddata )
{

	if ( pf_info.userData ) {
		// key value
		dstream << nodkeys[0];
		// raw user data
		dstream.writeRawData ( ( char * ) noddata->GetLista()->usr_data, pf_info.userDataSize );
	}
	else {
		// key value
		for ( int i = 0; i < pf_info.relationSize; i++ ) {
			dstream << nodkeys[i];
// 			lout3 << "w:" << i << " " << nodkeys[i] << endl;
		}

		// counters...
		int n_dbl, n_int;
		noddata->getCountersSize ( n_int, n_dbl );

		for ( int i = 0; i < n_int; i++ ) {
			dstream << ( quint64 ) noddata->GetLista()->int_counters[i];
		}
		for ( int i = 0; i < n_dbl; i++ ) {
			dstream << ( double ) noddata->GetLista()->dbl_counters[i];
		}
	}
}

bool PivotFile::openForWrite()
{
	dfile.setFileName ( composePrimaryFileName() );
	if ( !dfile.open ( QIODevice::WriteOnly ) )
		return true;

	dstream.setDevice ( &dfile );
	return false;
}

bool PivotFile::openForRead()
{
	dfile.setFileName ( composePrimaryFileName() );
	if ( !dfile.open ( QIODevice::ReadOnly ) )
		return true;

	dstream.setDevice ( &dfile );
	return false;
}

void PivotFile::close()
{
	if ( dstream.device() ) {
		dstream.device()->close();
	}
}


bool PivotFile::writeInfo ( const PivotFileInfo & pfi )
{
	QFile iod ( composeInfoFileNamePath() );
	iod.open ( QIODevice::WriteOnly );

	QDataStream info_ds ( &iod );
	int b;

	if ( pfi.userData )
		lout3 << "writing info for userdata of " << pfi.lenght << " records" << endl;

	info_ds << ( qint64 ) pfi.lenght;
	info_ds << ( qint32 ) pfi.numInts;
	info_ds << pfi.numDbls;
	info_ds << pfi.name;
	info_ds << pfi.relation;
	info_ds << pfi.relationSize;
	info_ds << pfi.switching;
	info_ds << pfi.date;
	info_ds << pfi.schema;
	info_ds << pfi.analysis;
	b = pfi.isPercent;
	info_ds << b;
	b = pfi.withPreSort;
	info_ds << b;
	b = pfi.reversePSort;
	info_ds << b;
	info_ds << pfi.numKeys;
	info_ds << pfi.sortdataindex;
	info_ds << pfi.sortpercindex;
	info_ds << pfi.userData;
	info_ds << pfi.userDataSize;
	b = pfi.reserved;
	info_ds << b;

	for ( int i = 0; i < MAX_PIVOT_KEYS; i++ ) {
		info_ds << pfi.key[i].name;
		info_ds << pfi.key[i].size;
		info_ds << pfi.key[i].in_buffer_offset;
	}

	QListIterator<QueryParams::Rest> it1 ( *pfi.restrictions );
	int res_size = pfi.restrictions->count();

	info_ds.writeRawData ( ( const char * ) &res_size, sizeof ( int ) );

	while ( it1.hasNext() ) {
		info_ds.writeRawData ( ( const char * ) &it1.next(), sizeof ( QueryParams::Rest ) );
	}

	for ( int i = 0; i < MAX_DIMENSION; i++ )
		info_ds << pfi.ffRelation[i];

	iod.close();

	return false;
}

bool PivotFile::readInfo ( PivotFileInfo * pfi )
{
	QFile iod ( composeInfoFileNamePath() );
	iod.open ( QIODevice::ReadOnly );

	delete pfi->restrictions;

	QDataStream info_ds ( &iod );
	char * str;
	info_ds >> ( qint64& ) pfi->lenght;
	info_ds >> ( qint32& ) pfi->numInts;
	info_ds >> pfi->numDbls;
	info_ds >> str;
	qstrncpy ( pfi->name, str, MAX_CONFIG_FILENAME );
	delete [] str;
	info_ds >> pfi->relation;
	info_ds >> pfi->relationSize;
	info_ds >> pfi->switching;
	info_ds >> str;
	qstrncpy ( pfi->date, str, ELAB_DATE_STR_LEN );
	delete [] str;
	info_ds >> pfi->schema;
	info_ds >> pfi->analysis;
	int b;
	info_ds >> b;
	pfi->isPercent = b;
	info_ds >> b;
	pfi->withPreSort = b;
	info_ds >> b;
	pfi->reversePSort = b;
	info_ds >> pfi->numKeys;
	info_ds >> pfi->sortdataindex;
	info_ds >> pfi->sortpercindex;
	info_ds >> pfi->userData;
	info_ds >> pfi->userDataSize;
	info_ds >> b;
	pfi->reserved = b;

	for ( int i = 0; i < MAX_PIVOT_KEYS; i++ ) {
		info_ds >> pfi->key[i].name;
		info_ds >> pfi->key[i].size;
		info_ds >> pfi->key[i].in_buffer_offset;
	}

	pfi->restrictions = new QList<QueryParams::Rest>;

	QueryParams::Rest * res;
	int res_size = 0;
	pfi->restrictions->clear();

	info_ds.readRawData ( ( char * ) &res_size, sizeof ( int ) );

	for ( int i = 0; i < res_size; i++ ) {
		res = new QueryParams::Rest;
		info_ds.readRawData ( ( char * ) res, sizeof ( QueryParams::Rest ) );
		pfi->restrictions->append ( *res );
	}

	for ( int i = 0; i < MAX_DIMENSION; i++ )
		info_ds >> pfi->ffRelation[i];

	iod.close();

	setupBtreeKeyFormat ( pfi->relationSize );

	if ( pfi->userData )
		lout3 << "have read info for userdata of " << pfi->lenght << " records" << endl;


	return false;
}

void PivotFile::rewind()
{
	bt_start = btree.begin();
	bt_end = btree.end();

	if ( scan_direction == reverse ) {
		bt_start = btree.end();
		bt_end = btree.begin();
		--bt_start;
		--bt_end;
	}

	bt_it = bt_start;
}

bool PivotFile::seekRowNum ( unsigned long rownum )
{
	while ( rownum > 0 ) {
		if ( bt_it == bt_end )		// EOB
			return true;

		if ( scan_direction == reverse )
			--bt_it;
		else
			++bt_it;

		--rownum;
	}

	return false;		// no errors
}

bool PivotFile::loadBtree()
{
	QStringVector nodkeys ( MAX_PIVOT_KEYS );
	QString keystring;
	char nodeKey[MAX_KEY_LENGTH];

	if ( pf_info.userData )
		Nodo::setupUserDataSize ( pf_info.userDataSize );
	else
		Nodo::setCountersSize ( pf_info.numInts, pf_info.numDbls );

	Nodo * nodo = new Nodo;
	nodo->setupCounters();

	for ( int rcnt = 0; rcnt < pf_info.lenght; rcnt++ ) {
		nodeKey[0] = '\0';
		// key value
		if ( pf_info.userData ) {
			dstream >> keystring;
		}
		else {
			for ( int i = 0; i < pf_info.relationSize; i++ ) {
				dstream >> keystring;
				nodkeys[i] = ( keystring.length() > 0 ) ? keystring : "";
				lout3 << "r:" << i << " " << nodkeys[i] << endl;
			}
		}

		if ( pf_info.userData ) {
			// user data
			dstream.readRawData ( ( char * ) nodo->GetLista()->usr_data, pf_info.userDataSize );
		}
		else {
			// counters...
			for ( int i = 0; i < pf_info.numInts; i++ ) {
				dstream >> ( quint64& ) nodo->GetLista()->int_counters[i];
			}
			for ( int i = 0; i < pf_info.numDbls; i++ ) {
				dstream >> nodo->GetLista()->dbl_counters[i];
			}
		}

		if ( !pf_info.userData ) {
			if ( focus_keyno >= 0 ) {
				if ( nodkeys[focus_keyno] != focus_str )
					continue;
			}

			// translate to user key
			fromPivotKeyToUserKey ( nodeKey, nodkeys, nodo );
			// insert node
			btree.insertOrSum ( nodeKey, *nodo );
		}
		else {
			btree.insert ( keystring, *nodo );
		}
	}

	dstream.device()->close();

// 	lout << "loadBtree: expect:" << pf_info.lenght << ", got:" << btree.count() << endl;

	return false;	// no errors
}

void PivotFile::fromPivotKeyToUserKey ( char * ukey, const QStringVector pkeys, Nodo * nodo )
{
	// sort on key/data schema: [SORT_KEY]|FIRST_KEY|[DATA_KEY]|SECOND_KEY|OTHER_KEYS...
	if ( hasSortOnKey() ) {
		qstrncpy ( ukey, qPrintable(pkeys[sort_keyno]), pf_info.key[sort_keyno].size + 1 );
	}
	for ( int i = 0; i < pf_info.relationSize; i++ ) {
		if ( user_enabled_keys[i] ) {
			strcat ( ukey, qPrintable(pkeys[i]) );
			if ( i == 0 && hasSortOnDataCol() ) {
				strcat ( ukey, qPrintable(evalSortOnData ( nodo )) );
			}
		}
	}

}

QString PivotFile::evalSortOnData ( Nodo * nodo )
{
	char SortKey[MAX_KEY_LENGTH];

	if ( hasSortOnDataCol() ) {		// ordinamento richiesto
		if ( sort_datano >= pf_info.numInts && sort_datano < ( pf_info.numInts + pf_info.numDbls ) ) {
			switch ( percType ) {
				case 0:	// double referrer
					sprintf ( SortKey, "%08.2f",
					          ( nodo->GetLista()->dbl_counters[sort_datano - pf_info.numInts] * 100.0 ) /
					          nodo->GetLista()->dbl_counters[percIndex - pf_info.numInts] );
					break;
				case 1: // int referrer
					sprintf ( SortKey, "%08.2f",
					          ( nodo->GetLista()->dbl_counters[sort_datano - pf_info.numInts] * 100.0 ) /
					          ( double ) nodo->GetLista()->int_counters[percIndex] );
					break;
				default:
					sprintf ( SortKey, "%08.2f",
					          nodo->GetLista()->dbl_counters[sort_datano - pf_info.numInts] );
			}
		}
		else if ( sort_datano >= 0 && sort_datano < pf_info.numInts ) {
			switch ( percType ) {
				case 0:	// double referrer
					sprintf ( SortKey, "%08.2f",
					          ( nodo->GetLista()->int_counters[sort_datano] * 100.0 ) /
					          nodo->GetLista()->dbl_counters[percIndex - pf_info.numInts] );
					break;
				case 1: // int referrer
					sprintf ( SortKey, "%08.2f",
					          ( nodo->GetLista()->int_counters[sort_datano] * 100.0 ) /
					          ( double ) nodo->GetLista()->int_counters[percIndex] );
					break;
				default:
					sprintf ( SortKey, "%08d", nodo->GetLista()->int_counters[sort_datano] );
			}
		}
	}

	return QString ( SortKey );
}


void PivotFile::setupBtreeKeyFormat ( int rel_size )
{
	int j = 0;
	int uoffs = 0;

	btree_key_format.size = 0;
	for ( int i = 0; i < MAX_PIVOT_KEYS; i++ ) {
		if ( i == rel_size ) break;
		if ( user_enabled_keys[i] ) {
			btree_key_format.size++;
			btree_key_format.pfi_key[j] = pf_info.key[i];
			btree_key_format.pfi_key[j].in_buffer_offset = uoffs;

			uoffs += btree_key_format.pfi_key[j].size;
			++j;
		}
	}
	btree_rel_size = j;
}


bool PivotFile::getRow ( PFRecord & pfr )
{
	if ( bt_it == bt_end ) {		// EOB
		lout << "EOB" << endl;
		return true;
	}

	QString rkey = bt_it.key();

	if ( pf_info.userData ) {
		pfr.pfi_key[0].size = MAX_KEY_LENGTH;
		pfr.pfi_key[0].in_buffer_offset = 0;
		pfr.pfi_key[0].name = rkey;
		pfr.usr_data = bt_it.value().GetLista()->usr_data;
	}
	else {
		int sort_shift = ( hasSortOnDataCol() ) ? SORT_SHIFT : 0;
		int key_sort_shift = ( hasSortOnKey() ) ? pf_info.key[sort_keyno].size : 0;

		for ( int i = 0; i < btree_rel_size; i++ ) {
			pfr.pfi_key[i].size = btree_key_format.pfi_key[i].size;
			pfr.pfi_key[i].in_buffer_offset = btree_key_format.pfi_key[i].in_buffer_offset;
			if ( i == 0 ) {
	// 			if (hasSortOnKey())
				pfr.pfi_key[i].name = rkey.mid ( pfr.pfi_key[i].in_buffer_offset + key_sort_shift,
								pfr.pfi_key[i].size );
				/*			else
								pfr.pfi_key[i].name = rkey.mid(pfr.pfi_key[i].in_buffer_offset, pfr.pfi_key[i].size);*/
			}
			else
				pfr.pfi_key[i].name = rkey.mid ( pfr.pfi_key[i].in_buffer_offset + sort_shift + key_sort_shift,
								pfr.pfi_key[i].size );
		}

		for ( int i = 0; i < pf_info.numInts; i++ )
			pfr.cnt_int[i] = bt_it.value().GetLista()->int_counters[i];
		for ( int i = 0; i < pf_info.numDbls; i++ )
			pfr.cnt_dbl[i] = bt_it.value().GetLista()->dbl_counters[i];
	}

	if ( scan_direction == reverse )
		--bt_it;
	else
		++bt_it;

	return false;	// no errors
}

long PivotFile::count()
{
	return btree.count();
}


void PivotFile::setSortOnDataCol ( int c, int pidx )
{
	sort_datano = c;

	if ( hasSortOnDataCol() && pf_info.isPercent ) {				// ordinamento richiesto
		if ( sort_datano >= pf_info.numInts && sort_datano < ( pf_info.numInts + pf_info.numDbls ) ) {
			percType = 0;		// double
		}
		else if ( sort_datano >= 0 && sort_datano < pf_info.numInts ) {
			percType = 1;		// integer
		}
	}

	percIndex = pidx;
}
