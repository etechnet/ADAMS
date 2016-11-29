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
                          httpimpl.h  -  description
                             -------------------
    begin                : Fri Mar 30 2001
    copyright            : (C) 2001 by Piergiorgio Betti
    email                : piergiorgio.betti@e-tech.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#ifndef HTTPIMPL_H
#define HTTPIMPL_H


/**
*Classe utilizzata per stabilire una connessione con il Web Server
*per inviare il report via HTTP.
*/

#include <qstring.h>
#include <qregexp.h>
#include <qpixmap.h>
#include <qstringlist.h>
#include <stdlib.h>
#include <configparser.h>
#include <socket.h>

#define HTTP_DEFAULT_PORT		80
#define HTTP_DEFAULT_METHOD		"GET"
#define MAX_LINE_LENGTH			512
#define FAKE_PAGE_LENGHT		2147483647
#define ADAMS_SOFTWARE_VERSION_STRING 	"5.0"

using namespace std;

class HttpImpl : public SimpleTCPStream {
public:
	/** Costruttore della classe.
	*/
	HttpImpl(int idUser,int idJob,int isScheduled , const char *isDecription);
	/** Distruttore della classe.
	*/
	~HttpImpl();
	/** Ritorna lo stato riconoscuto (true se valido) per l'ultima URL assegnata
	* per le operazioni.
	*/
	inline bool urlOk() { return url_status; }
	/** Assegna il method HTTP da utilizzare nelle successive operazioni verso il server.
	* Il method deve essere un tag valido in protocollo HTTP. Se il tag non viene esplicitamente
	* assegnato con una chiamata a questo method viene utilizzato per default "GET".
	*/
	inline void setHttpMethod(const char *metd = 0) { if (metd) httpmethod = QString(metd); }
	/** Ritorna il method impostato per le operazioni verso il server HTTP. */
	inline const QString * getHttpMethod() { return &httpmethod; }
	/** Stabilisce la connessione al server HTTP utilizzando i parametri impostatati.
	*@return True per una corretta apertura del socket
	*/
	bool openConnection();
	/** Stabilisce la connessione al server HTTP utilizzando i parametri impostatati.
	* L'inizializzazione comprende la parte di handshake HTTP 1.0.
	*@return True per una corretta apertura del socket
	*/
	bool initialize(const QString & urlref, bool onsubpath, bool justSendDesc = false);
	/** Invia (in modalit� asincrona) la stringa in argomento al server HTTP. */
	void send(const QString & snd);
	/** Invia (in modalit� asincrona) la stringa in argomento al server HTTP. La stringa viene terminata con CR+LF. */
	void sendString(const QString & snd);
	/** Preleva una stringa dal buffer di ricezione */
	const char *recv();

	/** Metodo per la codifica di una pixmap in formato JPEG ed invio verso server HTTP
	*@param img_pxm Pixmap da processare
	*@param urlref URL completa dove indirizzare la JPEG
	*@param onsubpath Switch per l'utilizzo di una URL alternativa
	*/
	bool sendJpegImage2Web(const QPixmap & img_pxm, const char *urlref, bool onsubpath);
	/** Metodo per l'invio di una pagina html (defnita in una @ref QStrList) su server HTTP
	*@param strlist Immagine della pagina html
	*@param urlref URL completa dove indirizzare la JPEG
	*@param onsubpath Switch per l'utilizzo di una URL alternativa
	*/
	bool sendHtmlPage2Web(QStringList & strlist, const char *urlref, bool onsubpath);
	/** Invia un breve file su WEB. Il file contiene una descrizione del report definita
	*dall'utente e viene utilizzato da apposite CGI su server HTTP STS.
	*/
	bool sendReportDescription2Web(const QString & repourl,const QString & Description, bool onsubpath);
	/** Chiude lo stream */
	void close() { endStream(); }
private:
	QString url;
	QString urlhost;
	QString urlpath;
	int hostport;
	QString httpmethod;
	bool url_status;
	char line_buffer[MAX_LINE_LENGTH + 1];
	const char *web_password;
	QString httpUrlPath, httpUrlSubPath;
	HttpImpl *WebCon;
	QString report_description;
	bool scheduledReport;
	QString http_prefix_string;
	/** Viene eseguito il parsing di una URL per scomporne nome host, porta (eventuale) e path */
	void urlParse();						// examine and decompose user url

	void initiateDoc(QString);
	void showConnectError(int errcode);
};
#endif




