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
#include <btreealarms.h>


#include <Qt/qxmlstream.h>
#include <Qt/qfile.h>
#include <Qt/qmap.h>
#include <Qt/qdatastream.h>

#include <pluginsapi.h>
#include <applogger.h>

AlarmStatusData& AlarmStatusData::operator= ( const AlarmStatusData& o )
{
	if (this == &o)
		return *this;

	this->valid_record = o.valid_record;
	this->alarm_status = o.alarm_status;
	this->status_changed = o.status_changed;

	quint16 qc_cnt = 0;
	for (int i = 0; i < MAX_ALLOWED_QC; i++) {
		if (o.qc[i] == 0)
			break;
		++qc_cnt;
	}

	for ( int i = 0; i <= qc_cnt; i++ ) {
		this->qc[i] = new QList< UnionValue * >;
	}

	for (int i = 0; i < qc_cnt; i++) {
		for (int j = 0; j < o.qc[i]->size(); j++) {
			switch (o.qc[i]->at(j)->typ) {
				case 'i':
					this->qc[i]->insert ( j, new UnionValue(o.qc[i]->at(j)->vInt) );
					break;
				case 'd':
					this->qc[i]->insert ( j, new UnionValue(o.qc[i]->at(j)->vDouble) );
					break;
			}
		}
	}

	if (o.alarm_values) {
		this->alarm_values = new QList< UnionValue * >;

		for (int j = 0; j < o.alarm_values->size(); j++) {
			switch (o.alarm_values->at(j)->typ) {
				case 'i':
					this->alarm_values->insert ( j, new UnionValue(o.alarm_values->at(j)->vInt) );
					break;
				case 'd':
					this->alarm_values->insert ( j, new UnionValue(o.alarm_values->at(j)->vDouble) );
					break;
			}
		}
	}

	this->interval = o.interval;
	this->persistence = o.persistence;
	this->link = o.link;
	this->a_time = o.a_time;
	if (o.hist_ala_pair_list) {
		this->hist_ala_pair_list = new QList< QPair<QString, QString> >;
		*this->hist_ala_pair_list = *o.hist_ala_pair_list;
	}
	if (o.hist_nodo) {
		int n_int, n_dbl;
		o.hist_nodo->getCountersSize_r(n_int, n_dbl);
		this->hist_nodo = new Nodo ( n_int, n_dbl );
		this->hist_nodo->setupCounters();
		*this->hist_nodo += *o.hist_nodo;
	}
	this->handler_id = o.handler_id;

	return *this;
}

AlarmStatusNode& AlarmStatusNode::operator= ( const AlarmStatusNode& o )
{
	if (this == &o)
		return *this;

	for (int i = 0; i < MAX_ALARM_GENERATOR; i++) {
		if (o.ad[i].valid_record)
			this->ad[i] = o.ad[i];
	}

	if (o.nodo) {
		int n_int, n_dbl;
		o.nodo->getCountersSize_r(n_int, n_dbl);
		this->nodo = new Nodo ( n_int, n_dbl );
		this->nodo->setupCounters();
		*this->nodo += *o.nodo;
	}

	this->num_alarms = o.num_alarms;

	return *this;
}


QDataStream& AlarmStatusNode::write ( QDataStream& ds ) const
{
	quint8 val8;
	quint32 val32;

	val32 = num_alarms; ds << val32;

	int n_int = 0, n_dbl = 0;
	if (nodo)
		nodo->getCountersSize_r(n_int, n_dbl);
	val32 = n_int; ds << val32;
	val32 = n_dbl; ds << val32;
	if (nodo) {
		for ( int i = 0; i < n_int; i++ ) {
			quint64 val = nodo->GetLista()->int_counters[i];
			ds << val;
		}
		for ( int i = 0; i < n_dbl; i++ ) {
			ds << nodo->GetLista()->dbl_counters[i];
		}
	}

	for (int p = 0; p < num_alarms; p++) {
		val8 = ad[p].valid_record; ds << val8;
		val8 = ad[p].alarm_status; ds << val8;
		val8 = ad[p].extended_status; ds << val8;
		val8 = ad[p].status_changed; ds << val8;

		quint16 qc_cnt = 0;
		for (int i = 0; i < MAX_ALLOWED_QC; i++) {
			if (ad[p].qc[i] == 0)
				break;
			++qc_cnt;
		}

		// qc sets present
		ds << qc_cnt;

		for (int i = 0; i < qc_cnt; i++) {
			quint16 n_intervals = ad[p].qc[i]->size();
			ds << n_intervals;

			for (int j = 0; j < n_intervals; j++) {
				switch (ad[p].qc[i]->at(j)->typ) {
					case 'i':
						val32 = ad[p].qc[i]->at(j)->vInt;
						val8 = 'i';
						ds << val8;
						ds << val32;
						break;
					case 'd':
						val8 = 'd';
						ds << val8;
						ds << (double)ad[p].qc[i]->at(j)->vDouble;
						break;
				}
			}
		}

		// alarm values list
		if (ad[p].alarm_values) {
			quint16 n_alval = ad[p].alarm_values->size();
			ds << n_alval;

			for (int j = 0; j < n_alval; j++) {
				switch (ad[p].alarm_values->at(j)->typ) {
					case 'i':
						val32 = ad[p].alarm_values->at(j)->vInt;
						val8 = 'i';
						ds << val8;
						ds << val32;
						break;
					case 'd':
						val8 = 'd';
						ds << val8;
						ds << (double)ad[p].alarm_values->at(j)->vDouble;
						break;
				}
			}
		}
		else {
			quint16 n_alval = 0;
			ds << n_alval;
		}

		val8 = ad[p].interval; ds << val8;
		val8 = ad[p].persistence; ds << val8;

		val32 = ad[p].link; ds << val32;

		ds << ad[p].a_time;
		if (ad[p].hist_ala_pair_list) {
			val8 = 1; ds << val8;
			ds << *ad[p].hist_ala_pair_list;
		}
		else {
			val8 = 0; ds << val8;
		}

		n_int = n_dbl = 0;
		if (ad[p].hist_nodo)
			ad[p].hist_nodo->getCountersSize_r(n_int, n_dbl);
		ds << n_int << n_dbl;
		if (ad[p].hist_nodo) {
			for ( int i = 0; i < n_int; i++ ) {
				quint64 val = ad[p].hist_nodo->GetLista()->int_counters[i];
				ds << val;
			}
			for ( int i = 0; i < n_dbl; i++ ) {
				ds << ad[p].hist_nodo->GetLista()->dbl_counters[i];
			}
		}
		val32 = ad[p].handler_id; ds << val32;
	}

	return ds;
}

QDataStream& AlarmStatusNode::read ( QDataStream& ds )
{
	quint8 val8;
	quint32 val32;

	ds >> val32; num_alarms = val32;

	quint32 n_int = 0, n_dbl = 0;
	ds >> n_int;
	ds >> n_dbl;

	if (n_int || n_dbl) {
		if (nodo)
			delete nodo;
		nodo = new Nodo ( n_int, n_dbl );
		nodo->setupCounters();
		quint64 val;
		for ( int i = 0; i < n_int; i++ ) {
			ds >> val;
			nodo->GetLista()->int_counters[i] = val;
		}
		for ( int i = 0; i < n_dbl; i++ ) {
			ds >> nodo->GetLista()->dbl_counters[i];
		}
	}

	for (int p = 0; p < num_alarms; p++) {
		ds >> val8; ad[p].valid_record = val8;
		ds >> val8; ad[p].alarm_status = val8;
		ds >> val8; ad[p].extended_status = val8;
		ds >> val8; ad[p].status_changed = val8;

		quint16 qc_cnt;

		// qc sets present
		ds >> qc_cnt;
		for ( int i = 0; i <= qc_cnt; i++ ) {
			ad[p].qc[i] = new QList< UnionValue * >;
		}

		double vald;
		for (int i = 0; i < qc_cnt; i++) {
			quint16 n_intervals;
			ds >> n_intervals;

			for (int j = 0; j < n_intervals; j++) {
				ds >> val8;
				switch (val8) {
					case 'i':
						ds >> val32;
						ad[p].qc[i]->insert ( j, new UnionValue((int)val8) );
						break;
					case 'd':
						ds >> vald;
						ad[p].qc[i]->insert ( j, new UnionValue(vald) );
						break;
				}
			}
		}

		// alarm values list
		quint16 n_alval;
		ds >> n_alval;

		if (n_alval) {
			ad[p].alarm_values = new QList< UnionValue * >;

			for (int j = 0; j < n_alval; j++) {
				ds >> val8;
				switch (val8) {
					case 'i':
						ds >> val32;
						ad[p].alarm_values->insert ( j, new UnionValue((int)val8) );
						break;
					case 'd':
						ds >> vald;
						ad[p].alarm_values->insert ( j, new UnionValue(vald) );
						break;
				}
			}
		}

		ds >> val8; ad[p].interval = val8;
		ds >> val8; ad[p].persistence = val8;

		ds >> val32; ad[p].link = val32;

		ds >> ad[p].a_time;

		ds >> val8;
		if (val8) {
			ad[p].hist_ala_pair_list = new QList< QPair<QString, QString> >;
			ds >> *ad[p].hist_ala_pair_list;
		}

		ds >> n_int;
		ds >> n_dbl;
		if (n_int || n_dbl) {
			if (ad[p].hist_nodo)
				delete ad[p].hist_nodo;
			ad[p].hist_nodo = new Nodo ( n_int, n_dbl );
			ad[p].hist_nodo->setupCounters();
			quint64 val;
			for ( int i = 0; i < n_int; i++ ) {
				ds >> val;
				ad[p].hist_nodo->GetLista()->int_counters[i] = val;
			}
			for ( int i = 0; i < n_dbl; i++ ) {
				ds >> ad[p].hist_nodo->GetLista()->dbl_counters[i];
			}
		}

		ds >> val32; ad[p].handler_id = val32;
	}

	return ds;
}



bool BTreeAlarm::writeXMLStatus(int reference_relation, const QString & status_file_name)
{
	AlarmGeneratorAPIData genAPI;

	// create a map from relation id to alarm vector
	QMap<int, int> id_to_vect;

	for ( int i = 0; i < num_alarms; i++ ) {
		if ( id_to_vect.find ( alarm_resource[i].id ) == id_to_vect.end() ) {  // avoid dups
			id_to_vect.insert ( alarm_resource[i].id, i );
			// 			lout1 << i << " -> " << alarm_resource[i].id << endl;
		}
		else
			continue;
	}

	QDateTime now = QDateTime::currentDateTime();

	QFile f_xml ( status_file_name );
	if ( ! f_xml.open ( QIODevice::WriteOnly | QIODevice::Text ) ) {
		lout << "** Error opening status file " << status_file_name
		<< " id:" << reference_relation << endl;
		return false;
	}

	QXmlStreamWriter w_xml ( &f_xml );

	w_xml.setAutoFormatting ( true );
	w_xml.writeStartDocument();
	w_xml.writeDTD ( "<!DOCTYPE alarm_status>" );
	w_xml.writeStartElement ( "alarm_master_status" );
	w_xml.writeAttribute ( "version", "1.0" );

	*last_run_time = QDateTime::currentDateTime();
	w_xml.writeStartElement ( "last_run_time" );
	w_xml.writeTextElement ( "date", last_run_time->date().toString ( "yyyyMMdd" ) );
	w_xml.writeTextElement ( "time", last_run_time->time().toString ( "hhmmss" ) );
	w_xml.writeTextElement ( "num_rel", QString::number ( num_alarms ) );
	w_xml.writeEndElement();

	BTreeAlarmIterator ai = begin();
	while ( ai != end() ) {

		QString DirectKey = ai.key();

		int rel_id = btree_alarm_key_rel_id ( DirectKey );
		int a_idx = id_to_vect.value ( rel_id );
		// strip relation di form key
		DirectKey.remove ( 0, ALARM_RELATION_INKEY_DIGITS );

		if ( rel_id != reference_relation ) {
			lout << "WARNING!! writeStatus called for different relation than in btree: "
			<< reference_relation << " (but " << rel_id << " in btree)"
			<< endl;
		}

		if ( a_idx >= num_alarms ) {
			lout << "Misconfigured node relation id " << rel_id << " @ position " << a_idx
			<< ". (size = " << id_to_vect.size() << ") Skipped."
			<< endl;
			++ai;
			continue;
		}

		// node init
		w_xml.writeStartElement ( "AlarmStatusNode" );
		w_xml.writeAttribute ( "key", ai.key() );
		w_xml.writeAttribute ( "n_int", QString::number ( alarm_resource[a_idx].n_int ) );
		w_xml.writeAttribute ( "n_dbl", QString::number ( alarm_resource[a_idx].n_dbl ) );

		// num alarms
		w_xml.writeStartElement ( "num_alarms" );
		w_xml.writeAttribute ( "val", QString::number ( ai.value()->getNumAlarms() ) );
		w_xml.writeEndElement();


		// nodo counters for this node
		w_xml.writeStartElement ( "nodo" );
		int n_int, n_dbl;
		ai.value()->nodo->getCountersSize_r ( n_int, n_dbl );

		if ( ai.value()->nodo->GetLista()->int_counters ) {
			for ( int i = 0; i < n_int; i++ ) {
				w_xml.writeTextElement ( "int", QString::number ( ai.value()->nodo->GetLista()->int_counters[i] ) );
			}
		}
		if ( ai.value()->nodo->GetLista()->dbl_counters ) {
			for ( int i = 0; i < n_dbl; i++ ) {
				w_xml.writeTextElement ( "dbl", QString::number ( ai.value()->nodo->GetLista()->dbl_counters[i] ) );
			}
		}

		w_xml.writeEndElement();

		// alarm data
		int p = 0;
		while ( alarm_resource[a_idx].handler[p].id > 0 && p < MAX_ALARM_GENERATOR ) {

			AlarmStatusData * asd = &ai.value()->ad[p];

			w_xml.writeStartElement ( "AlarmStatusData" );
			w_xml.writeAttribute ( "p", QString::number ( p ) );

			genAPI.operation = alarmHanlerOp_QCDescription;
			genAPI.infoPairList = 0;
			alarm_resource[a_idx].handler[p].plugin->pluginWorker ( ( void * ) &genAPI );

			int n_qc = 0;
			if ( genAPI.infoPairList )
				n_qc = genAPI.infoPairList->size();

			w_xml.writeAttribute ( "n_qc", QString::number ( n_qc ) );

			if ( genAPI.infoPairList ) {
				for ( int j = 0; j < n_qc; j++ ) {
					w_xml.writeStartElement ( "QC" );
					w_xml.writeAttribute ( "n", QString::number ( j ) );
					w_xml.writeAttribute ( "typ", genAPI.infoPairList->at ( j ).second );
					for ( int i = 0; i < alarm_resource[a_idx].policy.iInterval; i++ ) {
						w_xml.writeStartElement ( "qc" );
						w_xml.writeTextElement ( "idx", QString::number ( i ) );
						if ( genAPI.infoPairList->at ( j ).second == "int" )
							w_xml.writeTextElement ( "val", QString::number ( asd->qc[j]->value ( i )->vInt ) );
						else
							w_xml.writeTextElement ( "val", QString::number ( asd->qc[j]->value ( i )->vDouble ) );
						w_xml.writeEndElement();

					}
					w_xml.writeEndElement();
				}

				// remove all from list and list itself
				while ( ! genAPI.infoPairList->isEmpty() )
					genAPI.infoPairList->takeFirst();

				delete genAPI.infoPairList;
			}

			w_xml.writeStartElement ( "Alarm_Values" );
			genAPI.operation = alarmHanlerOp_AlarmValuesDescription;
			genAPI.infoPairList = 0;
			alarm_resource[a_idx].handler[p].plugin->pluginWorker ( ( void * ) &genAPI );

			if ( genAPI.infoPairList ) {
				for ( int i = 0; i < genAPI.infoPairList->size(); i++ ) {
					w_xml.writeStartElement ( "av" );
					w_xml.writeTextElement ( "idx", genAPI.infoPairList->at ( i ).first );
					int n_av = QString ( genAPI.infoPairList->at ( i ).first ).toInt();
					w_xml.writeTextElement ( "typ", genAPI.infoPairList->at ( i ).second );
					if ( genAPI.infoPairList->at ( i ).second == "int" )
						w_xml.writeTextElement ( "val", QString::number ( asd->alarm_values->value ( n_av )->vInt ) );
					else
						w_xml.writeTextElement ( "val", QString::number ( asd->alarm_values->value ( n_av )->vDouble ) );
					w_xml.writeEndElement();

				}

				// remove all from list and list itself
				while ( ! genAPI.infoPairList->isEmpty() )
					genAPI.infoPairList->takeFirst();

				delete genAPI.infoPairList;
			}
			w_xml.writeEndElement();

			w_xml.writeStartElement ( "fixed" );
			w_xml.writeTextElement ( "valid_record", QString::number ( asd->valid_record ) );
			w_xml.writeTextElement ( "alarm_status", QString::number ( asd->alarm_status ) );
			w_xml.writeTextElement ( "extended_status", QString::number ( asd->extended_status ) );
			w_xml.writeTextElement ( "status_changed", QString::number ( asd->status_changed ) );
			w_xml.writeTextElement ( "interval", QString::number ( asd->interval ) );
			w_xml.writeTextElement ( "persistence", QString::number ( asd->persistence ) );
			w_xml.writeTextElement ( "link", QString::number ( asd->link ) );
			w_xml.writeTextElement ( "a_time", asd->a_time.toString ( "yyyyMMddhhmmss" ) );
			w_xml.writeTextElement ( "handler_id", QString::number ( asd->handler_id ) );
			w_xml.writeEndElement();

			if ( asd->hist_ala_pair_list ) {

				w_xml.writeStartElement ( "AV_history" );

				for ( int i = 0; i < asd->hist_ala_pair_list->size(); i++ ) {
					w_xml.writeStartElement ( "av_h" );
					w_xml.writeTextElement ( "name", asd->hist_ala_pair_list->at ( i ).first );
					w_xml.writeTextElement ( "val", asd->hist_ala_pair_list->at ( i ).second );
					w_xml.writeEndElement();
				}

				w_xml.writeEndElement();
			}

			if ( asd->hist_nodo ) {
				w_xml.writeStartElement ( "hist_nodo" );
				int n_int, n_dbl;
				asd->hist_nodo->getCountersSize_r ( n_int, n_dbl );
				w_xml.writeAttribute ( "n_int", QString::number ( alarm_resource[a_idx].n_int ) );
				w_xml.writeAttribute ( "n_dbl", QString::number ( alarm_resource[a_idx].n_dbl ) );

				if ( asd->hist_nodo->GetLista()->int_counters ) {
					for ( int i = 0; i < n_int; i++ ) {
						w_xml.writeTextElement ( "int", QString::number ( asd->hist_nodo->GetLista()->int_counters[i] ) );
					}
				}
				if ( asd->hist_nodo->GetLista()->dbl_counters ) {
					for ( int i = 0; i < n_dbl; i++ ) {
						w_xml.writeTextElement ( "dbl", QString::number ( asd->hist_nodo->GetLista()->dbl_counters[i] ) );
					}
				}

				w_xml.writeEndElement();
			}

			w_xml.writeEndElement();
			++p;
		}

		w_xml.writeEndElement();
		++ai;
	}

	w_xml.writeEndElement();
	w_xml.writeEndDocument();
	f_xml.close();;

	return true;
}

int BTreeAlarm::readXMLStatus (int reference_relation, const QString & status_file_name, bool justDateCheck )
{
	QDateTime now = QDateTime::currentDateTime();

	QFile f_xml ( status_file_name );
	if ( ! f_xml.open ( QIODevice::ReadOnly | QIODevice::Text ) ) {
		lout << "** Error opening status file " << status_file_name
		<< " id:" << reference_relation << endl;
		return 0;
	}

	int n_rel = 0;

	QXmlStreamReader r_xml ( &f_xml );
	clear();

	AlarmStatusNode * al_node;
	QString al_key;

	while ( !r_xml.atEnd() ) {

		r_xml.readNext();

		if ( r_xml.isStartElement() ) {
			if ( r_xml.name() == "AlarmStatusNode" ) {
				al_node = new AlarmStatusNode ( n_rel );
				al_key = readAlarmStatusNode ( r_xml, al_node );
				insert ( al_key, al_node );
				continue;
			}
			else if ( r_xml.name() == "alarm_master_status" ) {
				lout << "Loading status from XML version:" << r_xml.attributes().value ( "version" ).toString() << endl;
				continue;
			}
			else if ( r_xml.name() == "last_run_time" ) {
				do {
					r_xml.readNext();

					if ( r_xml.isStartElement() &&  r_xml.name() == "date" ) {
						QString d = r_xml.readElementText();
						lout1 << "xml date:" << d << endl;
						last_run_time->setDate ( QDate::fromString ( d, "yyyyMMdd" ) );
						lout1 << "date:" << last_run_time->date().toString ( "yyyyMMdd" ) << endl;
					}
					else if ( r_xml.isStartElement() &&  r_xml.name() == "time" ) {
						QString t = r_xml.readElementText();
						lout1 << "xml time:" << t << endl;
					}
					else if ( r_xml.isStartElement() &&  r_xml.name() == "num_rel" ) {
						n_rel = r_xml.readElementText().toInt();
						lout1 << "num_rel:" << n_rel << endl;
					}
					// 					lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
				}
				while ( ! ( r_xml.isEndElement() && r_xml.name() == "last_run_time" ) && !r_xml.atEnd() );

						    if ( justDateCheck ) {
							    // 					btreeNet->unLock();
							    lout1 << "Just checking date." << endl;
							    f_xml.close();
							    return 1;
						    }

						    continue;
			}
		}
	}

	// 	btreeNet.unLock();

	if ( r_xml.hasError() ) {
		lout << "** Errors in XML file read" << endl;
		f_xml.close();
		f_xml.remove();
		return -1;
	}

	f_xml.close();
	return 1;
}

QString BTreeAlarm::readAlarmStatusNode ( QXmlStreamReader & r_xml, AlarmStatusNode * asn )
{
	QString node_key = r_xml.attributes().value ( "key" ).toString();
	int n_int = r_xml.attributes().value ( "n_int" ).toString().toInt();
	int n_dbl = r_xml.attributes().value ( "n_dbl" ).toString().toInt();

	asn->nodo = new Nodo ( n_int, n_dbl );
	asn->nodo->setupCounters();

	do {
		r_xml.readNext();

		if ( r_xml.isStartElement() && r_xml.name() == "num_alarms" ) {
			asn->setNumAlarms ( r_xml.attributes().value ( "val" ).toString().toInt() );
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "nodo" ) {
			readAlarmStatusNode_Nodo ( r_xml, asn );
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "AlarmStatusData" ) {
			readAlarmStatusData ( r_xml, asn );
		}

		// 		lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
	}
	while ( ! ( r_xml.isEndElement() && r_xml.name() == "AlarmStatusNode" ) && !r_xml.atEnd() );

						    // re-assign an hash key to the link
	int p = 0;
	while ( asn->ad[p].valid_record && p < MAX_ALARM_GENERATOR ) {
		if ( asn->ad[p].alarm_status == STATUS_ON && asn->ad[p].link != 0 ) {
			int rel_id = btree_alarm_key_rel_id ( node_key );
			int a_idx = -1;

			for ( int i = 0; i < num_alarms; i++ ) {
				if ( alarm_resource[i].id == rel_id ) {
					a_idx = i;
					break;
				}
			}

			if ( a_idx < 0 ) {
				lout << "Unmappable rel_id:" << rel_id
				<< " found. Link=" << asn->ad[p].link
				<< " Key=" << node_key
				<< endl;

				++p;
				continue;
			}

			QString h_key = node_key + QChar ( '~' ) + QString::number ( a_idx )
			+ QChar ( '~' ) + QString::number ( p );
			link_hash->insert ( asn->ad[p].link, h_key );
		}
		++p;
	}

	return node_key;
}

void BTreeAlarm::readAlarmStatusData_HistNodo ( QXmlStreamReader & r_xml, AlarmStatusData * asd )
{
	int c_int;
	int c_dbl;
	int n_int = r_xml.attributes().value ( "n_int" ).toString().toInt();
	int n_dbl = r_xml.attributes().value ( "n_dbl" ).toString().toInt();

	asd->hist_nodo = new Nodo ( n_int, n_dbl );
	asd->hist_nodo->setupCounters();

	c_dbl = c_int = 0;
	do {
		r_xml.readNext();

		if ( r_xml.isStartElement() && r_xml.name() == "int" ) {
			if ( c_int < n_int )
				asd->hist_nodo->GetLista()->int_counters[c_int++] = r_xml.readElementText().toInt();
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "dbl" ) {
			if ( c_dbl < n_dbl )
				asd->hist_nodo->GetLista()->dbl_counters[c_dbl++] = r_xml.readElementText().toDouble();
		}

		// 		lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
	}
	while ( ! ( r_xml.isEndElement() && r_xml.name() == "hist_nodo" ) && !r_xml.atEnd() );
}

void BTreeAlarm::readAlarmStatusNode_Nodo ( QXmlStreamReader & r_xml, AlarmStatusNode * asn )
{
	int n_int, c_int;
	int n_dbl, c_dbl;

	asn->nodo->getCountersSize_r ( n_int, n_dbl );

	c_dbl = c_int = 0;
	do {
		r_xml.readNext();

		if ( r_xml.isStartElement() && r_xml.name() == "int" ) {
			if ( c_int < n_int )
				asn->nodo->GetLista()->int_counters[c_int++] = r_xml.readElementText().toInt();
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "dbl" ) {
			if ( c_dbl < n_dbl )
				asn->nodo->GetLista()->dbl_counters[c_dbl++] = r_xml.readElementText().toDouble();
		}

		// 		lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
	}
	while ( ! ( r_xml.isEndElement() && r_xml.name() == "nodo" ) && !r_xml.atEnd() );
}

void BTreeAlarm::readAlarmStatusData ( QXmlStreamReader & r_xml, AlarmStatusNode * asn )
{
	int p = r_xml.attributes().value ( "p" ).toString().toInt();
	int n_qc = r_xml.attributes().value ( "n_qc" ).toString().toInt();
	AlarmStatusData * asd = &asn->ad[p];

	asd->alarm_values = new QList< UnionValue * >;
	for ( int i = 0; i <= n_qc; i++ ) {
		asd->qc[i] = new QList< UnionValue * >;
	}

	do {
		r_xml.readNext();

		if ( r_xml.isStartElement() &&  r_xml.name() == "QC" ) {
			int n = r_xml.attributes().value ( "n" ).toString().toInt();
			QString typ = r_xml.attributes().value ( "typ" ).toString();
			do {
				r_xml.readNext();

				if ( r_xml.isStartElement() &&  r_xml.name() == "qc" ) {
					readAlarmStatusData_qc ( r_xml, asd, n, typ );
				}
			}
			while ( ! ( r_xml.isEndElement() && r_xml.name() == "QC" ) && !r_xml.atEnd() );
		}
		else if ( r_xml.isStartElement() && r_xml.name() == "Alarm_Values" ) {
			do {
				r_xml.readNext();

				if ( r_xml.isStartElement() &&  r_xml.name() == "av" ) {
					readAlarmStatusData_av ( r_xml, asd );
				}
			}
			while ( ! ( r_xml.isEndElement() && r_xml.name() == "Alarm_Values" ) && !r_xml.atEnd() );
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "fixed" ) {
			do {
				r_xml.readNext();

				if ( r_xml.isStartElement() &&  r_xml.name() == "valid_record" ) {
					asd->valid_record = r_xml.readElementText().toInt();
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "alarm_status" ) {
					asd->alarm_status = r_xml.readElementText().toInt();
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "extended_status" ) {
					asd->extended_status = r_xml.readElementText().toInt();
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "status_changed" ) {
					asd->status_changed = r_xml.readElementText().toInt();
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "interval" ) {
					asd->interval = r_xml.readElementText().toInt();
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "persistence" ) {
					asd->persistence = r_xml.readElementText().toInt();
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "link" ) {
					asd->link = r_xml.readElementText().toInt();
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "a_time" ) {
					asd->a_time = QDateTime::fromString ( r_xml.readElementText(), "yyyyMMddhhmmss" );
				}
				if ( r_xml.isStartElement() &&  r_xml.name() == "handler_id" ) {
					asd->handler_id = r_xml.readElementText().toInt();
				}
			}
			while ( ! ( r_xml.isEndElement() && r_xml.name() == "fixed" ) && !r_xml.atEnd() );
		}
		else if ( r_xml.isStartElement() && r_xml.name() == "AV_history" ) {
			asd->hist_ala_pair_list = new QList< QPair<QString, QString> >;
			do {
				r_xml.readNext();

				if ( r_xml.isStartElement() &&  r_xml.name() == "av_h" ) {
					readAlarmStatusData_av_h ( r_xml, asd );
				}
			}
			while ( ! ( r_xml.isEndElement() && r_xml.name() == "AV_history" ) && !r_xml.atEnd() );
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "hist_nodo" ) {
			readAlarmStatusData_HistNodo ( r_xml, asd );
		}

		// 		lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
	}
	while ( ! ( r_xml.isEndElement() && r_xml.name() == "AlarmStatusData" ) && !r_xml.atEnd() );
}

void BTreeAlarm::readAlarmStatusData_av ( QXmlStreamReader & r_xml, AlarmStatusData * asd )
{
	QString typ;
	int idx = 0;

	do {
		r_xml.readNext();

		if ( r_xml.isStartElement() && r_xml.name() == "idx" ) {
			idx = r_xml.readElementText().toInt();
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "typ" ) {
			typ = r_xml.readElementText();
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "val" ) {
			if ( typ == "int" )
				asd->alarm_values->insert ( idx, new UnionValue ( r_xml.readElementText().toInt() ) );
			else
				asd->alarm_values->insert ( idx, new UnionValue ( r_xml.readElementText().toDouble() ) );
		}

		// 		lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
	}
	while ( ! ( r_xml.isEndElement() && r_xml.name() == "av" ) && !r_xml.atEnd() );
}

void BTreeAlarm::readAlarmStatusData_av_h ( QXmlStreamReader & r_xml, AlarmStatusData * asd )
{
	QString name;
	QString val;

	do {
		r_xml.readNext();

		if ( r_xml.isStartElement() && r_xml.name() == "name" ) {
			name = r_xml.readElementText();
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "val" ) {
			val = r_xml.readElementText();
		}

		// 		lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
	}
	while ( ! ( r_xml.isEndElement() && r_xml.name() == "av_h" ) && !r_xml.atEnd() );
						    asd->hist_ala_pair_list->append ( qMakePair ( name, val ) );
}

void BTreeAlarm::readAlarmStatusData_qc ( QXmlStreamReader & r_xml, AlarmStatusData * asd, int n, const QString & typ )
{
	int idx = 0;

	do {
		r_xml.readNext();

		if ( r_xml.isStartElement() && r_xml.name() == "idx" ) {
			idx = r_xml.readElementText().toInt();
		}
		else if ( r_xml.isStartElement() &&  r_xml.name() == "val" ) {
			if ( typ == "int" )
				asd->qc[n]->insert ( idx, new UnionValue ( r_xml.readElementText().toInt() ) );
			else
				asd->qc[n]->insert ( idx, new UnionValue ( r_xml.readElementText().toDouble() ) );
		}

		// 		lout1 << "token:" << r_xml.tokenString() << " name:" << r_xml.name().toString() << endl;
	}
	while ( ! ( r_xml.isEndElement() && r_xml.name() == "qc" ) && !r_xml.atEnd() );
}


bool RTNodeMap::unloadRTRelationMap ( int reference_relation, AMS_ALARM_RESOURCE * alarm_resource, int num_alarms, bool flush_map )
{
	// create a map from relation id to alarm vector
	QMap<int, int> id_to_vect;

	for ( int i = 0; i < num_alarms; i++ ) {
		if ( id_to_vect.find ( alarm_resource[i].id ) == id_to_vect.end() ) {  // avoid dups
			id_to_vect.insert ( alarm_resource[i].id, i );
		}
		else
			continue;
	}

	int a_idx = id_to_vect.value ( reference_relation );


	QString status_file_name(RT_STATUS_BASENAME);
	status_file_name += QString::number(reference_relation) + ".bin";

	QFile f_status ( status_file_name );
	if ( ! f_status.open ( QIODevice::WriteOnly ) ) {
		lout << "** Error opening status file " << status_file_name << endl;
		return false;
	}
	QDataStream ds(&f_status);

	RTNodeMapIterator node_map_iterator = begin();

	while ( node_map_iterator != end() ) {

		ds << node_map_iterator.key();

		for (int i = 0; i < alarm_resource[a_idx].n_int; i++) {
			ds << (quint64) node_map_iterator.value()->GetLista()->int_counters[i];
		}
		for (int i = 0; i < alarm_resource[a_idx].n_dbl; i++) {
			ds << (double) node_map_iterator.value()->GetLista()->dbl_counters[i];
		}

		++node_map_iterator;
	}

	f_status.close();

	if (flush_map) {
		foreach (Nodo * nodo, *this)
			delete nodo;
		clear();
	}

	return true;
}

bool RTNodeMap::loadRTRelationMap ( int reference_relation, AMS_ALARM_RESOURCE * alarm_resource, int num_alarms )
{
	// create a map from relation id to alarm vector
	QMap<int, int> id_to_vect;

	for ( int i = 0; i < num_alarms; i++ ) {
		if ( id_to_vect.find ( alarm_resource[i].id ) == id_to_vect.end() ) {  // avoid dups
			id_to_vect.insert ( alarm_resource[i].id, i );
		}
		else
			continue;
	}

	int a_idx = id_to_vect.value ( reference_relation );


	QString status_file_name(RT_STATUS_BASENAME);
	status_file_name += QString::number(reference_relation) + ".bin";

	QFile f_status ( status_file_name );
	if ( ! f_status.open ( QIODevice::ReadOnly ) ) {
		lout << "** Error opening status file " << status_file_name << endl;
		return false;
	}
	QDataStream ds(&f_status);

	foreach (Nodo * nodo, *this)
		delete nodo;
	clear();

	QString key;
	int int_n = alarm_resource[a_idx].n_int;
	int dbl_n = alarm_resource[a_idx].n_dbl;

	while (!ds.atEnd()) {
		Nodo * m_nodo = new Nodo(int_n, dbl_n);
		m_nodo->setupCounters();
		quint64 myulong;
		double mydouble;

		ds >> key;

		for (int i = 0; i < int_n; i++) {
			ds >> myulong;
			m_nodo->GetLista()->int_counters[i] = myulong;
		}
		for (int i = 0; i < dbl_n; i++) {
			ds >> mydouble;
			m_nodo->GetLista()->dbl_counters[i] = mydouble;
		}

		insert(key, m_nodo);
	}

	f_status.close();

	return true;
}

QDataStream &operator<<(QDataStream &out, const AlarmStatusNode &alarmatatusnode) { return alarmatatusnode.write(out); }
QDataStream &operator>>(QDataStream &in, AlarmStatusNode &alarmatatusnode) { return alarmatatusnode.read(in); }
