//
// C++ Interface: pivotfile
//
// Description:
//
//
// Author: Piergiorgio Betti <pbetti@lpconsul.net>, (C) 2006
//
// Copyright: See COPYING file that comes with this distribution
//
//
#ifndef PIVOTFILE_H
#define PIVOTFILE_H

#include <stdio.h>
#include <Qt/qstring.h>
#include <Qt/qfile.h>
#include <Qt/qdatastream.h>
#include <adams_limits.h>
#include <queryparams.h>
#include <nodo.h>
#include <btreenetworkrt.h>
#include <configuretypedefs.h>

class QStringList;

#define PF_FORWARD		0
#define PF_REVERSE		1

typedef struct {
// 	char name[ SHORT_DESC_LEN ];
	QString name;
	int size;
	int in_buffer_offset;
}
PFIKey;

typedef struct {
	PFIKey pfi_key[ MAX_PIVOT_KEYS ];
	unsigned long cnt_int[ MAX_INT_COUNTERS ];
	double cnt_dbl[ MAX_DBL_COUNTERS ];
	void * usr_data;
}
PFRecord;

typedef struct {
	int size;
	PFIKey pfi_key[ MAX_PIVOT_KEYS ];
}
PFBtreeKeyFormat;

/**
Gestione del file di bufferizzazione dei risultati di analysis nel formato Pivot.

	@author Piergiorgio Betti <pbetti@lpconsul.net>
*/
class PivotFile
{
public:

	enum ScanDirection {
	        forward,
	        reverse
	};

	PivotFile ( const QString & datadir, const QString & prfx );
	~PivotFile();

	void writeNode ( const QStringVector & nodkeys, Nodo * noddata );
	long count();
	bool openForWrite();
	bool openForRead();
	void close();
	bool loadBtree();
	void rewind();
	bool seekRowNum ( unsigned long rownum );
	bool getRow ( PFRecord & pfr );
	void setSortOnDataCol ( int c, int pidx );

	inline QString getDataDir() {
		return data_dir;
	}
	inline QString getFilePrefix() {
		return file_prefix;
	}

	inline void setInfoLenght ( long arg ) {
		pf_info.lenght = arg;
	}
	inline long getInfoLenght() {
		return pf_info.lenght;
	}
	inline void setInfoInts ( int arg ) {
		pf_info.numInts = arg;
	}
	inline int getInfoInts() {
		return pf_info.numInts;
	}
	inline void setInfoDbls ( int arg ) {
		pf_info.numDbls = arg;
	}
	inline int getInfoDbls() {
		return pf_info.numDbls;
	}
	inline void setInfoRelation ( int arg ) {
		pf_info.relation = arg;
	}
	inline int getInfoRelation() {
		return pf_info.relation;
	}
	inline void setInfoRelationSize ( int arg ) {
		pf_info.relationSize = arg;
	}
	inline int getInfoRelationSize() {
		return pf_info.relationSize;
	}
	inline void setInfoSwitching ( int arg ) {
		pf_info.switching = arg;
	}
	inline int getInfoSwitching() {
		return pf_info.switching;
	}
	inline void setInfoSchema ( int arg ) {
		pf_info.schema = arg;
	}
	inline int getInfoSchema() {
		return pf_info.schema;
	}
	inline void setInfoAnalysis ( int arg ) {
		pf_info.analysis = arg;
	}
	inline int getInfoAnalysis() {
		return pf_info.analysis;
	}
	inline void setInfoIsPercent ( bool arg ) {
		pf_info.isPercent = arg;
	}
	inline bool getInfoIsPercent() {
		return pf_info.isPercent;
	}
	inline void setInfoWithPreSort ( bool arg ) {
		pf_info.withPreSort = arg;
	}
	inline bool getInfoWithPreSort() {
		return pf_info.withPreSort;
	}
	inline void setInfoReversePreSort ( bool arg ) {
		pf_info.reversePSort = arg;
	}
	inline bool getInfoReversePreSort() {
		return pf_info.reversePSort;
	}
	inline void setUserData ( bool arg ) {
		pf_info.userData = arg;
	}
	inline bool getUserData() {
		return pf_info.userData;
	}
	inline void setUserDataSize ( int arg ) {
		pf_info.userDataSize = arg;
	}
	inline int getUserDataSize() {
		return pf_info.userDataSize;
	}
	inline void setInfoSortDataCol ( int arg ) {
		pf_info.sortdataindex = arg;
	}
	inline int getInfoSortDataCol() {
		return pf_info.sortdataindex;
	}
	inline void setInfoSortPercCol ( int arg ) {
		pf_info.sortpercindex = arg;
	}
	inline int getInfoSortPercCol() {
		return pf_info.sortpercindex;
	}
	inline void setReserved ( bool arg ) {
		pf_info.reserved = arg;
	}
	inline bool getReserved() {
		return pf_info.reserved;
	}
	inline void setInfoNumKeys ( int arg ) {
		pf_info.numKeys = arg;
	}
	inline int getInfoNumKeys() {
		return pf_info.numKeys;
	}
	inline void setInfoKey ( unsigned int knum, const QString & kname, int ksize = 0, int koffs = -1 ) {
		if ( knum > MAX_DIMENSION ) return ;
		pf_info.key[ knum ].name = kname;
		pf_info.key[ knum ].size = ksize;
		pf_info.key[ knum ].in_buffer_offset = koffs;
	}
	inline const PFIKey * getInfoKey() {
		return pf_info.key;
	}
	inline void setInfoName ( const QString & arg ) {
		qstrncpy ( pf_info.name, qPrintable ( arg ), MAX_CONFIG_FILENAME );
	}
	inline const char * getInfoName() {
		return pf_info.name;
	}
	inline void setInfoDate ( const QString & arg ) {
		qstrncpy ( pf_info.date, qPrintable ( arg ), ELAB_DATE_STR_LEN );
	}
	inline const char * getInfoDate() {
		return pf_info.date;
	}
	inline void setInfoRestrictions ( const QList<QueryParams::Rest> & rl ) {
		*pf_info.restrictions = rl;
	}
	inline const QList<QueryParams::Rest> & getInfoRestrictions() {
		return * pf_info.restrictions;
	}

	inline bool readInfo() {
		return readInfo ( &pf_info );
	}
	inline bool writeInfo() {
		return writeInfo ( pf_info );
	}
	inline void setDirection ( ScanDirection d ) {
		scan_direction = d;
	}
	inline ScanDirection getDirection() {
		return scan_direction;
	}
	inline void setUserKey ( bool * k ) {
		for ( int i = 0; i < MAX_PIVOT_KEYS; i++ ) user_enabled_keys[ i ] = k[ i ];
		setupBtreeKeyFormat ( pf_info.relationSize );
	}
	inline void setAllUserKey() {
		for ( int i = 0; i < MAX_PIVOT_KEYS; i++ ) user_enabled_keys[ i ] = true;
		setupBtreeKeyFormat ( pf_info.relationSize );
	}
	inline int numOfKeysInTree() {
		return btree_rel_size;
	}
	inline void setFocusFilter ( const char * str, int keyno ) {
		focus_str = str;
		focus_keyno = keyno;
	}
	inline void clearFocusFilter() {
		focus_str = "";
		focus_keyno = -1;
	}
	inline void setSortOnKey ( int k ) {
		sort_keyno = k;
	}
	inline void clearAllSorts() {
		sort_keyno = -1;
		sort_datano = -1;
	}
	inline bool hasSortOnKey() {
		return ( sort_keyno >= 0 );
	}
	inline bool hasSortOnDataCol() {
		return ( sort_datano >= 0 );
	}
	inline void setFfRelation ( int * arg ) {
		for ( int i = 0; i < MAX_DIMENSION; i++ ) pf_info.ffRelation[ i ] = arg[ i ];
	}
	inline int * getFfRelation() {
		return pf_info.ffRelation;
	}

private:
	class PivotFileInfo
	{
	public:
		long lenght;
		int numInts;
		int numDbls;
		char name[ MAX_CONFIG_FILENAME ];
		int relation;
		int relationSize;
		int switching;
		char date[ ELAB_DATE_STR_LEN ];
		int schema;
		int analysis;
		bool isPercent;
		bool withPreSort;
		bool reversePSort;
		bool userData;
		int userDataSize;
		int numKeys;
		int sortdataindex;
		int sortpercindex;
		bool reserved;
		PFIKey key[ MAX_PIVOT_KEYS ];
		QList<QueryParams::Rest> * restrictions;
		int ffRelation[ MAX_DIMENSION ];

		PivotFileInfo()
			: lenght ( 0 )
			, numInts ( 0 )
			, numDbls ( 0 )
			, relation ( 0 )
			, switching ( 0 )
			, schema ( 0 )
			, analysis ( 0 )
			, isPercent ( false )
			, withPreSort ( false )
			, reversePSort ( false )
			, userData ( false )
			, sortdataindex ( 0 )
			, sortpercindex ( 0 )
			, reserved ( false ) {
			name[ 0 ] = date[ 0 ] = '\0';
			restrictions = new QList<QueryParams::Rest>;
			for ( int i = 0; i < MAX_DIMENSION; i++ ) ffRelation[ i ] = 0;
		}

		~PivotFileInfo() {
			restrictions->clear();
		}
	};

	QFile dfile;
	QDataStream dstream;
	QString data_dir;
	QString file_prefix;
	PivotFileInfo pf_info;
	BTreeNetworkRT btree;    	// map of Btree
	BtreeMapIterator bt_it, bt_start, bt_end;
	ScanDirection scan_direction;
	PFBtreeKeyFormat btree_key_format;
	bool user_enabled_keys[ MAX_PIVOT_KEYS ];
	int btree_rel_size;
	QString focus_str;
	int focus_keyno;
	int sort_keyno;
	int sort_datano;
	AdamsCompleteConfig * ntm;
	int percType;
	int percIndex;

	bool readInfo ( PivotFileInfo * pfi );
	bool writeInfo ( const PivotFileInfo & pfi );
	void fromPivotKeyToUserKey ( char * ukey, const QStringVector pkeys, Nodo * nodo );
	void setupBtreeKeyFormat ( int rel_size );
	QString evalSortOnData ( Nodo * nodo );

	inline QString composeInfoFileNamePath() {
		return ( data_dir + "/" ) + file_prefix + "_INFO.dat";
	}
	inline QString composePrimaryFileName() {
		return ( data_dir + "/" ) + file_prefix + "_DATA.dat";
	}
};

#endif
