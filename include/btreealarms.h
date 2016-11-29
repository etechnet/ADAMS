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

#ifndef BTREEALARMS_H
#define BTREEALARMS_H

#include <nodo.h>
#include <adams_limits.h>
#include <applogger.h>
#include <common_alarm_typedef.h>
#include <ets_alarm_resource.h>

#include <Qt/qmutex.h>
#include <Qt/qlist.h>
#include <Qt/qmap.h>
#include <Qt/qdatetime.h>
#include <Qt/qpair.h>
#include <Qt/qhash.h>
#include <Qt/qstring.h>

class QXmlStreamReader;

#define MAX_ELEMENT_CODE_LEN			21	/* Lunghezza max codice elemento (alfanumerico) */
#define MAX_ALLOWED_QC				20	// Max Quality Counters
#define RT_STATUS_BASENAME			".rt_alarmdata_"

// #define MAX_TICKET_CODES        		50
// #define TICKET_CODES_LEN        		10      /* !!! COME DA TABELLA ORACLE !!! */
// #define TICKET_DESCR_LEN        		50      /* !!! COME DA TABELLA ORACLE !!! */

class AlarmStatusData
{
public:

	enum ext_AlarmStatus {
		None,
		Booked,
		Ticketed
	};

	enum alarmStates {
		Off,
		On,
		Reserved,
		TicketEmitted,
		numAlarmStates
	};

	bool valid_record;
	unsigned char alarm_status;
	unsigned char extended_status;
	bool status_changed;
	QList< UnionValue * > * qc[MAX_ALLOWED_QC];
	QList< UnionValue * > * alarm_values;
	unsigned char interval;
	unsigned char persistence;
	uint link;
	QDateTime a_time;
	QList< QPair<QString, QString> > * hist_ala_pair_list;
	Nodo * hist_nodo;
	int handler_id;

	AlarmStatusData()
	: valid_record ( false ),
	  alarm_values ( 0 ),
	  extended_status(None),
	  link(0),
	  persistence(0),
	  hist_ala_pair_list(0),
	  hist_nodo(0),
	  handler_id(0),
	  status_changed(false)
	{
		memset ( qc, 0, ( MAX_ALLOWED_QC * sizeof ( QList< UnionValue * > * ) ) );
	}

	~AlarmStatusData()
	{
		if (hist_nodo) delete hist_nodo;
		if (hist_ala_pair_list) {
			while (!hist_ala_pair_list->isEmpty())
				hist_ala_pair_list->takeFirst();
			delete hist_ala_pair_list;
		}
		if (alarm_values) {
			while (!alarm_values->isEmpty())
				delete alarm_values->takeFirst();
			delete alarm_values;
		}
		for (int i = 0; i < MAX_ALLOWED_QC; i++) {
			if (qc[i]) {
				while (!qc[i]->isEmpty())
					delete qc[i]->takeFirst();
				delete qc[i];
			}
		}
	}

	AlarmStatusData& operator= ( const AlarmStatusData& o );
};

typedef QMultiMap<QString, AlarmStatusData *> AlarmDataMap;
typedef AlarmDataMap::iterator AlarmDataMapIterator;

class AlarmStatusNode
{
public:

	friend QDataStream &operator<<(QDataStream &out, const AlarmStatusNode &alarmatatusnode);
	friend QDataStream &operator>>(QDataStream &in, AlarmStatusNode &alarmatatusnode);

	AlarmStatusData ad[MAX_ALARM_GENERATOR];
	Nodo * nodo;


	AlarmStatusNode()
	: nodo ( 0 ),
	num_alarms ( 0 )
	{
	}

	AlarmStatusNode ( Nodo * nn )
	: nodo ( nn ),
	num_alarms ( 0 )
	{
	}

	AlarmStatusNode ( int na )
	: nodo ( 0 ),
	num_alarms ( na )
	{
	}

	~AlarmStatusNode() {
		if ( nodo ) delete nodo;
	}

	inline void setNumAlarms ( int nal ) {
		num_alarms = nal;
	}

	inline int getNumAlarms () {
		return num_alarms;
	}

	AlarmStatusNode& operator= ( const AlarmStatusNode& o );

protected:
	QDataStream & read(QDataStream & ds);
	QDataStream & write(QDataStream & ds) const;

private:
	int num_alarms;

};

typedef QMap<QString, AlarmStatusNode *> BtreeAlarmMap;
typedef QHash<uint, QString> LinkHash;
#define btree_alarm_key_rel_id( key_str )	( QString(key_str).left(ALARM_RELATION_INKEY_DIGITS).toInt() )
/** Maps per la gestione della parte dati real-time degli allarmi */

typedef QMap<QString, Nodo *> t_node_map;
// typedef QMap<int, t_node_map *> RTRelationAlarmData;

class RTNodeMap : public t_node_map
{
public:
	/** Salva i dati di real-time per la relazione selezionata */
	bool unloadRTRelationMap( int reference_relation, AMS_ALARM_RESOURCE * alarm_resource, int num_alarms, bool flush_map = true );
	/** Carica i dati di real-time per la relazione selezionata */
	bool loadRTRelationMap( int reference_relation, AMS_ALARM_RESOURCE * alarm_resource, int num_alarms );
	/** Svuota la RTNodeMap */
	inline void flushMap() {
		foreach (Nodo * nodo, *this)
			delete nodo;
		clear();
	}
};

typedef RTNodeMap::iterator RTNodeMapIterator;

/** Sync class */

class SafeCounter
{
public:
	SafeCounter() : n(0) {}

	void reset() { QMutexLocker locker(&mutex); n = 0; }
	void increment() { QMutexLocker locker(&mutex); ++n; }
	int operator++() { QMutexLocker locker(&mutex); ++n; return n; }
	void decrement() { QMutexLocker locker(&mutex); --n; }
	int operator--() { QMutexLocker locker(&mutex); --n; return n; }
	int value() const { QMutexLocker locker(&mutex); return n; }

private:
	mutable QMutex mutex;
	int n;
};


/**
 * Classe che estende la @ref BtreeAlarmMap class per
 * la gestione dell'i/o e thread handling
* @author Piergiorgio Betti
*/

class BTreeAlarm : public BtreeAlarmMap
{
public:
	BTreeAlarm()
	: alarm_resource(0),
	num_alarms(0),
	last_run_time(0)
	{}

	BTreeAlarm(AMS_ALARM_RESOURCE * a_alarm_resource,
		int a_num_alarms,
		QDateTime * a_last_run_time)
	: alarm_resource(a_alarm_resource),
	num_alarms(a_num_alarms),
	last_run_time(a_last_run_time)
	{}

// 	~BTreeAlarm();

	/** Explicit parameters setup */
	inline void setAlarmResource( AMS_ALARM_RESOURCE * a_alarm_resource ) { alarm_resource = a_alarm_resource; }
	inline void setNumAlarms( int a_num_alarms ) { num_alarms = a_num_alarms; }
	inline void setLastRunTime( QDateTime * a_last_run_time ) { last_run_time = a_last_run_time; }
	inline void setLinkHash( LinkHash * a_link_hash ) { link_hash = a_link_hash; }

	/** Salva lo stato degli allarmi corrente su file XML
	 */
	bool writeXMLStatus(int reference_relation, const QString & status_file_name);
	/** Carica lo stato degli allarmi salvato su file XML
	 */
	int readXMLStatus(int reference_relation, const QString & status_file_name, bool justDateCheck = false);

private:
	QString readAlarmStatusNode(QXmlStreamReader & r_xml, AlarmStatusNode * asn);
	void readAlarmStatusNode_Nodo(QXmlStreamReader & r_xml, AlarmStatusNode * asn);
	void readAlarmStatusData(QXmlStreamReader & r_xml, AlarmStatusNode * asn);
	void readAlarmStatusData_av(QXmlStreamReader & r_xml, AlarmStatusData * asd);
	void readAlarmStatusData_qc(QXmlStreamReader & r_xml, AlarmStatusData * asd, int n, const QString & typ);
	void readAlarmStatusData_av_h(QXmlStreamReader & r_xml, AlarmStatusData * asd);
	void readAlarmStatusData_HistNodo(QXmlStreamReader & r_xml, AlarmStatusData * asd);

	AMS_ALARM_RESOURCE * alarm_resource;
	int num_alarms;
	QDateTime * last_run_time;
	LinkHash * link_hash;

};

typedef BTreeAlarm::iterator BTreeAlarmIterator;

extern QDataStream &operator<<(QDataStream &out, const AlarmStatusNode &alarmatatusnode);
extern QDataStream &operator>>(QDataStream &in, AlarmStatusNode &alarmatatusnode);

#endif // BTREEALARMS_H
// kate: indent-mode cstyle; replace-tabs off; tab-width 8;  replace-tabs off;  replace-tabs off;
