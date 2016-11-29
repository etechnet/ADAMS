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

#ifndef LTEXTSTREAM_H
#define LTEXTSTREAM_H

#include <QtCore/qiodevice.h>
#include <QtCore/qstring.h>
#include <QtCore/qchar.h>
#include <QtCore/qlocale.h>
#include <QtCore/qscopedpointer.h>
#include <QtCore/qobject.h>
#include "ssm_logger_if.h"

#include <stdio.h>

#ifdef Status
#error ltextstream.h must be included before any header file that defines Status
#endif

QT_BEGIN_HEADER

QT_BEGIN_NAMESPACE

// QT_MODULE ( Core )

class QTextCodec;
class QTextDecoder;

class LTextStreamPrivate;
class Q_CORE_EXPORT LTextStream : public QObject                                // text stream class
{
	Q_OBJECT
	Q_DECLARE_PRIVATE ( LTextStream )

public:
	enum RealNumberNotation {
	        SmartNotation,
	        FixedNotation,
	        ScientificNotation
	};
	enum FieldAlignment {
	        AlignLeft,
	        AlignRight,
	        AlignCenter,
	        AlignAccountingStyle
	};
	enum Status {
	        Ok,
	        ReadPastEnd,
	        ReadCorruptData
	};
	enum NumberFlag {
	        ShowBase = 0x1,
	        ForcePoint = 0x2,
	        ForceSign = 0x4,
	        UppercaseBase = 0x8,
	        UppercaseDigits = 0x10
	};
	Q_DECLARE_FLAGS ( NumberFlags, NumberFlag )

	enum LogLevels {
	        LogError,
	        LogNotice,
	        LogInfo,
	        LogDebug
	};

	enum LogDestination {
	        ToLogger,
	        ToMonitor
	};

	LTextStream();
	explicit LTextStream ( QIODevice *device );
	explicit LTextStream ( FILE *fileHandle, QIODevice::OpenMode openMode = QIODevice::ReadWrite );
	explicit LTextStream ( QString *string, QIODevice::OpenMode openMode = QIODevice::ReadWrite );
	explicit LTextStream ( const QString & app, QString *string, net::etech::adams::ssm_logger * ssm_logger_i, QIODevice::OpenMode openMode = QIODevice::ReadWrite );
	explicit LTextStream ( QByteArray *array, QIODevice::OpenMode openMode = QIODevice::ReadWrite );
	explicit LTextStream ( const QByteArray &array, QIODevice::OpenMode openMode = QIODevice::ReadOnly );
	virtual ~LTextStream();

#ifndef QT_NO_TEXTCODEC
	void setCodec ( QTextCodec *codec );
	void setCodec ( const char *codecName );
	QTextCodec *codec() const;
	void setAutoDetectUnicode ( bool enabled );
	bool autoDetectUnicode() const;
	void setGenerateByteOrderMark ( bool generate );
	bool generateByteOrderMark() const;
#endif

	void setLocale ( const QLocale &locale );
	QLocale locale() const;

	void setDevice ( QIODevice *device );
	QIODevice *device() const;

	void setString ( QString *string, QIODevice::OpenMode openMode = QIODevice::ReadWrite );
	QString *string() const;

	Status status() const;
	void setStatus ( Status status );
	void resetStatus();

	bool atEnd() const;
	void reset();
	void flush();
	void toSSMLogger ( );
	bool seek ( qint64 pos );
	qint64 pos() const;

	static void setReferenceLogLevel ( LTextStream::LogLevels lev );
	static LogLevels getReferenceLogLevel();
	static void setSSMLogger ( net::etech::adams::ssm_logger * ssm_logger_i );
	static net::etech::adams::ssm_logger * getSSMLogger();
	static void setApplicationName ( const QString & app_name );
	static const QString &  getApplicationName();

	void skipWhiteSpace();

	QString readLine ( qint64 maxlen = 0 );
	QString readAll();
	QString read ( qint64 maxlen );

	void setFieldAlignment ( FieldAlignment alignment );
	FieldAlignment fieldAlignment() const;

	void setPadChar ( QChar ch );
	QChar padChar() const;

	void setFieldWidth ( int width );
	int fieldWidth() const;

	void setNumberFlags ( NumberFlags flags );
	NumberFlags numberFlags() const;

	void setIntegerBase ( int base );
	int integerBase() const;

	void setLogLevel ( LogLevels level );
	LogLevels logLevel() const;

	void setLogDestination ( LogDestination dst );
	LogDestination logDestination() const;

	void setRealNumberNotation ( RealNumberNotation notation );
	RealNumberNotation realNumberNotation() const;

	void setRealNumberPrecision ( int precision );
	int realNumberPrecision() const;

	LTextStream &operator>> ( QChar &ch );
	LTextStream &operator>> ( char &ch );
	LTextStream &operator>> ( signed short &i );
	LTextStream &operator>> ( unsigned short &i );
	LTextStream &operator>> ( signed int &i );
	LTextStream &operator>> ( unsigned int &i );
	LTextStream &operator>> ( signed long &i );
	LTextStream &operator>> ( unsigned long &i );
	LTextStream &operator>> ( qlonglong &i );
	LTextStream &operator>> ( qulonglong &i );
	LTextStream &operator>> ( float &f );
	LTextStream &operator>> ( double &f );
	LTextStream &operator>> ( QString &s );
	LTextStream &operator>> ( QByteArray &array );
	LTextStream &operator>> ( char *c );

	LTextStream &operator<< ( QBool b );
	LTextStream &operator<< ( QChar ch );
	LTextStream &operator<< ( char ch );
	LTextStream &operator<< ( signed short i );
	LTextStream &operator<< ( unsigned short i );
	LTextStream &operator<< ( signed int i );
	LTextStream &operator<< ( unsigned int i );
	LTextStream &operator<< ( signed long i );
	LTextStream &operator<< ( unsigned long i );
	LTextStream &operator<< ( qlonglong i );
	LTextStream &operator<< ( qulonglong i );
	LTextStream &operator<< ( float f );
	LTextStream &operator<< ( double f );
	LTextStream &operator<< ( const QString &s );
	LTextStream &operator<< ( const QByteArray &array );
	LTextStream &operator<< ( const char *c );
	LTextStream &operator<< ( const void *ptr );


private:

	Q_DISABLE_COPY ( LTextStream )

	QScopedPointer<LTextStreamPrivate> d_ptr;

// signals:
// 	void streamFlushed(int level, int destination);
};

Q_DECLARE_OPERATORS_FOR_FLAGS ( LTextStream::NumberFlags )

/*****************************************************************************
  LTextStream manipulators
 *****************************************************************************/

typedef LTextStream & ( *LTextStreamFunction ) ( LTextStream & ); // manipulator function
typedef void ( LTextStream::*LTSMFI ) ( int ); // manipulator w/int argument
typedef void ( LTextStream::*LTSMFC ) ( QChar ); // manipulator w/QChar argument

class Q_CORE_EXPORT LTextStreamManipulator
{
public:
	LTextStreamManipulator ( LTSMFI m, int a ) {
		mf = m;
		mc = 0;
		arg = a;
	}
	LTextStreamManipulator ( LTSMFC m, QChar c ) {
		mf = 0;
		mc = m;
		ch = c;
		arg = -1;
	}
	void exec ( LTextStream &s ) {
		if ( mf ) {
			( s.*mf ) ( arg );
		}
		else {
			( s.*mc ) ( ch );
		}
	}

private:
	LTSMFI mf;                                        // LTextStream member function
	LTSMFC mc;                                        // LTextStream member function
	int arg;                                          // member function argument
	QChar ch;
};

inline LTextStream &operator>> ( LTextStream &s, LTextStreamFunction f )
{
	return ( *f ) ( s );
}

inline LTextStream &operator<< ( LTextStream &s, LTextStreamFunction f )
{
	return ( *f ) ( s );
}

inline LTextStream &operator<< ( LTextStream &s, LTextStreamManipulator m )
{
	m.exec ( s );
	return s;
}

Q_CORE_EXPORT LTextStream &bin ( LTextStream &s );
Q_CORE_EXPORT LTextStream &oct ( LTextStream &s );
Q_CORE_EXPORT LTextStream &dec ( LTextStream &s );
Q_CORE_EXPORT LTextStream &hex ( LTextStream &s );

Q_CORE_EXPORT LTextStream &showbase ( LTextStream &s );
Q_CORE_EXPORT LTextStream &forcesign ( LTextStream &s );
Q_CORE_EXPORT LTextStream &forcepoint ( LTextStream &s );
Q_CORE_EXPORT LTextStream &noshowbase ( LTextStream &s );
Q_CORE_EXPORT LTextStream &noforcesign ( LTextStream &s );
Q_CORE_EXPORT LTextStream &noforcepoint ( LTextStream &s );

Q_CORE_EXPORT LTextStream &uppercasebase ( LTextStream &s );
Q_CORE_EXPORT LTextStream &uppercasedigits ( LTextStream &s );
Q_CORE_EXPORT LTextStream &lowercasebase ( LTextStream &s );
Q_CORE_EXPORT LTextStream &lowercasedigits ( LTextStream &s );

Q_CORE_EXPORT LTextStream &fixed ( LTextStream &s );
Q_CORE_EXPORT LTextStream &scientific ( LTextStream &s );

Q_CORE_EXPORT LTextStream &left ( LTextStream &s );
Q_CORE_EXPORT LTextStream &right ( LTextStream &s );
Q_CORE_EXPORT LTextStream &center ( LTextStream &s );

Q_CORE_EXPORT LTextStream &endl ( LTextStream &s );
Q_CORE_EXPORT LTextStream &flush ( LTextStream &s );
Q_CORE_EXPORT LTextStream &reset ( LTextStream &s );

Q_CORE_EXPORT LTextStream &bom ( LTextStream &s );

Q_CORE_EXPORT LTextStream &ws ( LTextStream &s );

Q_CORE_EXPORT LTextStream &logerror ( LTextStream &s );
Q_CORE_EXPORT LTextStream &lognotice ( LTextStream &s );
Q_CORE_EXPORT LTextStream &loginfo ( LTextStream &s );
Q_CORE_EXPORT LTextStream &logdebug ( LTextStream &s );

Q_CORE_EXPORT LTextStream &tomonitor ( LTextStream &s );
Q_CORE_EXPORT LTextStream &tologger ( LTextStream &s );

inline LTextStreamManipulator lSetFieldWidth ( int width )
{
	LTSMFI func = &LTextStream::setFieldWidth;
	return LTextStreamManipulator ( func, width );
}

inline LTextStreamManipulator lSetPadChar ( QChar ch )
{
	LTSMFC func = &LTextStream::setPadChar;
	return LTextStreamManipulator ( func, ch );
}

inline LTextStreamManipulator lSetRealNumberPrecision ( int precision )
{
	LTSMFI func = &LTextStream::setRealNumberPrecision;
	return LTextStreamManipulator ( func, precision );
}

#ifndef QT_NO_QOBJECT
class LDeviceClosedNotifier : public QObject
{
	Q_OBJECT
public:
	inline LDeviceClosedNotifier() { }

	inline void setupDevice ( LTextStream *stream, QIODevice *device ) {
		disconnect();
		if ( device )
			connect ( device, SIGNAL ( aboutToClose() ), this, SLOT ( flushStream() ) );
		this->stream = stream;
	}

public Q_SLOTS:
	inline void flushStream() {
		stream->flush();
	}

private:
	LTextStream *stream;
};
#endif


QT_END_NAMESPACE

QT_END_HEADER

#endif // LTEXTSTREAM_H
