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

#include <drinterface.h>
#include <applogger.h>


static const DR_Field_Type DRFieldTypes[DRF_NumTypes] =	{
	{DRF_char, 		sizeof ( char ),		"Char"},
	{DRF_unsigned_char,	sizeof ( unsigned char ),	"Unsigned Char"},
	{DRF_short, 		sizeof ( short ),		"Short"},
	{DRF_unsigned_short,	sizeof ( unsigned short ),	"Unsigned Short"},
	{DRF_int, 		sizeof ( int ),		"Int"},
	{DRF_unsigned_int,	sizeof ( unsigned int ),	"Unsigned Int"},
	{DRF_long, 		sizeof ( long ),		"Long"},
	{DRF_unsigned_long,	sizeof ( unsigned long ),	"Unsigned Long"},
	{DRF_float, 		sizeof ( float ),		"Float"},
	{DRF_double,		sizeof ( double ),		"Double"},
	{DRF_BCD,		sizeof ( unsigned char ),	"Binary Coded Decimal"},
	{DRF_CString,		0,			"C-Type Char String"},
	{DRF_String,		0,			"Byte String"}
};

DRInterface::DRInterface() : QObject(), firsttime ( true ), fields ( 0 ), fimage ( 0 )
{
	fields = new QMultiHash<QString, DRField *>;
	fieldsnumber = 0;
}

DRInterface::~DRInterface()
{
	if ( fields ) {
		clear();
		delete fields;
	}
	if ( fimage )
		delete [] fimage;
}

// setup i/e tags

void DRInterface::ieInit()
{
}

// do the export of the dr config onto the ImportExport stream

void DRInterface::ieExport ( ImportExport & ie )
{
	ie.initWriteRecord ( GLOBAL_HEADERTAG );		// global info

	ie.setWriteTag ( "drUsePlugin" );
	ie.addWriteRecord ( glbOpt.data.drUsePlugin );
	ie.setWriteTag ( "drPluginName" );
	ie.addWriteRecord ( glbOpt.data.drPluginName );
	ie.setWriteTag ( "drUsePath" );
	ie.addWriteRecord ( glbOpt.data.drUsePath );
	ie.setWriteTag ( "drPathName" );
	ie.addWriteRecord ( glbOpt.data.drPathName );
	ie.setWriteTag ( "glbDefaultPluginPath" );
	ie.addWriteRecord ( glbOpt.data.glbDefaultPluginPath );
	ie.setWriteTag ( "glbAlias" );
	ie.addWriteRecord ( glbOpt.data.glbAlias );
	ie.setWriteTag ( "glbAuthor" );
	ie.addWriteRecord ( glbOpt.data.glbAuthor );
	ie.setWriteTag ( "glbLastModified" );
	ie.addWriteRecord ( qPrintable ( QDateTime::currentDateTime().toString() ) );

	ie.flushWriteRecord();

	if ( fields->count() > 0 ) {
		foreach ( DRField * dr, *fields ) {
			ie.initWriteRecord ( DR_HEADERTAG );

			ie.setWriteTag ( "position" );
			ie.addWriteRecord ( dr->data.position );
			ie.setWriteTag ( "fieldtype" );
			ie.addWriteRecord ( dr->data.fieldtype );
			ie.setWriteTag ( "offset" );
			ie.addWriteRecord ( dr->data.offset );
			ie.setWriteTag ( "size" );
			ie.addWriteRecord ( dr->data.size );
			ie.setWriteTag ( "type_size" );
			ie.addWriteRecord ( dr->data.type_size );
			ie.setWriteTag ( "is_array" );
			ie.addWriteRecord ( dr->data.is_array );
			ie.setWriteTag ( "array_size" );
			ie.addWriteRecord ( dr->data.array_size );
			ie.setWriteTag ( "description" );
			ie.addWriteRecord ( dr->data.description );
			ie.setWriteTag ( "isIndex" );
			ie.addWriteRecord ( dr->data.isIndex );
			ie.setWriteTag ( "indexByPlugin" );
			ie.addWriteRecord ( dr->data.indexByPlugin );
			ie.setWriteTag ( "indexBlockSize" );
			ie.addWriteRecord ( dr->data.indexBlockSize );
			ie.setWriteTag ( "indexRealTimeBlockSize" );
			ie.addWriteRecord ( dr->data.indexRealTimeBlockSize );
			ie.setWriteTag ( "startSize" );
			ie.addWriteRecord ( dr->data.startSize );
			ie.setWriteTag ( "indexSuffix" );
			ie.addWriteRecord ( dr->data.indexSuffix );
			ie.setWriteTag ( "startTime" );
			ie.addWriteRecord ( dr->data.startTime );
			ie.setWriteTag ( "endTime" );
			ie.addWriteRecord ( dr->data.endTime );

			ie.flushWriteRecord();
		}
	}
}


/** lista dei tipi campo */

const QStringList * DRInterface::getFieldTypes()
{
	static QStringList * tlist = 0;

	if ( tlist == ( QStringList * ) 0 ) {
		tlist = new QStringList();
		for ( int i = 0; i < DRF_NumTypes; i++ )
			tlist->append ( DRFieldTypes[i].description );
	}

	return tlist;
}

/* return field type size */

const size_t DRInterface::getTypeSize ( int atype )
{
	if ( atype >= 0 && atype < DRF_NumTypes ) {
		return DRFieldTypes[atype].size;
	} else return 0;
}


/* recalculate DR offsets */

void DRInterface::recalculateOffset()
{
	int calcoffset = 0;
	DRField *cf;

	for ( int i = 1; i <= count(); i++ ) {
		cf = get ( i );
		if ( cf == 0 )
			return;		// found a gap
		cf->data.offset = calcoffset;
		if ( cf->data.is_array )
			cf->data.size = cf->data.type_size * cf->data.array_size;
		else
			cf->data.size = cf->data.type_size;

		calcoffset += cf->data.size;
	}
}



/** Inserisce un nuovo campo nel record. Il campo assume la posizione specificata
o in assenza della posizione ordinale, viene accodato alla lista campi. */

bool DRInterface::add ( DRField * newfield )
{
	if ( newfield->data.position == 0 ) {
		++fieldsnumber;
		if ( fieldsnumber > DR_MAXFIELDS )
			return true;
		newfield->data.position = fieldsnumber;
	}
	// shift items if adding one in the middle of the record
	if ( newfield->data.position < fieldsnumber ) {
		if ( ( *fields->find ( QString::number ( newfield->data.position ) ) ) == ( DRField * ) 0 ) { 	// in the middle but not exists
			fields->insert ( QString::number ( newfield->data.position ), newfield );
		} else {										// in the middle but exists
			for ( int i = fieldsnumber; i > newfield->data.position; i-- ) {
				DRField *mf = ( *fields->find ( QString::number ( i ) ) );
				if ( mf == ( DRField * ) 0 )					// it's quite impossible, but...
					continue;
				fields->remove ( QString::number ( i ) );
				mf->data.position++;
				fields->insert ( QString::number ( mf->data.position ), mf );
			}								// now should be a hole here
			fields->insert ( QString::number ( newfield->data.position ), newfield );
		}
	} else {
		fields->insert ( QString::number ( newfield->data.position ), newfield ); // YES, i know, is a little redundant.
		if ( newfield->data.position > fieldsnumber )
			fieldsnumber = newfield->data.position;
	}

	recalculateOffset();
	return false;
}

/** retrieve a field, deallocates and remove from hash table */

bool DRInterface::remove ( int fieldnum )
{
	DRField *mf = ( *fields->find ( QString::number ( fieldnum ) ) );
	if ( mf == ( DRField * ) 0 ) {
		return true;
	}
	fields->remove ( QString::number ( fieldnum ) );
	delete mf;
	for ( int i = fieldnum + 1; i <= fieldsnumber; i++ ) {
		mf = ( *fields->find ( QString::number ( i ) ) );
		if ( mf == ( DRField * ) 0 )
			continue;
		fields->remove ( QString::number ( i ) );
		mf->data.position--;
		fields->insert ( QString::number ( mf->data.position ), mf );
	}

	recalculateOffset();
	return false;
}

/** gets a record from hush */

DRField * DRInterface::get ( int fieldnum )
{
	return ( *fields->find ( QString::number ( fieldnum ) ) );
}

// get by tag

DRField * DRInterface::getByTag ( const QString & srcTag )
{
	if ( fields->count() > 0 ) {
		foreach ( DRField * dr, *fields ) {
			if ( dr->data.description == srcTag )
				return dr;
		}
	}
	return ( DRField * ) 0;
}

// get tag list

TagList DRInterface::getTagList()
{
	TagList tagList;

	tagList.values << "-2";
	tagList.labels << "<none>";
	if ( fields->count() > 0 ) {
		foreach ( DRField * dr, *fields ) {
			tagList.values.append ( QString::number ( dr->data.position - 1 ) );
			tagList.labels.append ( QString ( dr->data.description ) );
		}
	}

	return tagList;
}


bool DRInterface::initFastget ( bool clear )
{
	if ( clear ) {
		if ( fimage )
			delete [] fimage;
		firsttime = true;
		return true;
	}
	if ( firsttime && count() > 0 ) {
		fimage = new DRField [count()];
		for ( int i = 0; i < count(); i++ ) {
			fimage[i] = * ( *fields->find ( QString::number ( i + 1 ) ) );
		}
		firsttime = false;
	}
	return firsttime;
}

// reset all

void DRInterface::clear()
{
	qDeleteAll ( *fields );
	clear();
	fieldsnumber = 0;
	initFastget ( true );
}

/* return record tag */

const char * DRInterface::getHeaderTag()
{
	static const char *ht = DR_HEADERTAG;

	return ht;
}

/* return record tag */

const char * DRInterface::getGlobalOptHeaderTag()
{
	static const char *ght = GLOBAL_HEADERTAG;

	return ght;
}

/* return DR record size */
int DRInterface::getDRRecordSize()
{
	int rsize = 0;
	int Count;
	DRField *fld;

	Count = count();
	for ( int fcnt = 1; fcnt <= Count; fcnt++ ) {
		fld = get ( fcnt );
		if ( fld ) {
			rsize += fld->data.size;
		}
	}
	return rsize;
}

// import a record from ie stream

void DRInterface::ieImport ( QString & ieRecordTag, ImportExport * ieptr )
{
	if ( ieRecordTag == DR_HEADERTAG ) {

		DRField *fld = new DRField;

		fld->data.position = ieptr->getIntToken ( "position" );
		fld->data.fieldtype = ieptr->getIntToken ( "fieldtype" );
		fld->data.offset = ieptr->getIntToken ( "offset" );
		fld->data.size = ieptr->getIntToken ( "size" );
		fld->data.type_size = ieptr->getIntToken ( "type_size" );
		fld->data.is_array = ieptr->getBoolToken ( "is_array" );
		fld->data.array_size = ieptr->getIntToken ( "array_size" );
		ieptr->getStrToken ( fld->data.description, "description", DR_FIELDSDESCRIPTIONLENGHT );
		fld->data.isIndex = ieptr->getBoolToken ( "isIndex" );
		fld->data.indexByPlugin = ieptr->getBoolToken ( "indexByPlugin" );
		fld->data.indexBlockSize = ieptr->getIntToken ( "indexBlockSize" );
		fld->data.indexRealTimeBlockSize = ieptr->getIntToken ( "indexRealTimeBlockSize" );
		fld->data.startSize = ieptr->getIntToken ( "startSize" );
		ieptr->getStrToken ( fld->data.indexSuffix, "indexSuffix", TE_SUFFIX_LEN );
		ieptr->getStrToken ( fld->data.startTime, "startTime", DR_DATE_LEN );
		ieptr->getStrToken ( fld->data.endTime, "endTime", DR_DATE_LEN );

		add ( fld );
	}

	if ( ieRecordTag == GLOBAL_HEADERTAG ) {
		glbOpt.data.drUsePlugin = ieptr->getBoolToken ( "drUsePlugin" );
		ieptr->getStrToken ( glbOpt.data.drPluginName, "drPluginName", DR_PLUGINNAME_LEN );
		glbOpt.data.drUsePath = ieptr->getBoolToken ( "drUsePath" );
		ieptr->getStrToken ( glbOpt.data.drPathName, "drPathName", DR_PATH_LEN );
		ieptr->getStrToken ( glbOpt.data.glbDefaultPluginPath, "glbDefaultPluginPath", GLB_INFO_LEN );
		ieptr->getStrToken ( glbOpt.data.glbAlias, "glbAlias", GLB_INFO_LEN );
		ieptr->getStrToken ( glbOpt.data.glbAuthor, "glbAuthor", GLB_INFO_LEN );
		ieptr->getStrToken ( glbOpt.data.glbLastModified, "glbLastModified", GLB_INFO_LEN );
	}

	return;
}

void DRInterface::copyToCorba ( DRSeq * seqptr )
{
	DATA_DR ddr;

	seqptr->length ( count() );
	int seqidx = 0;

	foreach ( DRField * dr, *fields ) {

		ddr.position = dr->data.position;
		ddr.fieldtype = dr->data.fieldtype;
		ddr.offset = dr->data.offset;
		ddr.size = dr->data.size;
		ddr.type_size = dr->data.type_size;
		ddr.is_array = dr->data.is_array;
		ddr.array_size = dr->data.array_size;
		c_qstrncpy ( ddr.description, dr->data.description, DR_FIELDSDESCRIPTIONLENGHT );
		ddr.isIndex = dr->data.isIndex;
		ddr.indexByPlugin = dr->data.indexByPlugin;
		ddr.indexBlockSize = dr->data.indexBlockSize;
		ddr.indexRealTimeBlockSize = dr->data.indexRealTimeBlockSize;
		ddr.startSize = dr->data.startSize;
		c_qstrncpy ( ddr.indexSuffix, dr->data.indexSuffix, TE_SUFFIX_LEN );
		c_qstrncpy ( ddr.startTime, dr->data.startTime, DR_DATE_LEN );
		c_qstrncpy ( ddr.endTime, dr->data.endTime, DR_DATE_LEN );

		( *seqptr ) [seqidx++] = ddr;
	}
}

void DRInterface::fillFromCorba ( const DRSeq * seqptr )
{
	DRField * dr;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		dr = new DRField;

		dr->data.position = ( *seqptr ) [cnt].position;
		dr->data.fieldtype = ( *seqptr ) [cnt].fieldtype;
		dr->data.offset = ( *seqptr ) [cnt].offset;
		dr->data.size = ( *seqptr ) [cnt].size;
		dr->data.type_size = ( *seqptr ) [cnt].type_size;
		dr->data.is_array = ( *seqptr ) [cnt].is_array;
		dr->data.array_size = ( *seqptr ) [cnt].array_size;
		c_qstrncpy ( dr->data.description, ( *seqptr ) [cnt].description, DR_FIELDSDESCRIPTIONLENGHT );
		dr->data.isIndex = ( *seqptr ) [cnt].isIndex;
		dr->data.indexByPlugin = ( *seqptr ) [cnt].indexByPlugin;
		dr->data.indexBlockSize = ( *seqptr ) [cnt].indexBlockSize;
		dr->data.indexRealTimeBlockSize = ( *seqptr ) [cnt].indexRealTimeBlockSize;
		dr->data.startSize = ( *seqptr ) [cnt].startSize;
		c_qstrncpy ( dr->data.indexSuffix, ( *seqptr ) [cnt].indexSuffix, TE_SUFFIX_LEN );
		c_qstrncpy ( dr->data.startTime, ( *seqptr ) [cnt].startTime, DR_DATE_LEN );
		c_qstrncpy ( dr->data.endTime, ( *seqptr ) [cnt].endTime, DR_DATE_LEN );

		add ( dr );
	}
	fieldsnumber = count();
	initFastget();
}

void DRInterface::copyToCorba ( GLOBALOPT * ptr )
{
	ptr->drUsePlugin = glbOpt.data.drUsePlugin;
	c_qstrncpy ( ptr->drPluginName, glbOpt.data.drPluginName, DR_PLUGINNAME_LEN );
	ptr->drUsePath = glbOpt.data.drUsePath;
	c_qstrncpy ( ptr->drPathName, glbOpt.data.drPathName, DR_PATH_LEN );
	c_qstrncpy ( ptr->glbDefaultPluginPath, glbOpt.data.glbDefaultPluginPath, GLB_INFO_LEN );
	c_qstrncpy ( ptr->glbAlias, glbOpt.data.glbAlias, GLB_INFO_LEN );
	c_qstrncpy ( ptr->glbAuthor, glbOpt.data.glbAuthor, GLB_INFO_LEN );
	c_qstrncpy ( ptr->glbLastModified, glbOpt.data.glbLastModified, GLB_INFO_LEN );
}

void DRInterface::fillFromCorba ( const GLOBALOPT * ptr )
{
	glbOpt.data.drUsePlugin = ptr->drUsePlugin;
	c_qstrncpy ( glbOpt.data.drPluginName, ptr->drPluginName, DR_PLUGINNAME_LEN );
	glbOpt.data.drUsePath = ptr->drUsePath;
	c_qstrncpy ( glbOpt.data.drPathName, ptr->drPathName, DR_PATH_LEN );
	c_qstrncpy ( glbOpt.data.glbDefaultPluginPath, ptr->glbDefaultPluginPath, GLB_INFO_LEN );
	c_qstrncpy ( glbOpt.data.glbAlias, ptr->glbAlias, GLB_INFO_LEN );
	c_qstrncpy ( glbOpt.data.glbAuthor, ptr->glbAuthor, GLB_INFO_LEN );
	c_qstrncpy ( glbOpt.data.glbLastModified, ptr->glbLastModified, GLB_INFO_LEN );
}



