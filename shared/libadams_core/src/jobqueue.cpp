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

#include <jobqueue.h>
#include <applogger.h>
#include <Qt/qfile.h>
#include <Qt/qmutex.h>

static QMutex jum_mutex;
static QMutex jl_mutex;


JobUsersMap::JobUsersMap()
	: m_queues_config ( 0 )
{
}

JobUsersMap::~JobUsersMap()
{
	if ( m_queues_config )
		m_queues_config->close();
}

bool JobUsersMap::useQueuesConfigFilename ( const QString & filename )
{
	m_queues_config_filename = filename;
	m_queues_config = new QFile ( m_queues_config_filename );
	return m_queues_config->open ( QIODevice::ReadWrite | QIODevice::Unbuffered );
}

bool JobUsersMap::loadJobUsersMap ()
{
	JOB_CONFIG job_config;
	QMutexLocker ml ( &jum_mutex );

	clear();

	m_queues_config->seek ( 0 );
	while ( m_queues_config->read ( ( char * ) &job_config, sizeof ( JOB_CONFIG ) ) > 0 ) {
		insert ( job_config.user, job_config );
	}

	return true;
}

bool JobUsersMap::saveJobUsersMap ()
{
	QMutexLocker ml ( &jum_mutex );

	m_queues_config->seek ( 0 );

	foreach ( JOB_CONFIG jc, *this ) {
		if ( m_queues_config->write ( ( char * ) &jc, sizeof ( JOB_CONFIG ) ) < 0 )
			return false;
	}

	return true;
}


JobList::JobList()
{
}

JobList::JobList ( const QString & path, const QString & user_name )
{
	loadUserQueue ( path, user_name );
}

JobList::~JobList()
{
	clear();
}

void JobList::clear()
{
	foreach ( JobQueue * ptr, *this )
		delete ptr;
	QList::clear();

}

JobList JobList::loadUserQueue ( const QString & path, const QString & user_name )
{
	QString queue_fname = build_queue_filepath ( path, user_name );
	QFile queue_file ( queue_fname );

	QMutexLocker ml ( &jl_mutex );

	clear();

	// check for existence
	if ( ! queue_file.exists() ) {
		queue_file.open ( QIODevice::WriteOnly );
		queue_file.close();
	}

	if ( ! queue_file.open ( QIODevice::ReadOnly ) ) {
		lout << "loadUserQueue: ** Error opening queue file for user: " << user_name << " in: " << path << endl;
		return *this;
	}

	QDataStream ds ( &queue_file );
	JobQueue * job_queue;

	while ( !ds.atEnd() ) {
		job_queue = new JobQueue;
		ds >> *job_queue;
		append ( job_queue );
	}

	queue_file.close();
	return *this;
}

bool JobList::saveUserQueue ( const QString & path, const QString & user_name )
{
	QString queue_fname = build_queue_filepath ( path, user_name );

	QMutexLocker ml ( &jl_mutex );

	QFile queue_file ( queue_fname );
	if ( ! queue_file.open ( QIODevice::WriteOnly | QIODevice::Append | QIODevice::Truncate ) ) {
		lout << "save_user_queue: ** Error opening queue file for user: " << user_name << endl;
		return false;
	}

	QDataStream ds ( &queue_file );

	foreach ( JobQueue * job_queue, *this ) {
		ds << *job_queue;
	}

	queue_file.close();
	return true;
}

bool JobList::removeUserQueue(const QString & path, const QString & user_name)
{
	QString queue_fname = build_queue_filepath ( path, user_name );
	QFile queue_file ( queue_fname );

	QMutexLocker ml ( &jl_mutex );

	if ( queue_file.exists() ) {
		return queue_file.resize ( 0 );
	}

	return true;
}

void JobQueue::copyToCorba ( STRUCT_JOB * rec )
{
	rec->new_job = data.new_job;
	rec->job_id = data.job_id;
	rec->user_id = data.user_id;
	rec->data_start = data.data_start;
	rec->data_end = data.data_end;
	rec->hour = data.hour;
	rec->modality = data.modality;
	rec->qnt = data.qnt;
	c_qstrncpy ( rec->dest_fep, data.dest_fep, JQ_NODO_FEP_SIZE );
	rec->last_day = data.last_day;
	rec->last_month = data.last_month;
	rec->last_quarter = data.last_quarter;
	c_qstrncpy ( rec->dest_dir, data.dest_dir, JQ_DIR_DEST_SIZE );
	rec->locked = data.locked;
	rec->period = data.period;
	rec->start_hour = data.start_hour;
	rec->end_hour = data.end_hour;
	c_qstrncpy ( rec->description, data.description, JQ_DESC_SIZE );
}

void JobList::copyToCorba ( JOBSeq * seqptr )
{
	STRUCT_JOB * rec;

	seqptr->length ( size() );
	int seqidx = 0;

	for ( int i = 0; i < size(); i++ ) {

		rec = new STRUCT_JOB;

		at ( i )->copyToCorba ( rec );

		( *seqptr ) [seqidx++] = ( *rec );
	}
}

void JobQueue::fillFromCorba ( const STRUCT_JOB * ptr )
{
	data.new_job = ptr->new_job;
	data.job_id = ptr->job_id;
	data.user_id = ptr->user_id;
	data.data_start = ptr->data_start;
	data.data_end = ptr->data_end;
	data.hour = ptr->hour;
	data.modality = ptr->modality;
	data.qnt = ptr->qnt;
	qstrncpy ( data.dest_fep, ptr->dest_fep, JQ_NODO_FEP_SIZE );
	data.last_day = ptr->last_day;
	data.last_month = ptr->last_month;
	data.last_quarter = ptr->last_quarter;
	qstrncpy ( data.dest_dir, ptr->dest_dir, JQ_DIR_DEST_SIZE );
	data.locked = ptr->locked;
	data.period = ptr->period;
	data.start_hour = ptr->start_hour;
	data.end_hour = ptr->end_hour;
	qstrncpy ( data.description, ptr->description, JQ_DESC_SIZE );
}

void JobList::fillFromCorba ( const JOBSeq * seqptr )
{
	JobQueue * rec;
	clear();

	for ( int cnt = 0; cnt < seqptr->length(); cnt++ ) {
		rec = new JobQueue;

		rec->fillFromCorba ( & ( ( *seqptr ) [cnt] ) );

		append ( rec );
	}
}

QDataStream& JobQueue::write ( QDataStream& ds ) const
{
	quint8 val8;
	quint32 val32;
	quint64 val64;

	val32 = data.new_job;
	ds << val32;
	val32 = data.job_id;
	ds << val32;
	val32 = data.user_id;
	ds << val32;
	val64 = data.data_start;
	ds << val64;
	val64 = data.data_end;
	ds << val64;
	val32 = data.hour;
	ds << val32;
	val32 = data.modality;
	ds << val32;
	val32 = data.qnt;
	ds << val32;

	ds << data.req;

	ds << QString ( data.dest_fep );
	val64 = data.last_day;
	ds << val64;
	val64 = data.last_month;
	ds << val64;
	val32 = data.last_quarter;
	ds << val32;
	ds << QString ( data.dest_dir );
	val32 = data.locked;
	ds << val32;
	val32 = data.period;
	ds << val32;
	val32 = data.start_hour;
	ds << val32;
	val32 = data.end_hour;
	ds << val32;
	ds << QString ( data.description );


	return ds;
}

QDataStream& JobQueue::read ( QDataStream& ds )
{
	quint8 val8;
	quint32 val32;
	quint64 val64;
	QString valS;

	ds >> val32;
	data.new_job = val32;
	ds >> val32;
	data.job_id = val32;
	ds >> val32;
	data.user_id = val32;
	ds >> val64;
	data.data_start = val64;
	ds >> val64;
	data.data_end = val64;
	ds >> val32;
	data.hour = val32;
	ds >> val32;
	data.modality = val32;
	ds >> val32;
	data.qnt = val32;

	ds >> data.req;

	ds >> valS;
	qstrcpy ( data.dest_fep, qPrintable ( valS ) );
	ds >> val64;
	data.last_day = val64;
	ds >> val64;
	data.last_month = val64;
	ds >> val32;
	data.last_quarter = val32;
	ds >> valS;
	qstrcpy ( data.dest_dir, qPrintable ( valS ) );
	ds >> val32;
	data.locked = val32;
	ds >> val32;
	data.period = val32;
	ds >> val32;
	data.start_hour = val32;
	ds >> val32;
	data.end_hour = val32;
	ds >> valS;
	qstrcpy ( data.description, qPrintable ( valS ) );

	return ds;
}


QDataStream &operator<< ( QDataStream &out, const JobQueue & job_queue )
{
	return job_queue.write ( out );
}
QDataStream &operator>> ( QDataStream &in, JobQueue & job_queue )
{
	return job_queue.read ( in );
}
