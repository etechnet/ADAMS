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

#include <xmlio.h>
#include <applogger.h>
#include <Qt/qtextstream.h>

XmlIO::XmlIO ( QObject *parent )
		: QObject ( parent ),
		xmld ( 0 )
{
	clearAndInit();
}

XmlIO::~XmlIO()
{
	if ( xmld )
		delete xmld;
}

/** Imposta il nome del file da utilizzare per le operazioni di I/O */

void XmlIO::setFileName ( const QString & fn, bool autoextension )
{
	fileName = fn;
	if ( autoextension && !fileName.contains ( '.' ) )
		fileName += XML_FILE_EXTENSION;
	ief.setFileName ( fileName );
}

// opens the file to be used for i/o

bool XmlIO::open ( QIODevice::OpenMode mode )
{
	if ( ief.open ( mode ) ) {
		return true;
	}
	else
		return false;
}

bool XmlIO::load()
{
	if ( !open() )
		return false;

	if ( !xmld->setContent ( &ief ) ) {
		ief.close();
		clearAndInit();
		return false;
	}

	ief.close();
	return true;
}

bool XmlIO::load ( const QString& fn )
{
	setFileName ( fn );
	return load();
}


bool XmlIO::save()
{
	lout3 << "XMLDOC:" << endl << xmld->toString() << endl;

	if ( !open() )
		return false;
	QTextStream docout ( &ief );
	xmld->save ( docout, 3 );
	return true;
}


QDomElement XmlIO::addNodeSimple ( const QString & tag, const QString & value )
{
	QDomElement elem = xmld->createElement ( tag );
	elem.setAttribute ( tag, value );
	root.appendChild ( elem );
	return elem;
}

QDomElement XmlIO::addNodeSimple ( const QString& tag, const unsigned long value )
{
	QDomElement elem = xmld->createElement ( tag );
	elem.setAttribute ( tag, ( quint64 ) value );
	root.appendChild ( elem );
	lout3 << "xml addNodeSimple:" << xmld->toString() << endl;
	return elem;
}

QDomElement XmlIO::addNodeSimple ( const QString& tag, const unsigned int value )
{
	QDomElement elem = xmld->createElement ( tag );
	elem.setAttribute ( tag, ( quint64 ) value );
	root.appendChild ( elem );
	return elem;
}

QDomElement XmlIO::addNodeSimple ( const QString& tag, const double value )
{
	QDomElement elem = xmld->createElement ( tag );
	elem.setAttribute ( tag, value );
	root.appendChild ( elem );
	return elem;
}

QString XmlIO::getNodeSimple ( const QString& tag )
{
	QDomElement docElem = xmld->documentElement();
	QDomNodeList nodeList = docElem.elementsByTagName ( tag );
	lout3 << "XmlIO::getNodeSimple nodeList.count()=" << nodeList.count() << " tag=[" << tag << "]" << endl;
// 	QDomNode n = docElem.firstChild();
// 	while ( !n.isNull() ) {
// 		QDomElement e = n.toElement(); // try to convert the node to an element.
// 		if ( !e.isNull() ) {
// 			lout <<  e.tagName() << endl; // the node really is an element.
// 		}
// 		n = n.nextSibling();
// 	}

	if ( nodeList.count() > 0 ) {
		return nodeList.at ( 0 ).attributes().namedItem ( tag ).nodeValue();
	}

	return QString ( "" );
}


/*!
    \fn XmlIO::clearAndInit()
 */
void XmlIO::clearAndInit()
{
	lout3 << "clearAndInit" << endl;
	if ( xmld )
		delete xmld;
	xmld = new QDomDocument ( XML_DOCTYPE_STRING );

	root = xmld->createElement(XML_DOCTYPE_STRING);
	xmld->appendChild(root);
	lout3 << "xml clear:" << xmld->toString() << endl;
}

void XmlIO::xmlClear()
{
	lout3 << "xmlclear" << endl;
	if ( xmld ) {
		xmld->clear();
		root = xmld->createElement ( XML_DOCTYPE_STRING );
		xmld->appendChild ( root );
		lout3 << "xml clear:" << xmld->toString() << endl;
	}
}
