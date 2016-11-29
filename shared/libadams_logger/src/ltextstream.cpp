/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */

/****************************************************************************
**
** Copyright (C) 2010 Nokia Corporation and/or its subsidiary(-ies).
** All rights reserved.
** Contact: Nokia Corporation (qt-info@nokia.com)
**
** This file is part of the QtCore module of the Qt Toolkit.
**
** $QT_BEGIN_LICENSE:LGPL$
** Commercial Usage
** Licensees holding valid Qt Commercial licenses may use this file in
** accordance with the Qt Commercial License Agreement provided with the
** Software or, alternatively, in accordance with the terms contained in
** a written agreement between you and Nokia.
**
** GNU Lesser General Public License Usage
** Alternatively, this file may be used under the terms of the GNU Lesser
** General Public License version 2.1 as published by the Free Software
** Foundation and appearing in the file LICENSE.LGPL included in the
** packaging of this file.  Please review the following information to
** ensure the GNU Lesser General Public License version 2.1 requirements
** will be met: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html.
**
** In addition, as a special exception, Nokia gives you certain additional
** rights.  These rights are described in the Nokia Qt LGPL Exception
** version 1.1, included in the file LGPL_EXCEPTION.txt in this package.
**
** GNU General Public License Usage
** Alternatively, this file may be used under the terms of the GNU
** General Public License version 3.0 as published by the Free Software
** Foundation and appearing in the file LICENSE.GPL included in the
** packaging of this file.  Please review the following information to
** ensure the GNU General Public License version 3.0 requirements will be
** met: http://www.gnu.org/copyleft/gpl.html.
**
** If you have questions regarding the use of this file, please contact
** Nokia at qt-info@nokia.com.
** $QT_END_LICENSE$
**
****************************************************************************/

//#define LTEXTSTREAM_DEBUG
static const int LTEXTSTREAM_BUFFERSIZE = 16384;

// #include <Qt/qmutex.h>

// static QMutex m_mutex;

#include <ltextstream.h>
#include <Qt/qbuffer.h>
#include <Qt/qfile.h>
#include <Qt/qnumeric.h>
#include <Qt/qdatetime.h>
#ifndef QT_NO_TEXTCODEC
#include <Qt/qtextcodec.h>
#endif
#ifndef Q_OS_WINCE
#include <locale.h>
#endif
#include "llocale_p.h"

#include <stdlib.h>
#include <unistd.h>
#include <limits.h>
#include <new>
// #include <iostream>


static net::etech::adams::ssm_logger * ssm_logger = 0;
static QString application_name;
LTextStream::LogLevels reference_log_level;

#if defined LTEXTSTREAM_DEBUG
#include <ctype.h>

QT_BEGIN_NAMESPACE

// Returns a human readable representation of the first \a len
// characters in \a data.
static QByteArray qt_prettyDebug ( const char *data, int len, int maxSize )
{
	if ( !data ) return "(null)";
	QByteArray out;
	for ( int i = 0; i < len; ++i ) {
		char c = data[i];
		if ( isprint ( int ( uchar ( c ) ) ) ) {
			out += c;
		} else switch ( c ) {
				case '\n':
					out += "\\n";
					break;
				case '\r':
					out += "\\r";
					break;
				case '\t':
					out += "\\t";
					break;
				default:
					QString tmp;
					tmp.sprintf ( "\\x%x", ( unsigned int ) ( unsigned char ) c );
					out += tmp.toLatin1();
			}
	}

	if ( len < maxSize )
		out += "...";

	return out;
}
QT_END_NAMESPACE

#endif

// A precondition macro
#define Q_VOID
#define CHECK_VALID_STREAM(x) do { \
    if (!d->string && !d->device) { \
        qWarning("LTextStream: No device"); \
        return x; \
    } } while (0)

// Base implementations of operator>> for ints and reals
#define IMPLEMENT_STREAM_RIGHT_INT_OPERATOR(type) do { \
    Q_D(LTextStream); \
    CHECK_VALID_STREAM(*this); \
    qulonglong tmp; \
    switch (d->getNumber(&tmp)) { \
    case LTextStreamPrivate::npsOk: \
        i = (type)tmp; \
        break; \
    case LTextStreamPrivate::npsMissingDigit: \
    case LTextStreamPrivate::npsInvalidPrefix: \
        i = (type)0; \
        setStatus(atEnd() ? LTextStream::ReadPastEnd : LTextStream::ReadCorruptData); \
        break; \
    } \
    return *this; } while (0)

#define IMPLEMENT_STREAM_RIGHT_REAL_OPERATOR(type) do { \
    Q_D(LTextStream); \
    CHECK_VALID_STREAM(*this); \
    double tmp; \
    if (d->getReal(&tmp)) { \
        f = (type)tmp; \
    } else { \
        f = (type)0; \
        setStatus(atEnd() ? LTextStream::ReadPastEnd : LTextStream::ReadCorruptData); \
    } \
    return *this; } while (0)

QT_BEGIN_NAMESPACE


//-------------------------------------------------------------------
class LTextStreamPrivate
{
	Q_DECLARE_PUBLIC ( LTextStream )
public:
	LTextStreamPrivate ( LTextStream *q_ptr );
	~LTextStreamPrivate();
	void reset();

	// device
	QIODevice *device;
#ifndef QT_NO_QOBJECT
	LDeviceClosedNotifier deviceClosedNotifier;
#endif
	bool deleteDevice;

	// string
	QString *string;
	int stringOffset;
	QIODevice::OpenMode stringOpenMode;
// 	net::etech::adams::ssm_logger * ssm_logger;
// 	LTextStream::LogLevels reference_log_level;
// 	QString application_name;
	pid_t application_pid;

#ifndef QT_NO_TEXTCODEC
	// codec
	QTextCodec *codec;
	QTextCodec::ConverterState readConverterState;
	QTextCodec::ConverterState writeConverterState;
	QTextCodec::ConverterState *readConverterSavedState;
	bool autoDetectUnicode;
#endif

	// i/o
	enum TokenDelimiter {
		Space,
		NotSpace,
		EndOfLine
	};

	QString read ( int maxlen );
	bool scan ( const QChar **ptr, int *tokenLength,
	            int maxlen, TokenDelimiter delimiter );
	inline const QChar *readPtr() const;
	inline void consumeLastToken();
	inline void consume ( int nchars );
	void saveConverterState ( qint64 newPos );
	void restoreToSavedConverterState();
	int lastTokenSize;

	// Return value type for getNumber()
	enum NumberParsingStatus {
		npsOk,
		npsMissingDigit,
		npsInvalidPrefix
	};

	inline bool write ( const QString &data );
	inline bool getChar ( QChar *ch );
	inline void ungetChar ( const QChar &ch );
	NumberParsingStatus getNumber ( qulonglong *l );
	bool getReal ( double *f );

	bool putNumber ( qulonglong number, bool negative );
	inline bool putString ( const QString &ch, bool number = false );

	// buffers
	bool fillReadBuffer ( qint64 maxBytes = -1 );
	void resetReadBuffer();
	bool flushWriteBuffer();
	QString writeBuffer;
	QString readBuffer;
	int readBufferOffset;
	int readConverterSavedStateOffset; //the offset between readBufferStartDevicePos and that start of the buffer
	qint64 readBufferStartDevicePos;

	// streaming parameters
	int realNumberPrecision;
	int integerBase;
	int fieldWidth;
	QChar padChar;
	LTextStream::FieldAlignment fieldAlignment;
	LTextStream::RealNumberNotation realNumberNotation;
	LTextStream::NumberFlags numberFlags;

	// logger
	LTextStream::LogLevels logLevel;
	LTextStream::LogDestination logDestination;

	// status
	LTextStream::Status status;

	QLocale locale;

	LTextStream *q_ptr;
};

/*! \internal
*/
LTextStreamPrivate::LTextStreamPrivate ( LTextStream *q_ptr )
		:
#ifndef QT_NO_TEXTCODEC
		readConverterSavedState ( 0 ),
#endif
		readConverterSavedStateOffset ( 0 ),
		locale ( QLocale::c() )
{
	this->q_ptr = q_ptr;
	reset();
}

/*! \internal
*/
LTextStreamPrivate::~LTextStreamPrivate()
{
	if ( deleteDevice ) {
#ifndef QT_NO_QOBJECT
		device->blockSignals ( true );
#endif
		delete device;
	}
#ifndef QT_NO_TEXTCODEC
	delete readConverterSavedState;
#endif
}

#ifndef QT_NO_TEXTCODEC
static void resetCodecConverterStateHelper ( QTextCodec::ConverterState *state )
{
	state->~ConverterState();
	new ( state ) QTextCodec::ConverterState;
}

static void copyConverterStateHelper ( QTextCodec::ConverterState *dest,
                                       const QTextCodec::ConverterState *src )
{
	// ### QTextCodec::ConverterState's copy constructors and assignments are
	// private. This function copies the structure manually.
	Q_ASSERT ( !src->d );
	dest->flags = src->flags;
	dest->invalidChars = src->invalidChars;
	dest->state_data[0] = src->state_data[0];
	dest->state_data[1] = src->state_data[1];
	dest->state_data[2] = src->state_data[2];
}
#endif

/*! \internal
*/
void LTextStreamPrivate::reset()
{
	realNumberPrecision = 6;
	integerBase = 0;
	fieldWidth = 0;
	padChar = QLatin1Char ( ' ' );
	fieldAlignment = LTextStream::AlignRight;
	realNumberNotation = LTextStream::SmartNotation;
	numberFlags = 0;

	device = 0;
	deleteDevice = false;
	string = 0;
	stringOffset = 0;
	stringOpenMode = QIODevice::NotOpen;

	readBufferOffset = 0;
	readBufferStartDevicePos = 0;
	lastTokenSize = 0;

#ifndef QT_NO_TEXTCODEC
	codec = QTextCodec::codecForLocale();
	resetCodecConverterStateHelper ( &readConverterState );
	resetCodecConverterStateHelper ( &writeConverterState );
	delete readConverterSavedState;
	readConverterSavedState = 0;
	writeConverterState.flags |= QTextCodec::IgnoreHeader;
	autoDetectUnicode = true;
#endif
}

/*! \internal
*/
bool LTextStreamPrivate::fillReadBuffer ( qint64 maxBytes )
{
	// no buffer next to the QString itself; this function should only
	// be called internally, for devices.
	Q_ASSERT ( !string );
	Q_ASSERT ( device );

	// handle text translation and bypass the Text flag in the device.
	bool textModeEnabled = device->isTextModeEnabled();
	if ( textModeEnabled )
		device->setTextModeEnabled ( false );

	// read raw data into a temporary buffer
	char buf[LTEXTSTREAM_BUFFERSIZE];
	qint64 bytesRead = 0;
#if defined(Q_OS_WIN)
	// On Windows, there is no non-blocking stdin - so we fall back to reading
	// lines instead. If there is no QOBJECT, we read lines for all sequential
	// devices; otherwise, we read lines only for stdin.
	QFile *file = 0;
	Q_UNUSED ( file );
	if ( device->isSequential()
#if !defined(QT_NO_QOBJECT)
	                && ( file = qobject_cast<QFile *> ( device ) ) && file->handle() == 0
#endif
	   ) {
		if ( maxBytes != -1 )
			bytesRead = device->readLine ( buf, qMin<qint64> ( sizeof ( buf ), maxBytes ) );
		else
			bytesRead = device->readLine ( buf, sizeof ( buf ) );
	} else
#endif
	{
		if ( maxBytes != -1 )
			bytesRead = device->read ( buf, qMin<qint64> ( sizeof ( buf ), maxBytes ) );
		else
			bytesRead = device->read ( buf, sizeof ( buf ) );
	}

#ifndef QT_NO_TEXTCODEC
	// codec auto detection, explicitly defaults to locale encoding if the
	// codec has been set to 0.
	if ( !codec || autoDetectUnicode ) {
		autoDetectUnicode = false;

		codec = QTextCodec::codecForUtfText ( QByteArray::fromRawData ( buf, bytesRead ), codec );
		if ( !codec ) {
			codec = QTextCodec::codecForLocale();
			writeConverterState.flags |= QTextCodec::IgnoreHeader;
		}
	}
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::fillReadBuffer(), using %s codec",
	         codec->name().constData() );
#endif
#endif

#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::fillReadBuffer(), device->read(\"%s\", %d) == %d",
	         qt_prettyDebug ( buf, qMin ( 32, int ( bytesRead ) ) , int ( bytesRead ) ).constData(), sizeof ( buf ), int ( bytesRead ) );
#endif

	if ( bytesRead <= 0 )
		return false;

	int oldReadBufferSize = readBuffer.size();
#ifndef QT_NO_TEXTCODEC
	// convert to unicode
	readBuffer += codec->toUnicode ( buf, bytesRead, &readConverterState );
#else
	readBuffer += QString::fromLatin1 ( QByteArray ( buf, bytesRead ).constData() );
#endif

	// reset the Text flag.
	if ( textModeEnabled )
		device->setTextModeEnabled ( true );

	// remove all '\r\n' in the string.
	if ( readBuffer.size() > oldReadBufferSize && textModeEnabled ) {
		QChar CR = QLatin1Char ( '\r' );
		QChar *writePtr = readBuffer.data() + oldReadBufferSize;
		QChar *readPtr = readBuffer.data() + oldReadBufferSize;
		QChar *endPtr = readBuffer.data() + readBuffer.size();

		int n = oldReadBufferSize;
		if ( readPtr < endPtr ) {
			// Cut-off to avoid unnecessary self-copying.
			while ( *readPtr++ != CR ) {
				++n;
				if ( ++writePtr == endPtr )
					break;
			}
		}
		while ( readPtr < endPtr ) {
			QChar ch = *readPtr++;
			if ( ch != CR ) {
				*writePtr++ = ch;
			} else {
				if ( n < readBufferOffset )
					--readBufferOffset;
				--bytesRead;
			}
			++n;
		}
		readBuffer.resize ( writePtr - readBuffer.data() );
	}

#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::fillReadBuffer() read %d bytes from device. readBuffer = [%s]", int ( bytesRead ),
	         qt_prettyDebug ( readBuffer.toLatin1(), readBuffer.size(), readBuffer.size() ).data() );
#endif
	return true;
}

/*! \internal
*/
void LTextStreamPrivate::resetReadBuffer()
{
	readBuffer.clear();
	readBufferOffset = 0;
	readBufferStartDevicePos = ( device ? device->pos() : 0 );
}

/*! \internal
*/
bool LTextStreamPrivate::flushWriteBuffer()
{
	// no buffer next to the QString itself; this function should only
	// be called internally, for devices.
	if ( string || !device )
		return false;
	if ( writeBuffer.isEmpty() )
		return true;

#if defined (Q_OS_WIN)
	// handle text translation and bypass the Text flag in the device.
	bool textModeEnabled = device->isTextModeEnabled();
	if ( textModeEnabled ) {
		device->setTextModeEnabled ( false );
		writeBuffer.replace ( QLatin1Char ( '\n' ), QLatin1String ( "\r\n" ) );
	}
#endif

#ifndef QT_NO_TEXTCODEC
	if ( !codec )
		codec = QTextCodec::codecForLocale();
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::flushWriteBuffer(), using %s codec (%s generating BOM)",
	         codec->name().constData(), writeConverterState.flags & QTextCodec::IgnoreHeader ? "not" : "" );
#endif

	// convert from unicode to raw data
	QByteArray data = codec->fromUnicode ( writeBuffer.data(), writeBuffer.size(), &writeConverterState );
#else
	QByteArray data = writeBuffer.toLocal8Bit();
#endif
	writeBuffer.clear();

	// write raw data to the device
	qint64 bytesWritten = device->write ( data );
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::flushWriteBuffer(), device->write(\"%s\") == %d",
	         qt_prettyDebug ( data.constData(), qMin ( data.size(), 32 ), data.size() ).constData(), int ( bytesWritten ) );
#endif
	if ( bytesWritten <= 0 )
		return false;

#if defined (Q_OS_WIN)
	// replace the text flag
	if ( textModeEnabled )
		device->setTextModeEnabled ( true );
#endif

	// flush the file
#ifndef QT_NO_QOBJECT
	QFile *file = qobject_cast<QFile *> ( device );
	bool flushed = file && file->flush();
#else
	bool flushed = true;
#endif

#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::flushWriteBuffer() wrote %d bytes",
	         int ( bytesWritten ) );
#endif
	return flushed && bytesWritten == qint64 ( data.size() );
}

QString LTextStreamPrivate::read ( int maxlen )
{
	QString ret;
	if ( string ) {
		lastTokenSize = qMin ( maxlen, string->size() - stringOffset );
		ret = string->mid ( stringOffset, lastTokenSize );
	} else {
		while ( readBuffer.size() - readBufferOffset < maxlen && fillReadBuffer() ) ;
		lastTokenSize = qMin ( maxlen, readBuffer.size() - readBufferOffset );
		ret = readBuffer.mid ( readBufferOffset, lastTokenSize );
	}
	consumeLastToken();

#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::read() maxlen = %d, token length = %d", maxlen, ret.length() );
#endif
	return ret;
}

/*! \internal

    Scans no more than \a maxlen QChars in the current buffer for the
    first \a delimiter. Stores a pointer to the start offset of the
    token in \a ptr, and the length in QChars in \a length.
*/
bool LTextStreamPrivate::scan ( const QChar **ptr, int *length, int maxlen, TokenDelimiter delimiter )
{
	int totalSize = 0;
	int delimSize = 0;
	bool consumeDelimiter = false;
	bool foundToken = false;
	int startOffset = device ? readBufferOffset : stringOffset;
	QChar lastChar;

	bool canStillReadFromDevice = true;
	do {
		int endOffset;
		const QChar *chPtr;
		if ( device ) {
			chPtr = readBuffer.constData();
			endOffset = readBuffer.size();
		} else {
			chPtr = string->constData();
			endOffset = string->size();
		}
		chPtr += startOffset;

		for ( ; !foundToken && startOffset < endOffset && ( !maxlen || totalSize < maxlen ); ++startOffset ) {
			const QChar ch = *chPtr++;
			++totalSize;

			switch ( delimiter ) {
				case Space:
					if ( ch.isSpace() ) {
						foundToken = true;
						delimSize = 1;
					}
					break;
				case NotSpace:
					if ( !ch.isSpace() ) {
						foundToken = true;
						delimSize = 1;
					}
					break;
				case EndOfLine:
					if ( ch == QLatin1Char ( '\n' ) ) {
						foundToken = true;
						delimSize = ( lastChar == QLatin1Char ( '\r' ) ) ? 2 : 1;
						consumeDelimiter = true;
					}
					lastChar = ch;
					break;
			}
		}
	} while ( !foundToken
	                && ( !maxlen || totalSize < maxlen )
	                && ( device && ( canStillReadFromDevice = fillReadBuffer() ) ) );

	// if the token was not found, but we reached the end of input,
	// then we accept what we got. if we are not at the end of input,
	// we return false.
	if ( !foundToken && ( !maxlen || totalSize < maxlen )
	                && ( totalSize == 0
	                     || ( string && stringOffset + totalSize < string->size() )
	                     || ( device && !device->atEnd() && canStillReadFromDevice ) ) ) {
#if defined (LTEXTSTREAM_DEBUG)
		qDebug ( "LTextStreamPrivate::scan() did not find the token." );
#endif
		return false;
	}

	// if we find a '\r' at the end of the data when reading lines,
	// don't make it part of the line.
	if ( delimiter == EndOfLine && totalSize > 0 && !foundToken ) {
		if ( ( ( string && stringOffset + totalSize == string->size() ) || ( device && device->atEnd() ) )
		                && lastChar == QLatin1Char ( '\r' ) ) {
			consumeDelimiter = true;
			++delimSize;
		}
	}

	// set the read offset and length of the token
	if ( length )
		*length = totalSize - delimSize;
	if ( ptr )
		*ptr = readPtr();

	// update last token size. the callee will call consumeLastToken() when
	// done.
	lastTokenSize = totalSize;
	if ( !consumeDelimiter )
		lastTokenSize -= delimSize;

#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::scan(%p, %p, %d, %x) token length = %d, delimiter = %d",
	         ptr, length, maxlen, ( int ) delimiter, totalSize - delimSize, delimSize );
#endif
	return true;
}

/*! \internal
*/
inline const QChar *LTextStreamPrivate::readPtr() const
{
	Q_ASSERT ( readBufferOffset <= readBuffer.size() );
	if ( string )
		return string->constData() + stringOffset;
	return readBuffer.constData() + readBufferOffset;
}

/*! \internal
*/
inline void LTextStreamPrivate::consumeLastToken()
{
	if ( lastTokenSize )
		consume ( lastTokenSize );
	lastTokenSize = 0;
}

/*! \internal
*/
inline void LTextStreamPrivate::consume ( int size )
{
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStreamPrivate::consume(%d)", size );
#endif
	if ( string ) {
		stringOffset += size;
		if ( stringOffset > string->size() )
			stringOffset = string->size();
	} else {
		readBufferOffset += size;
		if ( readBufferOffset >= readBuffer.size() ) {
			readBufferOffset = 0;
			readBuffer.clear();
			saveConverterState ( device->pos() );
		} else if ( readBufferOffset > LTEXTSTREAM_BUFFERSIZE ) {
			readBuffer = readBuffer.remove ( 0, readBufferOffset );
			readConverterSavedStateOffset += readBufferOffset;
			readBufferOffset = 0;
		}
	}
}

/*! \internal
*/
inline void LTextStreamPrivate::saveConverterState ( qint64 newPos )
{
#ifndef QT_NO_TEXTCODEC
	if ( readConverterState.d ) {
		// converter cannot be copied, so don't save anything
		// don't update readBufferStartDevicePos either
		return;
	}

	if ( !readConverterSavedState )
		readConverterSavedState = new QTextCodec::ConverterState;
	copyConverterStateHelper ( readConverterSavedState, &readConverterState );
#endif

	readBufferStartDevicePos = newPos;
	readConverterSavedStateOffset = 0;
}

/*! \internal
*/
inline void LTextStreamPrivate::restoreToSavedConverterState()
{
#ifndef QT_NO_TEXTCODEC
	if ( readConverterSavedState ) {
		// we have a saved state
		// that means the converter can be copied
		copyConverterStateHelper ( &readConverterState, readConverterSavedState );
	} else {
		// the only state we could save was the initial
		// so reset to that
		resetCodecConverterStateHelper ( &readConverterState );
	}
#endif
}

/*! \internal
*/
inline bool LTextStreamPrivate::write ( const QString &data )
{
	if ( string ) {
		// ### What about seek()??
		string->append ( data );
	} else {
		writeBuffer += data;
		if ( writeBuffer.size() > LTEXTSTREAM_BUFFERSIZE )
			return flushWriteBuffer();
	}
	return true;
}

/*! \internal
*/
inline bool LTextStreamPrivate::getChar ( QChar *ch )
{
	if ( ( string && stringOffset == string->size() )
	                || ( device && readBuffer.isEmpty() && !fillReadBuffer() ) ) {
		if ( ch )
			*ch = 0;
		return false;
	}
	if ( ch )
		*ch = *readPtr();
	consume ( 1 );
	return true;
}

/*! \internal
*/
inline void LTextStreamPrivate::ungetChar ( const QChar &ch )
{
	if ( string ) {
		if ( stringOffset == 0 )
			string->prepend ( ch );
		else
			( *string ) [--stringOffset] = ch;
		return;
	}

	if ( readBufferOffset == 0 ) {
		readBuffer.prepend ( ch );
		return;
	}

	readBuffer[--readBufferOffset] = ch;
}

/*! \internal
*/
inline bool LTextStreamPrivate::putString ( const QString &s, bool number )
{
	QString tmp = s;

	// handle padding
	int padSize = fieldWidth - s.size();
	if ( padSize > 0 ) {
		QString pad ( padSize, padChar );
		if ( fieldAlignment == LTextStream::AlignLeft ) {
			tmp.append ( QString ( padSize, padChar ) );
		} else if ( fieldAlignment == LTextStream::AlignRight
		                || fieldAlignment == LTextStream::AlignAccountingStyle ) {
			tmp.prepend ( QString ( padSize, padChar ) );
			if ( fieldAlignment == LTextStream::AlignAccountingStyle && number ) {
				const QChar sign = s.size() > 0 ? s.at ( 0 ) : QChar();
				if ( sign == locale.negativeSign() || sign == locale.positiveSign() ) {
					QChar *data = tmp.data();
					data[padSize] = tmp.at ( 0 );
					data[0] = sign;
				}
			}
		} else if ( fieldAlignment == LTextStream::AlignCenter ) {
			tmp.prepend ( QString ( padSize / 2, padChar ) );
			tmp.append ( QString ( padSize - padSize / 2, padChar ) );
		}
	}

#if defined (LTEXTSTREAM_DEBUG)
	QByteArray a = s.toUtf8();
	QByteArray b = tmp.toUtf8();
	qDebug ( "LTextStreamPrivate::putString(\"%s\") calls write(\"%s\")",
	         qt_prettyDebug ( a.constData(), a.size(), qMax ( 16, a.size() ) ).constData(),
	         qt_prettyDebug ( b.constData(), b.size(), qMax ( 16, b.size() ) ).constData() );
#endif
	return write ( tmp );
}

/*!
    Constructs a LTextStream. Before you can use it for reading or
    writing, you must assign a device or a string.

    \sa setDevice(), setString()
*/
LTextStream::LTextStream()
		: d_ptr ( new LTextStreamPrivate ( this ) )
{
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::LTextStream()" );
#endif
	Q_D ( LTextStream );
	d->status = Ok;
	d->logLevel = LogError;
}

/*!
    Constructs a LTextStream that operates on \a device.
*/
LTextStream::LTextStream ( QIODevice *device )
		: d_ptr ( new LTextStreamPrivate ( this ) )
{
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::LTextStream(QIODevice *device == *%p)",
	         device );
#endif
	Q_D ( LTextStream );
	d->device = device;
#ifndef QT_NO_QOBJECT
	d->deviceClosedNotifier.setupDevice ( this, d->device );
#endif
	d->status = Ok;
	d->logLevel = LogError;
}

/*!
    Constructs a LTextStream that operates on \a string, using \a
    openMode to define the open mode.
*/
LTextStream::LTextStream ( QString *string, QIODevice::OpenMode openMode )
		: d_ptr ( new LTextStreamPrivate ( this ) )
{
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::LTextStream(QString *string == *%p, openMode = %d)",
	         string, int ( openMode ) );
#endif
	Q_D ( LTextStream );
	d->string = string;
	d->stringOpenMode = openMode;
	d->status = Ok;
	d->logLevel = LogError;
}

/*!
 *    Constructs a LTextStream that operates on \a string, using \a
 *    openMode to define the open mode.
 */
LTextStream::LTextStream ( const QString & app, QString * string, net::etech::adams::ssm_logger * ssm_logger_i, QIODevice::OpenMode openMode )
: d_ptr ( new LTextStreamPrivate ( this ) )
{
	#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::LTextStream(QString *string == *%p, openMode = %d)",
		 string, int ( openMode ) );
	#endif
	Q_D ( LTextStream );
	d->string = string;
	ssm_logger = ssm_logger_i;
	d->stringOpenMode = openMode;
	d->status = Ok;
	d->logLevel = LogError;
	application_name = app;
	d->application_pid = ::getpid();
}

/*!
    Constructs a LTextStream that operates on \a array, using \a
    openMode to define the open mode. Internally, the array is wrapped
    by a QBuffer.
*/
LTextStream::LTextStream ( QByteArray *array, QIODevice::OpenMode openMode )
		: d_ptr ( new LTextStreamPrivate ( this ) )
{
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::LTextStream(QByteArray *array == *%p, openMode = %d)",
	         array, int ( openMode ) );
#endif
	Q_D ( LTextStream );
	d->device = new QBuffer ( array );
	d->device->open ( openMode );
	d->deleteDevice = true;
#ifndef QT_NO_QOBJECT
	d->deviceClosedNotifier.setupDevice ( this, d->device );
#endif
	d->status = Ok;
	d->logLevel = LogError;
}

/*!
    Constructs a LTextStream that operates on \a array, using \a
    openMode to define the open mode. The array is accessed as
    read-only, regardless of the values in \a openMode.

    This constructor is convenient for working on constant
    strings. Example:

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 3
*/
LTextStream::LTextStream ( const QByteArray &array, QIODevice::OpenMode openMode )
		: d_ptr ( new LTextStreamPrivate ( this ) )
{
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::LTextStream(const QByteArray &array == *(%p), openMode = %d)",
	         &array, int ( openMode ) );
#endif
	QBuffer *buffer = new QBuffer;
	buffer->setData ( array );
	buffer->open ( openMode );

	Q_D ( LTextStream );
	d->device = buffer;
	d->deleteDevice = true;
#ifndef QT_NO_QOBJECT
	d->deviceClosedNotifier.setupDevice ( this, d->device );
#endif
	d->status = Ok;
	d->logLevel = LogError;
}

/*!
    Constructs a LTextStream that operates on \a fileHandle, using \a
    openMode to define the open mode. Internally, a QFile is created
    to handle the FILE pointer.

    This constructor is useful for working directly with the common
    FILE based input and output streams: stdin, stdout and stderr. Example:

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 4
*/

LTextStream::LTextStream ( FILE *fileHandle, QIODevice::OpenMode openMode )
		: d_ptr ( new LTextStreamPrivate ( this ) )
{
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::LTextStream(FILE *fileHandle = %p, openMode = %d)",
	         fileHandle, int ( openMode ) );
#endif
	QFile *file = new QFile;
	file->open ( fileHandle, openMode );

	Q_D ( LTextStream );
	d->device = file;
	d->deleteDevice = true;
#ifndef QT_NO_QOBJECT
	d->deviceClosedNotifier.setupDevice ( this, d->device );
#endif
	d->status = Ok;
	d->logLevel = LogError;
}

/*!
    Destroys the LTextStream.

    If the stream operates on a device, flush() will be called
    implicitly. Otherwise, the device is unaffected.
*/
LTextStream::~LTextStream()
{
	Q_D ( LTextStream );
#if defined (LTEXTSTREAM_DEBUG)
	qDebug ( "LTextStream::~LTextStream()" );
#endif
	if ( !d->writeBuffer.isEmpty() )
		d->flushWriteBuffer();
// 	toSSMLogger();
}

/*!
    Resets LTextStream's formatting options, bringing it back to its
    original constructed state. The device, string and any buffered
    data is left untouched.
*/
void LTextStream::reset()
{
	Q_D ( LTextStream );

	d->realNumberPrecision = 6;
	d->integerBase = 0;
	d->fieldWidth = 0;
	d->padChar = QLatin1Char ( ' ' );
	d->fieldAlignment = LTextStream::AlignRight;
	d->realNumberNotation = LTextStream::SmartNotation;
	d->numberFlags = 0;
	d->logLevel = LogError;
}

/*!
    Flushes any buffered data waiting to be written to the device.

    If LTextStream operates on a string, this function does nothing.
*/
void LTextStream::flush()
{
	Q_D ( LTextStream );
	d->flushWriteBuffer();
	toSSMLogger();
}

void LTextStream::setReferenceLogLevel( LTextStream::LogLevels lev )
{
	reference_log_level = lev;
}

LTextStream::LogLevels LTextStream::getReferenceLogLevel()
{
	return reference_log_level;
}

void LTextStream::setSSMLogger( net::etech::adams::ssm_logger * ssm_logger_i )
{
	ssm_logger = ssm_logger_i;
}

net::etech::adams::ssm_logger * LTextStream::getSSMLogger()
{
	return ssm_logger;
}

void LTextStream::setApplicationName( const QString & app_name )
{
	application_name = app_name;
}

const QString & LTextStream::getApplicationName()
{
	return application_name;
}
 /*!
 *    Sends string data to ssm_loger D-BUS interface
 */
void LTextStream::toSSMLogger ()
{
	Q_D ( LTextStream );

	// 	mtx.lock();

	if ( d->logLevel > reference_log_level ) {
		d->string->clear();
		return;
	}

	QDateTime now = QDateTime::currentDateTime();

	if ( d->logDestination == LTextStream::ToLogger ) {
		if (ssm_logger)
			ssm_logger->toLogger ( d->logLevel, now, application_name, d->application_pid, *(d->string) );
		else
			return;
// 			std::cerr << qPrintable(*(d->string)) << std::endl;
	}
	else {
		if (ssm_logger)
			ssm_logger->toMonitor ( now, application_name, *(d->string) );
	}

	d->string->clear();

// 	mtx.unlock();
}


/*!
    Seeks to the position \a pos in the device. Returns true on
    success; otherwise returns false.
*/
bool LTextStream::seek ( qint64 pos )
{
	Q_D ( LTextStream );
	d->lastTokenSize = 0;

	if ( d->device ) {
		// Empty the write buffer
		d->flushWriteBuffer();
		toSSMLogger();
		if ( !d->device->seek ( pos ) )
			return false;
		d->resetReadBuffer();

#ifndef QT_NO_TEXTCODEC
		// Reset the codec converter states.
		resetCodecConverterStateHelper ( &d->readConverterState );
		resetCodecConverterStateHelper ( &d->writeConverterState );
		delete d->readConverterSavedState;
		d->readConverterSavedState = 0;
		d->writeConverterState.flags |= QTextCodec::IgnoreHeader;
#endif
		return true;
	}

	// string
	if ( d->string && pos <= d->string->size() ) {
		d->stringOffset = int ( pos );
		return true;
	}
	return false;
}

/*!
    \since 4.2

    Returns the device position corresponding to the current position of the
    stream, or -1 if an error occurs (e.g., if there is no device or string,
    or if there's a device error).

    Because LTextStream is buffered, this function may have to
    seek the device to reconstruct a valid device position. This
    operation can be expensive, so you may want to avoid calling this
    function in a tight loop.

    \sa seek()
*/
qint64 LTextStream::pos() const
{
	Q_D ( const LTextStream );
	if ( d->device ) {
		// Cutoff
		if ( d->readBuffer.isEmpty() )
			return d->device->pos();
		if ( d->device->isSequential() )
			return 0;

		// Seek the device
		if ( !d->device->seek ( d->readBufferStartDevicePos ) )
			return qint64 ( -1 );

		// Reset the read buffer
		LTextStreamPrivate *thatd = const_cast<LTextStreamPrivate *> ( d );
		thatd->readBuffer.clear();

#ifndef QT_NO_TEXTCODEC
		thatd->restoreToSavedConverterState();
		if ( d->readBufferStartDevicePos == 0 )
			thatd->autoDetectUnicode = true;
#endif

		// Rewind the device to get to the current position Ensure that
		// readBufferOffset is unaffected by fillReadBuffer()
		int oldReadBufferOffset = d->readBufferOffset + d->readConverterSavedStateOffset;
		while ( d->readBuffer.size() < oldReadBufferOffset ) {
			if ( !thatd->fillReadBuffer ( 1 ) )
				return qint64 ( -1 );
		}
		thatd->readBufferOffset = oldReadBufferOffset;

		// Return the device position.
		return d->device->pos();
	}

	if ( d->string )
		return d->stringOffset;

	qWarning ( "LTextStream::pos: no device" );
	return qint64 ( -1 );
}

/*!
    Reads and discards whitespace from the stream until either a
    non-space character is detected, or until atEnd() returns
    true. This function is useful when reading a stream character by
    character.

    Whitespace characters are all characters for which
    QChar::isSpace() returns true.

    \sa operator>>()
*/
void LTextStream::skipWhiteSpace()
{
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( Q_VOID );
	d->scan ( 0, 0, 0, LTextStreamPrivate::NotSpace );
	d->consumeLastToken();
}

/*!
    Sets the current device to \a device. If a device has already been
    assigned, LTextStream will call flush() before the old device is
    replaced.

    \note This function resets locale to the default locale ('C')
    and codec to the default codec, QTextCodec::codecForLocale().

    \sa device(), setString()
*/
void LTextStream::setDevice ( QIODevice *device )
{
	Q_D ( LTextStream );
	flush();
	if ( d->deleteDevice ) {
#ifndef QT_NO_QOBJECT
		d->deviceClosedNotifier.disconnect();
#endif
		delete d->device;
		d->deleteDevice = false;
	}

	d->reset();
	d->status = Ok;
	d->device = device;
	d->resetReadBuffer();
#ifndef QT_NO_QOBJECT
	d->deviceClosedNotifier.setupDevice ( this, d->device );
#endif
}

/*!
    Returns the current device associated with the LTextStream,
    or 0 if no device has been assigned.

    \sa setDevice(), string()
*/
QIODevice *LTextStream::device() const
{
	Q_D ( const LTextStream );
	return d->device;
}

/*!
    Sets the current string to \a string, using the given \a
    openMode. If a device has already been assigned, LTextStream will
    call flush() before replacing it.

    \sa string(), setDevice()
*/
void LTextStream::setString ( QString *string, QIODevice::OpenMode openMode )
{
	Q_D ( LTextStream );
	flush();
	if ( d->deleteDevice ) {
#ifndef QT_NO_QOBJECT
		d->deviceClosedNotifier.disconnect();
		d->device->blockSignals ( true );
#endif
		delete d->device;
		d->deleteDevice = false;
	}

	d->reset();
	d->status = Ok;
	d->string = string;
	d->stringOpenMode = openMode;
}

/*!
    Returns the current string assigned to the LTextStream, or 0 if no
    string has been assigned.

    \sa setString(), device()
*/
QString *LTextStream::string() const
{
	Q_D ( const LTextStream );
	return d->string;
}

/*!
    Sets the field alignment to \a mode. When used together with
    setFieldWidth(), this function allows you to generate formatted
    output with text aligned to the left, to the right or center
    aligned.

    \sa fieldAlignment(), setFieldWidth()
*/
void LTextStream::setFieldAlignment ( FieldAlignment mode )
{
	Q_D ( LTextStream );
	d->fieldAlignment = mode;
}

/*!
    Returns the current field alignment.

    \sa setFieldAlignment(), fieldWidth()
*/
LTextStream::FieldAlignment LTextStream::fieldAlignment() const
{
	Q_D ( const LTextStream );
	return d->fieldAlignment;
}

/*!
    Sets the pad character to \a ch. The default value is the ASCII
    space character (' '), or QChar(0x20). This character is used to
    fill in the space in fields when generating text.

    Example:

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 5

    The string \c s contains:

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 6

    \sa padChar(), setFieldWidth()
*/
void LTextStream::setPadChar ( QChar ch )
{
	Q_D ( LTextStream );
	d->padChar = ch;
}

/*!
    Returns the current pad character.

    \sa setPadChar(), setFieldWidth()
*/
QChar LTextStream::padChar() const
{
	Q_D ( const LTextStream );
	return d->padChar;
}

/*!
    Sets the current field width to \a width. If \a width is 0 (the
    default), the field width is equal to the length of the generated
    text.

    \note The field width applies to every element appended to this
    stream after this function has been called (e.g., it also pads
    endl). This behavior is different from similar classes in the STL,
    where the field width only applies to the next element.

    \sa fieldWidth(), setPadChar()
*/
void LTextStream::setFieldWidth ( int width )
{
	Q_D ( LTextStream );
	d->fieldWidth = width;
}

/*!
    Returns the current field width.

    \sa setFieldWidth()
*/
int LTextStream::fieldWidth() const
{
	Q_D ( const LTextStream );
	return d->fieldWidth;
}

/*!
 *    Sets the current log level to \a level.
 */
void LTextStream::setLogLevel ( LogLevels level )
{
	Q_D ( LTextStream );
	d->logLevel = level;
}

/*!
 *    Returns the current log level.
 *
 *    \sa setLogLevel()
 */
LTextStream::LogLevels LTextStream::logLevel() const
{
	Q_D ( const LTextStream );
	return d->logLevel;
}

/*!
 *    Sets the current log destination to \a d.
 */
void LTextStream::setLogDestination ( LogDestination dst )
{
	Q_D ( LTextStream );
	d->logDestination = dst;
}

/*!
 *    Returns the current log destination.
 *
 *    \sa setLogDestination()
 */
LTextStream::LogDestination LTextStream::logDestination() const
{
	Q_D ( const LTextStream );
	return d->logDestination;
}


/*!
    Sets the current number flags to \a flags. \a flags is a set of
    flags from the NumberFlag enum, and describes options for
    formatting generated code (e.g., whether or not to always write
    the base or sign of a number).

    \sa numberFlags(), setIntegerBase(), setRealNumberNotation()
*/
void LTextStream::setNumberFlags ( NumberFlags flags )
{
	Q_D ( LTextStream );
	d->numberFlags = flags;
}

/*!
    Returns the current number flags.

    \sa setNumberFlags(), integerBase(), realNumberNotation()
*/
LTextStream::NumberFlags LTextStream::numberFlags() const
{
	Q_D ( const LTextStream );
	return d->numberFlags;
}

/*!
    Sets the base of integers to \a base, both for reading and for
    generating numbers. \a base can be either 2 (binary), 8 (octal),
    10 (decimal) or 16 (hexadecimal). If \a base is 0, LTextStream
    will attempt to detect the base by inspecting the data on the
    stream. When generating numbers, LTextStream assumes base is 10
    unless the base has been set explicitly.

    \sa integerBase(), QString::number(), setNumberFlags()
*/
void LTextStream::setIntegerBase ( int base )
{
	Q_D ( LTextStream );
	d->integerBase = base;
}

/*!
    Returns the current base of integers. 0 means that the base is
    detected when reading, or 10 (decimal) when generating numbers.

    \sa setIntegerBase(), QString::number(), numberFlags()
*/
int LTextStream::integerBase() const
{
	Q_D ( const LTextStream );
	return d->integerBase;
}

/*!
    Sets the real number notation to \a notation (SmartNotation,
    FixedNotation, ScientificNotation). When reading and generating
    numbers, LTextStream uses this value to detect the formatting of
    real numbers.

    \sa realNumberNotation(), setRealNumberPrecision(), setNumberFlags(), setIntegerBase()
*/
void LTextStream::setRealNumberNotation ( RealNumberNotation notation )
{
	Q_D ( LTextStream );
	d->realNumberNotation = notation;
}

/*!
    Returns the current real number notation.

    \sa setRealNumberNotation(), realNumberPrecision(), numberFlags(), integerBase()
*/
LTextStream::RealNumberNotation LTextStream::realNumberNotation() const
{
	Q_D ( const LTextStream );
	return d->realNumberNotation;
}

/*!
    Sets the precision of real numbers to \a precision. This value
    describes the number of fraction digits LTextStream should
    write when generating real numbers.

    The precision cannot be a negative value. The default value is 6.

    \sa realNumberPrecision(), setRealNumberNotation()
*/
void LTextStream::setRealNumberPrecision ( int precision )
{
	Q_D ( LTextStream );
	if ( precision < 0 ) {
		qWarning ( "LTextStream::setRealNumberPrecision: Invalid precision (%d)", precision );
		d->realNumberPrecision = 6;
		return;
	}
	d->realNumberPrecision = precision;
}

/*!
    Returns the current real number precision, or the number of fraction
    digits LTextStream will write when generating real numbers.

    \sa setRealNumberNotation(), realNumberNotation(), numberFlags(), integerBase()
*/
int LTextStream::realNumberPrecision() const
{
	Q_D ( const LTextStream );
	return d->realNumberPrecision;
}

/*!
    Returns the status of the text stream.

    \sa LTextStream::Status, setStatus(), resetStatus()
*/

LTextStream::Status LTextStream::status() const
{
	Q_D ( const LTextStream );
	return d->status;
}

/*!
    \since 4.1

    Resets the status of the text stream.

    \sa LTextStream::Status, status(), setStatus()
*/
void LTextStream::resetStatus()
{
	Q_D ( LTextStream );
	d->status = Ok;
}

/*!
    \since 4.1

    Sets the status of the text stream to the \a status given.

    \sa Status status() resetStatus()
*/
void LTextStream::setStatus ( Status status )
{
	Q_D ( LTextStream );
	if ( d->status == Ok )
		d->status = status;
}

/*!
    Returns true if there is no more data to be read from the
    LTextStream; otherwise returns false. This is similar to, but not
    the same as calling QIODevice::atEnd(), as LTextStream also takes
    into account its internal Unicode buffer.
*/
bool LTextStream::atEnd() const
{
	Q_D ( const LTextStream );
	CHECK_VALID_STREAM ( true );

	if ( d->string )
		return d->string->size() == d->stringOffset;
	return d->readBuffer.isEmpty() && d->device->atEnd();
}

/*!
    Reads the entire content of the stream, and returns it as a
    QString. Avoid this function when working on large files, as it
    will consume a significant amount of memory.

    Calling readLine() is better if you do not know how much data is
    available.

    \sa readLine()
*/
QString LTextStream::readAll()
{
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( QString() );

	return d->read ( INT_MAX );
}

/*!
    Reads one line of text from the stream, and returns it as a
    QString. The maximum allowed line length is set to \a maxlen. If
    the stream contains lines longer than this, then the lines will be
    split after \a maxlen characters and returned in parts.

    If \a maxlen is 0, the lines can be of any length. A common value
    for \a maxlen is 75.

    The returned line has no trailing end-of-line characters ("\\n"
    or "\\r\\n"), so calling QString::trimmed() is unnecessary.

    If the stream has read to the end of the file, readLine() will return a
    null QString. For strings, or for devices that support it, you can
    explicitly test for the end of the stream using atEnd().

    \sa readAll(), QIODevice::readLine()
*/
QString LTextStream::readLine ( qint64 maxlen )
{
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( QString() );

	const QChar *readPtr;
	int length;
	if ( !d->scan ( &readPtr, &length, int ( maxlen ), LTextStreamPrivate::EndOfLine ) )
		return QString();

	QString tmp = QString ( readPtr, length );
	d->consumeLastToken();
	return tmp;
}

/*!
    \since 4.1

    Reads at most \a maxlen characters from the stream, and returns the data
    read as a QString.

    \sa readAll(), readLine(), QIODevice::read()
*/
QString LTextStream::read ( qint64 maxlen )
{
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( QString() );

	if ( maxlen <= 0 )
		return QString::fromLatin1 ( "" );  // empty, not null

	return d->read ( int ( maxlen ) );
}

/*! \internal
*/
LTextStreamPrivate::NumberParsingStatus LTextStreamPrivate::getNumber ( qulonglong *ret )
{
	scan ( 0, 0, 0, NotSpace );
	consumeLastToken();

	// detect int encoding
	int base = integerBase;
	if ( base == 0 ) {
		QChar ch;
		if ( !getChar ( &ch ) )
			return npsInvalidPrefix;
		if ( ch == QLatin1Char ( '0' ) ) {
			QChar ch2;
			if ( !getChar ( &ch2 ) ) {
				// Result is the number 0
				*ret = 0;
				return npsOk;
			}
			ch2 = ch2.toLower();

			if ( ch2 == QLatin1Char ( 'x' ) ) {
				base = 16;
			} else if ( ch2 == QLatin1Char ( 'b' ) ) {
				base = 2;
			} else if ( ch2.isDigit() && ch2.digitValue() >= 0 && ch2.digitValue() <= 7 ) {
				base = 8;
			} else {
				base = 10;
			}
			ungetChar ( ch2 );
		} else if ( ch == locale.negativeSign() || ch == locale.positiveSign() || ch.isDigit() ) {
			base = 10;
		} else {
			ungetChar ( ch );
			return npsInvalidPrefix;
		}
		ungetChar ( ch );
		// State of the stream is now the same as on entry
		// (cursor is at prefix),
		// and local variable 'base' has been set appropriately.
	}

	qulonglong val = 0;
	switch ( base ) {
		case 2: {
				QChar pf1, pf2, dig;
				// Parse prefix '0b'
				if ( !getChar ( &pf1 ) || pf1 != QLatin1Char ( '0' ) )
					return npsInvalidPrefix;
				if ( !getChar ( &pf2 ) || pf2.toLower() != QLatin1Char ( 'b' ) )
					return npsInvalidPrefix;
				// Parse digits
				int ndigits = 0;
				while ( getChar ( &dig ) ) {
					int n = dig.toLower().unicode();
					if ( n == '0' || n == '1' ) {
						val <<= 1;
						val += n - '0';
					} else {
						ungetChar ( dig );
						break;
					}
					ndigits++;
				}
				if ( ndigits == 0 ) {
					// Unwind the prefix and abort
					ungetChar ( pf2 );
					ungetChar ( pf1 );
					return npsMissingDigit;
				}
				break;
			}
		case 8: {
				QChar pf, dig;
				// Parse prefix '0'
				if ( !getChar ( &pf ) || pf != QLatin1Char ( '0' ) )
					return npsInvalidPrefix;
				// Parse digits
				int ndigits = 0;
				while ( getChar ( &dig ) ) {
					int n = dig.toLower().unicode();
					if ( n >= '0' && n <= '7' ) {
						val *= 8;
						val += n - '0';
					} else {
						ungetChar ( dig );
						break;
					}
					ndigits++;
				}
				if ( ndigits == 0 ) {
					// Unwind the prefix and abort
					ungetChar ( pf );
					return npsMissingDigit;
				}
				break;
			}
		case 10: {
				// Parse sign (or first digit)
				QChar sign;
				int ndigits = 0;
				if ( !getChar ( &sign ) )
					return npsMissingDigit;
				if ( sign != locale.negativeSign() && sign != locale.positiveSign() ) {
					if ( !sign.isDigit() ) {
						ungetChar ( sign );
						return npsMissingDigit;
					}
					val += sign.digitValue();
					ndigits++;
				}
				// Parse digits
				QChar ch;
				while ( getChar ( &ch ) ) {
					if ( ch.isDigit() ) {
						val *= 10;
						val += ch.digitValue();
					} else if ( locale != QLocale::c() && ch == locale.groupSeparator() ) {
						continue;
					} else {
						ungetChar ( ch );
						break;
					}
					ndigits++;
				}
				if ( ndigits == 0 )
					return npsMissingDigit;
				if ( sign == locale.negativeSign() ) {
					qlonglong ival = qlonglong ( val );
					if ( ival > 0 )
						ival = -ival;
					val = qulonglong ( ival );
				}
				break;
			}
		case 16: {
				QChar pf1, pf2, dig;
				// Parse prefix ' 0x'
				if ( !getChar ( &pf1 ) || pf1 != QLatin1Char ( '0' ) )
					return npsInvalidPrefix;
				if ( !getChar ( &pf2 ) || pf2.toLower() != QLatin1Char ( 'x' ) )
					return npsInvalidPrefix;
				// Parse digits
				int ndigits = 0;
				while ( getChar ( &dig ) ) {
					int n = dig.toLower().unicode();
					if ( n >= '0' && n <= '9' ) {
						val <<= 4;
						val += n - '0';
					} else if ( n >= 'a' && n <= 'f' ) {
						val <<= 4;
						val += 10 + ( n - 'a' );
					} else {
						ungetChar ( dig );
						break;
					}
					ndigits++;
				}
				if ( ndigits == 0 ) {
					return npsMissingDigit;
				}
				break;
			}
		default:
			// Unsupported integerBase
			return npsInvalidPrefix;
	}

	if ( ret )
		*ret = val;
	return npsOk;
}

/*! \internal
    (hihi)
*/
bool LTextStreamPrivate::getReal ( double *f )
{
	// We use a table-driven FSM to parse floating point numbers
	// strtod() cannot be used directly since we may be reading from a
	// QIODevice.
	enum ParserState {
		Init = 0,
		Sign = 1,
		Mantissa = 2,
		Dot = 3,
		Abscissa = 4,
		ExpMark = 5,
		ExpSign = 6,
		Exponent = 7,
		Nan1 = 8,
		Nan2 = 9,
		Inf1 = 10,
		Inf2 = 11,
		NanInf = 12,
		Done = 13
	};
	enum InputToken {
		None = 0,
		InputSign = 1,
		InputDigit = 2,
		InputDot = 3,
		InputExp = 4,
		InputI = 5,
		InputN = 6,
		InputF = 7,
		InputA = 8,
		InputT = 9
	};

	static const uchar table[13][10] = {
		// None InputSign InputDigit InputDot InputExp InputI    InputN    InputF    InputA    InputT
		{ 0,    Sign,     Mantissa,  Dot,     0,       Inf1,     Nan1,     0,        0,        0      }, // 0  Init
		{ 0,    0,        Mantissa,  Dot,     0,       Inf1,     Nan1,     0,        0,        0      }, // 1  Sign
		{ Done, Done,     Mantissa,  Dot,     ExpMark, 0,        0,        0,        0,        0      }, // 2  Mantissa
		{ 0,    0,        Abscissa,  0,       0,       0,        0,        0,        0,        0      }, // 3  Dot
		{ Done, Done,     Abscissa,  Done,    ExpMark, 0,        0,        0,        0,        0      }, // 4  Abscissa
		{ 0,    ExpSign,  Exponent,  0,       0,       0,        0,        0,        0,        0      }, // 5  ExpMark
		{ 0,    0,        Exponent,  0,       0,       0,        0,        0,        0,        0      }, // 6  ExpSign
		{ Done, Done,     Exponent,  Done,    Done,    0,        0,        0,        0,        0      }, // 7  Exponent
		{ 0,    0,        0,         0,       0,       0,        0,        0,        Nan2,     0      }, // 8  Nan1
		{ 0,    0,        0,         0,       0,       0,        NanInf,   0,        0,        0      }, // 9  Nan2
		{ 0,    0,        0,         0,       0,       0,        Inf2,     0,        0,        0      }, // 10 Inf1
		{ 0,    0,        0,         0,       0,       0,        0,        NanInf,   0,        0      }, // 11 Inf2
		{ Done, 0,        0,         0,       0,       0,        0,        0,        0,        0      }, // 11 NanInf
	};

	ParserState state = Init;
	InputToken input = None;

	scan ( 0, 0, 0, NotSpace );
	consumeLastToken();

	const int BufferSize = 128;
	char buf[BufferSize];
	int i = 0;

	QChar c;
	while ( getChar ( &c ) ) {
		switch ( c.unicode() ) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				input = InputDigit;
				break;
			case 'i':
			case 'I':
				input = InputI;
				break;
			case 'n':
			case 'N':
				input = InputN;
				break;
			case 'f':
			case 'F':
				input = InputF;
				break;
			case 'a':
			case 'A':
				input = InputA;
				break;
			case 't':
			case 'T':
				input = InputT;
				break;
			default: {
					QChar lc = c.toLower();
					if ( lc == locale.decimalPoint().toLower() )
						input = InputDot;
					else if ( lc == locale.exponential().toLower() )
						input = InputExp;
					else if ( lc == locale.negativeSign().toLower()
					                || lc == locale.positiveSign().toLower() )
						input = InputSign;
					else if ( locale != QLocale::c() // backward-compatibility
					                && lc == locale.groupSeparator().toLower() )
						input = InputDigit; // well, it isn't a digit, but no one cares.
					else
						input = None;
				}
				break;
		}

		state = ParserState ( table[state][input] );

		if ( state == Init || state == Done || i > ( BufferSize - 5 ) ) {
			ungetChar ( c );
			if ( i > ( BufferSize - 5 ) ) { // ignore rest of digits
				while ( getChar ( &c ) ) {
					if ( !c.isDigit() ) {
						ungetChar ( c );
						break;
					}
				}
			}
			break;
		}

		buf[i++] = c.toLatin1();
	}

	if ( i == 0 )
		return false;
	if ( !f )
		return true;
	buf[i] = '\0';

	// backward-compatibility. Old implementation supported +nan/-nan
	// for some reason. QLocale only checks for lower-case
	// nan/+inf/-inf, so here we also check for uppercase and mixed
	// case versions.
	if ( !qstricmp ( buf, "nan" ) || !qstricmp ( buf, "+nan" ) || !qstricmp ( buf, "-nan" ) ) {
		*f = qSNaN();
		return true;
	} else if ( !qstricmp ( buf, "+inf" ) || !qstricmp ( buf, "inf" ) ) {
		*f = qInf();
		return true;
	} else if ( !qstricmp ( buf, "-inf" ) ) {
		*f = -qInf();
		return true;
	}
	bool ok;
	*f = locale.toDouble ( QString::fromLatin1 ( buf ), &ok );
	return ok;
}

/*!
    Reads a character from the stream and stores it in \a c. Returns a
    reference to the LTextStream, so several operators can be
    nested. Example:

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 7

    Whitespace is \e not skipped.
*/

LTextStream &LTextStream::operator>> ( QChar &c )
{
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->scan ( 0, 0, 0, LTextStreamPrivate::NotSpace );
	if ( !d->getChar ( &c ) )
		setStatus ( ReadPastEnd );
	return *this;
}

/*!
    \overload

    Reads a character from the stream and stores it in \a c. The
    character from the stream is converted to ISO-5589-1 before it is
    stored.

    \sa QChar::toLatin1()
*/
LTextStream &LTextStream::operator>> ( char &c )
{
	QChar ch;
	*this >> ch;
	c = ch.toLatin1();
	return *this;
}

/*!
    Reads an integer from the stream and stores it in \a i, then
    returns a reference to the LTextStream. The number is cast to
    the correct type before it is stored. If no number was detected on
    the stream, \a i is set to 0.

    By default, LTextStream will attempt to detect the base of the
    number using the following rules:

    \table
    \header \o Prefix                \o Base
    \row    \o "0b" or "0B"          \o 2 (binary)
    \row    \o "0" followed by "0-7" \o 8 (octal)
    \row    \o "0" otherwise         \o 10 (decimal)
    \row    \o "0x" or "0X"          \o 16 (hexadecimal)
    \row    \o "1" to "9"            \o 10 (decimal)
    \endtable

    By calling setIntegerBase(), you can specify the integer base
    explicitly. This will disable the auto-detection, and speed up
    LTextStream slightly.

    Leading whitespace is skipped.
*/
LTextStream &LTextStream::operator>> ( signed short &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( signed short );
}

/*!
    \overload

    Stores the integer in the unsigned short \a i.
*/
LTextStream &LTextStream::operator>> ( unsigned short &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( unsigned short );
}

/*!
    \overload

    Stores the integer in the signed int \a i.
*/
LTextStream &LTextStream::operator>> ( signed int &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( signed int );
}

/*!
    \overload

    Stores the integer in the unsigned int \a i.
*/
LTextStream &LTextStream::operator>> ( unsigned int &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( unsigned int );
}

/*!
    \overload

    Stores the integer in the signed long \a i.
*/
LTextStream &LTextStream::operator>> ( signed long &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( signed long );
}

/*!
    \overload

    Stores the integer in the unsigned long \a i.
*/
LTextStream &LTextStream::operator>> ( unsigned long &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( unsigned long );
}

/*!
    \overload

    Stores the integer in the qlonglong \a i.
*/
LTextStream &LTextStream::operator>> ( qlonglong &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( qlonglong );
}

/*!
    \overload

    Stores the integer in the qulonglong \a i.
*/
LTextStream &LTextStream::operator>> ( qulonglong &i )
{
	IMPLEMENT_STREAM_RIGHT_INT_OPERATOR ( qulonglong );
}

/*!
    Reads a real number from the stream and stores it in \a f, then
    returns a reference to the LTextStream. The number is cast to
    the correct type. If no real number is detect on the stream, \a f
    is set to 0.0.

    As a special exception, LTextStream allows the strings "nan" and "inf" to
    represent NAN and INF floats or doubles.

    Leading whitespace is skipped.
*/
LTextStream &LTextStream::operator>> ( float &f )
{
	IMPLEMENT_STREAM_RIGHT_REAL_OPERATOR ( float );
}

/*!
    \overload

    Stores the real number in the double \a f.
*/
LTextStream &LTextStream::operator>> ( double &f )
{
	IMPLEMENT_STREAM_RIGHT_REAL_OPERATOR ( double );
}

/*!
    Reads a word from the stream and stores it in \a str, then returns
    a reference to the stream. Words are separated by whitespace
    (i.e., all characters for which QChar::isSpace() returns true).

    Leading whitespace is skipped.
*/
LTextStream &LTextStream::operator>> ( QString &str )
{
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );

	str.clear();
	d->scan ( 0, 0, 0, LTextStreamPrivate::NotSpace );
	d->consumeLastToken();

	const QChar *ptr;
	int length;
	if ( !d->scan ( &ptr, &length, 0, LTextStreamPrivate::Space ) ) {
		setStatus ( ReadPastEnd );
		return *this;
	}

	str = QString ( ptr, length );
	d->consumeLastToken();
	return *this;
}

/*!
    \overload

    Converts the word to ISO-8859-1, then stores it in \a array.

    \sa QString::toLatin1()
*/
LTextStream &LTextStream::operator>> ( QByteArray &array )
{
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );

	array.clear();
	d->scan ( 0, 0, 0, LTextStreamPrivate::NotSpace );
	d->consumeLastToken();

	const QChar *ptr;
	int length;
	if ( !d->scan ( &ptr, &length, 0, LTextStreamPrivate::Space ) ) {
		setStatus ( ReadPastEnd );
		return *this;
	}

	for ( int i = 0; i < length; ++i )
		array += ptr[i].toLatin1();

	d->consumeLastToken();
	return *this;
}

/*!
    \overload

    Stores the word in \a c, terminated by a '\0' character. If no word is
    available, only the '\0' character is stored.

    Warning: Although convenient, this operator is dangerous and must
    be used with care. LTextStream assumes that \a c points to a
    buffer with enough space to hold the word. If the buffer is too
    small, your application may crash.

    If possible, use the QByteArray operator instead.
*/
LTextStream &LTextStream::operator>> ( char *c )
{
	Q_D ( LTextStream );
	*c = 0;
	CHECK_VALID_STREAM ( *this );
	d->scan ( 0, 0, 0, LTextStreamPrivate::NotSpace );
	d->consumeLastToken();

	const QChar *ptr;
	int length;
	if ( !d->scan ( &ptr, &length, 0, LTextStreamPrivate::Space ) ) {
		setStatus ( ReadPastEnd );
		return *this;
	}

	for ( int i = 0; i < length; ++i )
		*c++ = ptr[i].toLatin1();
	*c = '\0';
	d->consumeLastToken();
	return *this;
}

/*! \internal
 */
bool LTextStreamPrivate::putNumber ( qulonglong number, bool negative )
{
	QString result;

	unsigned flags = 0;
	if ( numberFlags & LTextStream::ShowBase )
		flags |= LLocalePrivate::ShowBase;
	if ( numberFlags & LTextStream::ForceSign )
		flags |= LLocalePrivate::AlwaysShowSign;
	if ( numberFlags & LTextStream::UppercaseBase )
		flags |= LLocalePrivate::UppercaseBase;
	if ( numberFlags & LTextStream::UppercaseDigits )
		flags |= LLocalePrivate::CapitalEorX;

	// add thousands group separators. For backward compatibility we
	// don't add a group separator for C locale.
	if ( locale != QLocale::c() )
		flags |= LLocalePrivate::ThousandsGroup;

//     const LLocalePrivate *dd = locale.d();
	int base = integerBase ? integerBase : 10;
	if ( negative && base == 10 ) {
		/*	    result = dd->longLongToString(-static_cast<qlonglong>(number), -1,
							  base, -1, flags);*/
		result = QString::number ( -static_cast<qlonglong> ( number ) );
	} else if ( negative ) {
		// Workaround for backward compatibility for writing negative
		// numbers in octal and hex:
		// LTextStream(result) << showbase << hex << -1 << oct << -1
		// should output: -0x1 -0b1
//         result = dd->unsLongLongToString(number, -1, base, -1, flags);
		result = QString::number ( number, base );
	} else {
//         result = dd->unsLongLongToString(number, -1, base, -1, flags);
		result = QString::number ( number, base );
		// workaround for backward compatibility - in octal form with
		// ShowBase flag set zero should be written as '00'
		if ( number == 0 && base == 8 && numberFlags & LTextStream::ShowBase
		                && result == QLatin1String ( "0" ) ) {
			result.prepend ( QLatin1Char ( '0' ) );
		}
	}
	return putString ( result, true );
}

/*!
    \internal
    \overload
*/
LTextStream &LTextStream::operator<< ( QBool b )
{
	return *this << bool ( b );
}

/*!
    Writes the character \a c to the stream, then returns a reference
    to the LTextStream.

    \sa setFieldWidth()
*/
LTextStream &LTextStream::operator<< ( QChar c )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putString ( QString ( c ) );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Converts \a c from ASCII to a QChar, then writes it to the stream.
*/
LTextStream &LTextStream::operator<< ( char c )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putString ( QString ( QChar::fromAscii ( c ) ) );
// 	m_mutex.unlock();
	return *this;
}

/*!
    Writes the integer number \a i to the stream, then returns a
    reference to the LTextStream. By default, the number is stored in
    decimal form, but you can also set the base by calling
    setIntegerBase().

    \sa setFieldWidth(), setNumberFlags()
*/
LTextStream &LTextStream::operator<< ( signed short i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( ( qulonglong ) qAbs ( qlonglong ( i ) ), i < 0 );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the unsigned short \a i to the stream.
*/
LTextStream &LTextStream::operator<< ( unsigned short i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( ( qulonglong ) i, false );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the signed int \a i to the stream.
*/
LTextStream &LTextStream::operator<< ( signed int i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( ( qulonglong ) qAbs ( qlonglong ( i ) ), i < 0 );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the unsigned int \a i to the stream.
*/
LTextStream &LTextStream::operator<< ( unsigned int i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( ( qulonglong ) i, false );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the signed long \a i to the stream.
*/
LTextStream &LTextStream::operator<< ( signed long i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( ( qulonglong ) qAbs ( qlonglong ( i ) ), i < 0 );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the unsigned long \a i to the stream.
*/
LTextStream &LTextStream::operator<< ( unsigned long i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( ( qulonglong ) i, false );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the qlonglong \a i to the stream.
*/
LTextStream &LTextStream::operator<< ( qlonglong i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( ( qulonglong ) qAbs ( i ), i < 0 );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the qulonglong \a i to the stream.
*/
LTextStream &LTextStream::operator<< ( qulonglong i )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putNumber ( i, false );
// 	m_mutex.unlock();
	return *this;
}

/*!
    Writes the real number \a f to the stream, then returns a
    reference to the LTextStream. By default, LTextStream stores it
    using SmartNotation, with up to 6 digits of precision. You can
    change the textual representation LTextStream will use for real
    numbers by calling setRealNumberNotation(),
    setRealNumberPrecision() and setNumberFlags().

    \sa setFieldWidth(), setRealNumberNotation(),
    setRealNumberPrecision(), setNumberFlags()
*/
LTextStream &LTextStream::operator<< ( float f )
{
	return *this << double ( f );
}

/*!
    \overload

    Writes the double \a f to the stream.
*/
LTextStream &LTextStream::operator<< ( double f )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );

	LLocalePrivate::DoubleForm form = LLocalePrivate::DFDecimal;
	switch ( realNumberNotation() ) {
		case FixedNotation:
			form = LLocalePrivate::DFDecimal;
			break;
		case ScientificNotation:
			form = LLocalePrivate::DFExponent;
			break;
		case SmartNotation:
			form = LLocalePrivate::DFSignificantDigits;
			break;
	}

	uint flags = 0;
	if ( numberFlags() & ShowBase )
		flags |= LLocalePrivate::ShowBase;
	if ( numberFlags() & ForceSign )
		flags |= LLocalePrivate::AlwaysShowSign;
	if ( numberFlags() & UppercaseBase )
		flags |= LLocalePrivate::UppercaseBase;
	if ( numberFlags() & UppercaseDigits )
		flags |= LLocalePrivate::CapitalEorX;
	if ( numberFlags() & ForcePoint )
		flags |= LLocalePrivate::Alternate;

//     const LLocalePrivate *dd = d->locale.d();
// 	QString num = dd->doubleToString(f, d->realNumberPrecision, form, -1, flags);
	QString num = QString::number ( f, 'g', d->realNumberPrecision );
	d->putString ( num, true );
// 	m_mutex.unlock();
	return *this;
}

/*!
    Writes the string \a string to the stream, and returns a reference
    to the LTextStream. The string is first encoded using the assigned
    codec (the default codec is QTextCodec::codecForLocale()) before
    it is written to the stream.

    \sa setFieldWidth(), setCodec()
*/
LTextStream &LTextStream::operator<< ( const QString &string )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putString ( string );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes \a array to the stream. The contents of \a array are
    converted with QString::fromAscii().
*/
LTextStream &LTextStream::operator<< ( const QByteArray &array )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putString ( QString::fromAscii ( array.constData(), array.length() ) );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes the constant string pointed to by \a string to the stream. \a
    string is assumed to be in ISO-8859-1 encoding. This operator
    is convenient when working with constant string data. Example:

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 8

    Warning: LTextStream assumes that \a string points to a string of
    text, terminated by a '\0' character. If there is no terminating
    '\0' character, your application may crash.
*/
LTextStream &LTextStream::operator<< ( const char *string )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	d->putString ( QLatin1String ( string ) );
// 	m_mutex.unlock();
	return *this;
}

/*!
    \overload

    Writes \a ptr to the stream as a hexadecimal number with a base.
*/

LTextStream &LTextStream::operator<< ( const void *ptr )
{
// 	m_mutex.lock();
	Q_D ( LTextStream );
	CHECK_VALID_STREAM ( *this );
	int oldBase = d->integerBase;
	NumberFlags oldFlags = d->numberFlags;
	d->integerBase = 16;
	d->numberFlags |= ShowBase;
	d->putNumber ( reinterpret_cast<quintptr> ( ptr ), false );
	d->integerBase = oldBase;
	d->numberFlags = oldFlags;
// 	m_mutex.unlock();
	return *this;
}

/*!
    \relates LTextStream

    Calls LTextStream::setIntegerBase(2) on \a stream and returns \a
    stream.

    \sa oct(), dec(), hex(), {LTextStream manipulators}
*/
LTextStream &bin ( LTextStream &stream )
{
	stream.setIntegerBase ( 2 );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setIntegerBase(8) on \a stream and returns \a
    stream.

    \sa bin(), dec(), hex(), {LTextStream manipulators}
*/
LTextStream &oct ( LTextStream &stream )
{
	stream.setIntegerBase ( 8 );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setIntegerBase(10) on \a stream and returns \a
    stream.

    \sa bin(), oct(), hex(), {LTextStream manipulators}
*/
LTextStream &dec ( LTextStream &stream )
{
	stream.setIntegerBase ( 10 );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setIntegerBase(16) on \a stream and returns \a
    stream.

    \note The hex modifier can only be used for writing to streams.
    \sa bin(), oct(), dec(), {LTextStream manipulators}
*/
LTextStream &hex ( LTextStream &stream )
{
	stream.setIntegerBase ( 16 );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() |
    LTextStream::ShowBase) on \a stream and returns \a stream.

    \sa noshowbase(), forcesign(), forcepoint(), {LTextStream manipulators}
*/
LTextStream &showbase ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() | LTextStream::ShowBase );
	return stream;
}

/*!
 *    \relates LTextStream
 *
 *    Calls LTextStream::setLogLevel error on \a stream and returns \a
 *    stream.
 */
LTextStream &logerror ( LTextStream &stream )
{
	stream.setLogLevel ( LTextStream::LogError );
	return stream;
}

/*!
 *    \relates LTextStream
 *
 *    Calls LTextStream::setLogLevel notice on \a stream and returns \a
 *    stream.
 */
LTextStream &lognotice ( LTextStream &stream )
{
	stream.setLogLevel ( LTextStream::LogNotice );
	return stream;
}

/*!
 *    \relates LTextStream
 *
 *    Calls LTextStream::setLogLevel info on \a stream and returns \a
 *    stream.
 */
LTextStream &loginfo ( LTextStream &stream )
{
	stream.setLogLevel ( LTextStream::LogInfo );
	return stream;
}

/*!
 *    \relates LTextStream
 *
 *    Calls LTextStream::setLogLevel debug on \a stream and returns \a
 *    stream.
 */
LTextStream &logdebug ( LTextStream &stream )
{
	stream.setLogLevel ( LTextStream::LogDebug );
	return stream;
}

/*!
 *    \relates LTextStream
 *
 *    Calls LTextStream::setLogDestination ToLogger on \a stream and returns \a
 *    stream.
 */
LTextStream &tologger ( LTextStream &stream )
{
	stream.setLogDestination ( LTextStream::ToLogger );
	return stream;
}

/*!
 *    \relates LTextStream
 *
 *    Calls LTextStream::setLogDestination ToMonitor on \a stream and returns \a
 *    stream.
 */
LTextStream &tomonitor ( LTextStream &stream )
{
	stream.setLogDestination ( LTextStream::ToMonitor );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() |
    LTextStream::ForceSign) on \a stream and returns \a stream.

    \sa noforcesign(), forcepoint(), showbase(), {LTextStream manipulators}
*/
LTextStream &forcesign ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() | LTextStream::ForceSign );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() |
    LTextStream::ForcePoint) on \a stream and returns \a stream.

    \sa noforcepoint(), forcesign(), showbase(), {LTextStream manipulators}
*/
LTextStream &forcepoint ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() | LTextStream::ForcePoint );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() &
    ~LTextStream::ShowBase) on \a stream and returns \a stream.

    \sa showbase(), noforcesign(), noforcepoint(), {LTextStream manipulators}
*/
LTextStream &noshowbase ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() &= ~LTextStream::ShowBase );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() &
    ~LTextStream::ForceSign) on \a stream and returns \a stream.

    \sa forcesign(), noforcepoint(), noshowbase(), {LTextStream manipulators}
*/
LTextStream &noforcesign ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() &= ~LTextStream::ForceSign );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() &
    ~LTextStream::ForcePoint) on \a stream and returns \a stream.

    \sa forcepoint(), noforcesign(), noshowbase(), {LTextStream manipulators}
*/
LTextStream &noforcepoint ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() &= ~LTextStream::ForcePoint );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() |
    LTextStream::UppercaseBase) on \a stream and returns \a stream.

    \sa lowercasebase(), uppercasedigits(), {LTextStream manipulators}
*/
LTextStream &uppercasebase ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() | LTextStream::UppercaseBase );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() |
    LTextStream::UppercaseDigits) on \a stream and returns \a stream.

    \sa lowercasedigits(), uppercasebase(), {LTextStream manipulators}
*/
LTextStream &uppercasedigits ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() | LTextStream::UppercaseDigits );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() &
    ~LTextStream::UppercaseBase) on \a stream and returns \a stream.

    \sa uppercasebase(), lowercasedigits(), {LTextStream manipulators}
*/
LTextStream &lowercasebase ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() & ~LTextStream::UppercaseBase );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setNumberFlags(LTextStream::numberFlags() &
    ~LTextStream::UppercaseDigits) on \a stream and returns \a stream.

    \sa uppercasedigits(), lowercasebase(), {LTextStream manipulators}
*/
LTextStream &lowercasedigits ( LTextStream &stream )
{
	stream.setNumberFlags ( stream.numberFlags() & ~LTextStream::UppercaseDigits );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setRealNumberNotation(LTextStream::FixedNotation)
    on \a stream and returns \a stream.

    \sa scientific(), {LTextStream manipulators}
*/
LTextStream &fixed ( LTextStream &stream )
{
	stream.setRealNumberNotation ( LTextStream::FixedNotation );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setRealNumberNotation(LTextStream::ScientificNotation)
    on \a stream and returns \a stream.

    \sa fixed(), {LTextStream manipulators}
*/
LTextStream &scientific ( LTextStream &stream )
{
	stream.setRealNumberNotation ( LTextStream::ScientificNotation );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setFieldAlignment(LTextStream::AlignLeft)
    on \a stream and returns \a stream.

    \sa right(), center(), {LTextStream manipulators}
*/
LTextStream &left ( LTextStream &stream )
{
	stream.setFieldAlignment ( LTextStream::AlignLeft );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setFieldAlignment(LTextStream::AlignRight)
    on \a stream and returns \a stream.

    \sa left(), center(), {LTextStream manipulators}
*/
LTextStream &right ( LTextStream &stream )
{
	stream.setFieldAlignment ( LTextStream::AlignRight );
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::setFieldAlignment(LTextStream::AlignCenter)
    on \a stream and returns \a stream.

    \sa left(), right(), {LTextStream manipulators}
*/
LTextStream &center ( LTextStream &stream )
{
	stream.setFieldAlignment ( LTextStream::AlignCenter );
	return stream;
}

/*!
    \relates LTextStream

    Writes '\n' to the \a stream and flushes the stream.

    Equivalent to

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 9

    Note: On Windows, all '\n' characters are written as '\r\n' if
    LTextStream's device or string is opened using the QIODevice::Text flag.

    \sa flush(), reset(), {LTextStream manipulators}
*/
LTextStream &endl ( LTextStream &stream )
{
	return stream << QLatin1Char ( '\n' ) << flush;
}

/*!
    \relates LTextStream

    Calls LTextStream::flush() on \a stream and returns \a stream.

    \sa endl(), reset(), {LTextStream manipulators}
*/
LTextStream &flush ( LTextStream &stream )
{
	stream.flush();
	return stream;
}

/*!
    \relates LTextStream

    Calls LTextStream::reset() on \a stream and returns \a stream.

    \sa flush(), {LTextStream manipulators}
*/
LTextStream &reset ( LTextStream &stream )
{
	stream.reset();
	return stream;
}

/*!
    \relates LTextStream

    Calls skipWhiteSpace() on \a stream and returns \a stream.

    \sa {LTextStream manipulators}
*/
LTextStream &ws ( LTextStream &stream )
{
	stream.skipWhiteSpace();
	return stream;
}

/*!
    \fn LTextStreamManipulator qSetFieldWidth(int width)
    \relates LTextStream

    Equivalent to LTextStream::setFieldWidth(\a width).
*/

/*!
    \fn LTextStreamManipulator qSetPadChar(QChar ch)
    \relates LTextStream

    Equivalent to LTextStream::setPadChar(\a ch).
*/

/*!
    \fn LTextStreamManipulator qSetRealNumberPrecision(int precision)
    \relates LTextStream

    Equivalent to LTextStream::setRealNumberPrecision(\a precision).
*/

#ifndef QT_NO_TEXTCODEC
/*!
    \relates LTextStream

    Toggles insertion of the Byte Order Mark on \a stream when LTextStream is
    used with a UTF codec.

    \sa LTextStream::setGenerateByteOrderMark(), {LTextStream manipulators}
*/
LTextStream &bom ( LTextStream &stream )
{
	stream.setGenerateByteOrderMark ( true );
	return stream;
}

/*!
    Sets the codec for this stream to \a codec. The codec is used for
    decoding any data that is read from the assigned device, and for
    encoding any data that is written. By default,
    QTextCodec::codecForLocale() is used, and automatic unicode
    detection is enabled.

    If LTextStream operates on a string, this function does nothing.

    \warning If you call this function while the text stream is reading
    from an open sequential socket, the internal buffer may still contain
    text decoded using the old codec.

    \sa codec(), setAutoDetectUnicode(), setLocale()
*/
void LTextStream::setCodec ( QTextCodec *codec )
{
	Q_D ( LTextStream );
	qint64 seekPos = -1;
	if ( !d->readBuffer.isEmpty() ) {
		if ( !d->device->isSequential() ) {
			seekPos = pos();
		}
	}
	d->codec = codec;
	if ( seekPos >= 0 && !d->readBuffer.isEmpty() )
		seek ( seekPos );
}

/*!
    Sets the codec for this stream to the QTextCodec for the encoding
    specified by \a codecName. Common values for \c codecName include
    "ISO 8859-1", "UTF-8", and "UTF-16". If the encoding isn't
    recognized, nothing happens.

    Example:

    \snippet doc/src/snippets/code/src_corelib_io_ltextstream.cpp 10

    \sa QTextCodec::codecForName(), setLocale()
*/
void LTextStream::setCodec ( const char *codecName )
{
	QTextCodec *codec = QTextCodec::codecForName ( codecName );
	if ( codec )
		setCodec ( codec );
}

/*!
    Returns the codec that is current assigned to the stream.

    \sa setCodec(), setAutoDetectUnicode(), locale()
*/
QTextCodec *LTextStream::codec() const
{
	Q_D ( const LTextStream );
	return d->codec;
}

/*!
    If \a enabled is true, LTextStream will attempt to detect Unicode
    encoding by peeking into the stream data to see if it can find the
    UTF-16 or UTF-32 BOM (Byte Order Mark). If this mark is found, LTextStream
    will replace the current codec with the UTF codec.

    This function can be used together with setCodec(). It is common
    to set the codec to UTF-8, and then enable UTF-16 detection.

    \sa autoDetectUnicode(), setCodec()
*/
void LTextStream::setAutoDetectUnicode ( bool enabled )
{
	Q_D ( LTextStream );
	d->autoDetectUnicode = enabled;
}

/*!
    Returns true if automatic Unicode detection is enabled; otherwise
    returns false.

    \sa setAutoDetectUnicode(), setCodec()
*/
bool LTextStream::autoDetectUnicode() const
{
	Q_D ( const LTextStream );
	return d->autoDetectUnicode;
}

/*!
    If \a generate is true and a UTF codec is used, LTextStream will insert
    the BOM (Byte Order Mark) before any data has been written to the
    device. If \a generate is false, no BOM will be inserted. This function
    must be called before any data is written. Otherwise, it does nothing.

    \sa generateByteOrderMark(), bom()
*/
void LTextStream::setGenerateByteOrderMark ( bool generate )
{
	Q_D ( LTextStream );
	if ( d->writeBuffer.isEmpty() ) {
		if ( generate )
			d->writeConverterState.flags &= ~QTextCodec::IgnoreHeader;
		else
			d->writeConverterState.flags |= QTextCodec::IgnoreHeader;
	}
}

/*!
    Returns true if LTextStream is set to generate the UTF BOM (Byte Order
    Mark) when using a UTF codec; otherwise returns false.

    \sa setGenerateByteOrderMark()
*/
bool LTextStream::generateByteOrderMark() const
{
	Q_D ( const LTextStream );
	return ( d->writeConverterState.flags & QTextCodec::IgnoreHeader ) == 0;
}

#endif

/*!
    \since 4.5

    Sets the locale for this stream to \a locale. The specified locale is
    used for conversions between numbers and their string representations.

    The default locale is C and it is a special case - the thousands
    group separator is not used for backward compatibility reasons.

    \sa locale()
*/
void LTextStream::setLocale ( const QLocale &locale )
{
	Q_D ( LTextStream );
	d->locale = locale;
}

/*!
    \since 4.5

    Returns the locale for this stream. The default locale is C.

    \sa setLocale()
*/
QLocale LTextStream::locale() const
{
	Q_D ( const LTextStream );
	return d->locale;
}


QT_END_NAMESPACE

// #ifndef QT_NO_QOBJECT
// #include "moc_ltextstream.cxx"
// #endif

