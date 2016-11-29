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

#ifndef XMLIO_H
#define XMLIO_H

#include <Qt/qobject.h>
#include <Qt/qtextstream.h>
#include <Qt/qfile.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <Qt/qdom.h>
#include <applogger.h>

#define XML_FILE_EXTENSION	".adams.xml"
#define XML_DOCTYPE_STRING	"ADAMS_XML_CONFIGURATION"

/** Questa classe implementa le necessarie funzionalit� di I/O
  * per ottenere la possibilit� di importare (ed esportare) il contenuto delle
  * classi di configurazione della Matrice in formato XML.
  *@author Piergiorgio Betti
  *@short XML I/O per la configurazione ADAMS.
  */

/**
@author Piergiorgio Betti <piergiorgio.betti@e-tech.net>
*/
class XmlIO : public QObject
{
	Q_OBJECT
public:
	XmlIO ( QObject *parent = 0 );
	~XmlIO();

	/** Imposta il nome del file da utilizzare per le operazioni di I/O */
	void setFileName ( const QString & fn, bool autoextension = false );
	/** Cancella l'intero documento e reinizializza il DocumentType */
	void clearAndInit();
	/** Importa il documento XML */
	bool load();
	bool load ( const QString & fn );
	/** Salva il documento su file */
	bool save();
	/** Aggiunge un nodo semplice */
	QDomElement addNodeSimple ( const QString & tag, const QString & value );
	QDomElement addNodeSimple ( const QString & tag, const unsigned long value );
	QDomElement addNodeSimple ( const QString & tag, const unsigned int value );
	QDomElement addNodeSimple ( const QString & tag, const double value );
	inline QDomElement addNodeSimple ( const QString & tag, const quint64 value ) {
		return addNodeSimple ( tag, ( unsigned long ) value );
	}
	/** Ritorna il nodo relativo al tag richiesto (come creato da @ref addNodeSimple)
	 */
	QString getNodeSimple ( const QString & tag );
	/** Azzera il documento */
	void xmlClear();

private:
	QString fileName;		// Working file name
	QFile ief;

	QDomDocument * xmld;		// Root document tree
	QDomElement root;		// Root node

	/** Apertura del file utilizzato per le operazioni di I/O */
	bool open ( QIODevice::OpenMode mode = QIODevice::ReadWrite | QIODevice::Text );
};

#endif
