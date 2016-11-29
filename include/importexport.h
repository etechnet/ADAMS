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

#ifndef IMPORTEXPORT_H
#define IMPORTEXPORT_H

#include <Qt/qobject.h>
#include <Qt/qtextstream.h>
#include <Qt/qfile.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qhash.h>
#include <Qt/qdatetime.h>

#define IMP_EXP_FILE_EXTENSION	".cnfbackup"
#define ERR_FILTER_MSG		"Warning: Added a field which contains forbidden characters !!"

/** Questa classe implementa le necessarie funzionalit� di I/O
  * per ottenere la possibilit� di importare (ed esportare) il contenuto delle
  * classi di configurazione della Matrice in formato ASCII.
  * Lo scopo finale � quello di ottenere sia la possibilt� di effettuare un backup generico
  * della configurazione, sia quello di consentire un facile aggiornamento di una configurazione attraverso
  * (eventuali) releases successive della matrice e del suo formato
  * di configurazione.
  *@author Piergiorgio Betti
  *@short Import/Export della configurazione.
  */

class ImportExport : public QObject, QTextStream
{
	Q_OBJECT
public:
	ImportExport ( QObject *parent = 0 );
	~ImportExport();

	/** Imposta il nome del file da utilizzare per le operazioni di I/O */
	void setFileName ( const QString & fn, bool autoextension = false );
	/** Apertura del file utilizzato per l'i/o */
	bool open ( QIODevice::OpenMode mode );
	/** Imposta nell'immagine del record in export il tag relativo al campo successivo terminato da un segno di '=' */
	inline void setWriteTag ( const QString & atag ) {
		writeRecord << atag + QString ( "=" );
	}
	/** Inizializza e resetta l'immagine del record da utilizzare per le operazioni di export */
	void initWriteRecord ( const QString & recordIdTag );
	/** Emette un warning nel caso la stringa contenga caratteri riservati */
	inline QString filterString ( const QString & str ) {
		if ( str.contains ( '|' ) || str.contains ( '~' ) ) errFilterMsg();
		return str;
	}
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record */
	inline void addWriteRecord ( const char * arg ) {
		writeRecord << filterString ( QString ( arg ) ) + QString ( "|" );
	}
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	inline void addWriteRecord ( int arg ) {
		writeRecord << QString::number ( arg ) + QString ( "|" );
	}
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	inline void addWriteRecord ( unsigned long arg ) {
		writeRecord << QString::number ( arg ) + QString ( "|" );
	}
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	inline void addWriteRecord ( long arg ) {
		writeRecord << QString::number ( arg ) + QString ( "|" );
	}
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	inline void addWriteRecord ( bool arg ) {
		writeRecord << QString::number ( arg ) + QString ( "|" );
	}
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	inline void addWriteRecord ( double arg ) {
		writeRecord << QString::number ( arg ) + QString ( "|" );
	}
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	void addWriteRecord ( const QStringList & arg );
	/** Aggiunge un campo di tipo non-array nell'immagine interna del record. Overload del method precedente differisce
	 * per il tipo di argomento
	 */
	void addWriteRecord ( const QList<int> & arg );
	/** Aggiunge un campo di tipo array nell'immagine interna del record */
	void addArrayWriteRecord ( char * arg, unsigned int ar_size, unsigned int el_size = sizeof ( char ), bool trueArray = false );
	/** Aggiunge un campo di tipo array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	void addArrayWriteRecord ( int * arg, unsigned int size );
	/** Aggiunge un campo di tipo array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	void addArrayWriteRecord ( int * arg, unsigned int ar_size, unsigned int el_num );

	/** Aggiunge un campo di tipo array nell'immagine interna del record. Overload del method precedente differisce
	  * per il tipo di argomento
	  */
	void addArrayWriteRecord ( double * arg, unsigned int size );
	/** Sincronizza il file in export con l'immagine del record in write */
	bool flushWriteRecord();
	/** Esegue la lettura (l'import) da file. Viene emesso un signal @ref recordReady() a fronte di ogni lettura eseguita
	  * con successo.
	  */
	bool import();
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	inline int getIntToken ( const QString & token ) {
		return extractToken ( token ).toInt();
	}
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	inline long getLongToken ( const QString & token ) {
		return extractToken ( token ).toLong();
	}
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	inline long getShortToken ( const QString & token ) {
		return extractToken ( token ).toShort();
	}
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	inline double getDoubleToken ( const QString & token ) {
		return extractToken ( token ).toDouble();
	}
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	inline bool getBoolToken ( const QString & token ) {
		return ( bool ) extractToken ( token ).toInt();
	}
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	inline void getStrToken ( char * dest, const QString & token, int size ) {
		qstrcpy ( dest, qPrintable ( extractToken ( token ).mid ( 0, size - 1 ) ) );
	}
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	inline QString getQStringToken ( const QString & token ) {
		return extractToken ( token );
	}
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	void getDoubleArrToken ( double * dest, const QString & token, unsigned int size );
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	void getIntArrToken ( int * dest, const QString & token, unsigned int size );
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	void getIntArrToken ( int * dest, const QString & token, unsigned int size, unsigned int el_num );
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	void getStrArrToken ( char * dest, const QString & token, unsigned int ar_size, unsigned int el_size, bool trueArray = false );
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	void getQStringListToken ( QStringList & dest, const QString & token );
	/** Ricerca un token dal buffer dati corrente e ne ritorna il valore secondo il tipo richiesto */
	void getQListIntToken ( QList<int> & dest, const QString & token );
private:
	QString fileName;
	QFile ief;
	bool opened;
	QStringList writeRecord;
	QStringList readBuffer;

	/** Questo method estrae un token dal buffer dati corrente */
	QString extractToken ( const QString & token );
	/** Errore su filtro ingresso (messaggio) */
	void errFilterMsg();

signals: // Signals
	/** Questo signal viene emesso a fronte di ogni record letto dalla routine di import.
	  * Il valore fornito in argomento consiste nel tag identificativo del record stesso.
	  */
	void recordReady ( QString &, ImportExport * );
};

#endif
