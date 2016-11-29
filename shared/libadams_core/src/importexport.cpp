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

#include <importexport.h>
#include <Qt/qtextstream.h>
#include <applogger.h>

ImportExport::ImportExport ( QObject *parent ) : QObject ( parent ), QTextStream(),
	opened ( false )
{
}

ImportExport::~ImportExport()
{
	if ( opened )
		ief.close();
}

/** Imposta il nome del file da utilizzare per le operazioni di I/O */

void ImportExport::setFileName ( const QString & fn, bool autoextension )
{
	fileName = fn;
	if ( autoextension && !fileName.contains ( '.' ) )
		fileName += IMP_EXP_FILE_EXTENSION;
	ief.setFileName ( fileName );
}

// opens the file to be used for i/o

bool ImportExport::open ( QIODevice::OpenMode mode )
{
	if ( ief.open ( mode ) ) {
		setDevice ( &ief );
		return true;
	}
	else
		return false;
}

// initialize write record image */

void ImportExport::initWriteRecord ( const QString & recordIdTag )
{
	writeRecord.clear();
	writeRecord << QString ( "|" ) + recordIdTag + QString ( "|" );
	writeRecord << QDateTime::currentDateTime().toString() + QString ( "|" );
}

// flush the record image

bool ImportExport::flushWriteRecord()
{
	if ( writeRecord.isEmpty() )
		return false;
	for ( QStringList::Iterator it = writeRecord.begin(); it != writeRecord.end(); ++it ) {
		*this << *it;
	}
	*this << '\n';

	return false;
}

// do the import from a file stream

bool ImportExport::import()
{
	QString buf;

	while ( !atEnd() ) {
		if ( atEnd() )
			break;
		buf = readLine();
		if ( buf.isNull() )			// avoid null reads
			continue;

		readBuffer = buf.split ( QChar ( '|' ) );

		emit recordReady ( readBuffer[0], this );
	}

	return true;
}

QString ImportExport::extractToken ( const QString & token )
{
	if ( readBuffer.empty() )
		return QString ( "" );

	QString etoken = token + QString ( "=" );
	QStringList buf = readBuffer.filter ( etoken );

	if ( buf.empty() )
		return QString ( "" );
	else
		etoken = buf[0];

	int strip = etoken.indexOf ( QChar ( '=' ) );
	if ( strip < 0 )
		return QString ( "" );
	else
		return etoken.mid ( strip + 1 );
}

// export array of chars

void ImportExport::addArrayWriteRecord ( char * arg, unsigned int ar_size, unsigned int el_size, bool trueArray )
{
	char * buf = new char [el_size + 1];

	while ( ar_size-- ) {
		if ( trueArray ) {
			buf[el_size] = '\0';
			for ( int i = 0; i < el_size; i++ )
				buf[i] = arg[i];
			writeRecord << QString ( buf ) + QString ( "~" );
		}
		else {
			arg[el_size - 1] = '\0';
			writeRecord << QString ( arg ) + QString ( "~" );
		}
		arg += el_size;
	}
	writeRecord << QString ( "|" );

	delete buf;
}

// export qstringlist

void ImportExport::addWriteRecord ( const QStringList & arg )
{
	for ( QStringList::ConstIterator it = arg.begin(); it != arg.end(); ++it ) {
		writeRecord << *it + QString ( "~" );
	}
	writeRecord << QString ( "|" );
}

// export qlist<int>

void ImportExport::addWriteRecord ( const QList<int> & arg )
{
	for ( QList<int>::ConstIterator it = arg.begin(); it != arg.end(); ++it ) {
		writeRecord << QString::number ( *it ) + QString ( "~" );
	}
	writeRecord << QString ( "|" );
}

// export array of int

void ImportExport::addArrayWriteRecord ( int * arg, unsigned int ar_size )
{
	int i = 0;

	while ( ar_size-- ) {
		writeRecord << QString::number ( arg[i++] ) + QString ( "~" );
	}
	writeRecord << QString ( "|" );
}

// export matrixes of int

void ImportExport::addArrayWriteRecord ( int * arg, unsigned int ar_size, unsigned int el_num )
{
	while ( ar_size-- ) {
		for ( int i = 0; i < el_num; i++ )
			writeRecord << QString::number ( arg[i] ) + QString ( "," );
		writeRecord << QString ( "~" );
		arg += el_num;
	}
	writeRecord << QString ( "|" );
}

// export array of double

void ImportExport::addArrayWriteRecord ( double * arg, unsigned int ar_size )
{
	int i = 0;

	while ( ar_size-- ) {
		writeRecord << QString::number ( arg[i++] ) + QString ( "~" );
	}
	writeRecord << QString ( "|" );
}

// import array of double

void ImportExport::getDoubleArrToken ( double * dest, const QString & token, unsigned int size )
{
	QString etoken = extractToken ( token );
	QStringList vals = etoken.split ( QChar ( '~' ) );

	int idx = 0;
	for ( QStringList::Iterator it = vals.begin(); it != vals.end(); ++it ) {
		if ( idx >= ( size - 1 ) )
			break;
		dest[idx++] = ( *it ).toDouble();
	}
}

// import array of int

void ImportExport::getIntArrToken ( int * dest, const QString & token, unsigned int size )
{
	QStringList vals;
	QString etoken = extractToken ( token );
	QStringList vals0 = etoken.split ( QChar ( '~' ) );
	// check to see if this has been exported as a bi-dimensional array...
	// and in case import the first level (index 0) that is better than nothing......
	if ( vals0[0].contains ( "," ) ) {
		vals = vals0[0].split ( QChar ( ',' ) );
	}
	else
		vals = vals0;

	int idx = 0;
	for ( QStringList::Iterator it = vals.begin(); it != vals.end(); ++it ) {
		if ( idx >= ( size - 1 ) )
			break;
		dest[idx++] = ( *it ).toInt();
	}
}

// import array of int

void ImportExport::getIntArrToken ( int * dest, const QString & token, unsigned int size, unsigned int el_num )
{
	QString etoken = extractToken ( token );
	QStringList rows = etoken.split ( QChar ( '~' ) );

	int idx = 0;
	for ( QStringList::Iterator it = rows.begin(); it != rows.end(); ++it ) {
		QStringList cols = ( *it ).split ( QChar ( ',' ) );

		int idx1 = 0;
		for ( QStringList::Iterator it1 = cols.begin(); it1 != cols.end(); ++it1 ) {
			if ( idx1 >= ( el_num - 1 ) )
				break;
			dest[idx1++] = ( *it1 ).toInt();
		}
		if ( idx >= ( size - 1 ) )
			break;
		dest += el_num;
		++idx;
	}
}

// import array of string

void ImportExport::getStrArrToken ( char * dest, const QString & token, unsigned int ar_size, unsigned int el_size, bool trueArray )
{
	QString etoken = extractToken ( token );
	QStringList vals = etoken.split ( QChar ( '~' ) );
	char * buf = new char [el_size + 1];

	int idx = 0;
	for ( QStringList::Iterator it = vals.begin(); it != vals.end(); ++it ) {
		if ( idx == ( ar_size - 1 ) )
			break;
		if ( trueArray ) {
			qstrcpy ( buf,  qPrintable ( ( *it ).mid ( 0, el_size ) ) );
			for ( int i = 0; i < el_size; i++ )
				dest[i] = buf[i];
		}
		else
			qstrcpy ( dest, qPrintable ( ( *it ).mid ( 0, el_size - 1 ) ) );
		dest += el_size;
		++idx;
	}
	delete buf;
}

// import array of qstring

void ImportExport::getQStringListToken ( QStringList & dest, const QString & token )
{
	QString etoken = extractToken ( token );
	dest = etoken.split ( QChar ( '~' ) );
}

// import qlist<int>

void ImportExport::getQListIntToken ( QList<int> & dest, const QString & token )
{
	QString etoken = extractToken ( token );
	QStringList vals = etoken.split ( QChar ( '~' ) );

	int idx = 0;
	for ( QStringList::Iterator it = vals.begin(); it != vals.end(); ++it ) {
		dest.append(QString(*it).toInt());
	}
}


void ImportExport::errFilterMsg()
{
	lout << ERR_FILTER_MSG << endl;
}
