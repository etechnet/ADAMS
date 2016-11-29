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

#include <queryparams.h>
#include <nodeconfighandler.h>
#include <Qt/qdatetime.h>


QueryParams::QueryParams(){
}
QueryParams::~QueryParams(){
}

void QueryParams::init_fake(const QString & my_node, const char * ini_filename, int diff_day )
{
	clear();
	c_qstrncpy(data.Name, "fake", MAX_CONFIG_FILENAME);
	data.AnalysisType = 0;
	data.NetworkRealTime = false;
	data.DataType = usrDataType_current;
	data.OutputType = usrOutputType_Web;

	data.ElaborationData << QDateTime::currentDateTime().addDays(diff_day).toString("yyyyMMdd");

	QString conf_ini_file;
	if (ini_filename)
		conf_ini_file = ini_filename;
	else
		conf_ini_file = ADAMSINIFILE;

	NodeConfigHandler nodeConfigHandler(conf_ini_file);


	for(int i = 0; i < MAX_FEP; i++) {
		if (QString(nodeConfigHandler.getInputNodeList().at(i).acName) == my_node)
			data.Fep[i] = 1;
		else
			data.Fep[i] = 0;
	}

	data.Relation = 0;
	data.RelationDirection = usrRelationKind_Direct;

}


void QueryParams::copyToCorba(STRUCT_PARAMS * ptr)
{
	c_qstrncpy(ptr->Name, data.Name, MAX_CONFIG_FILENAME);
	ptr->AnalysisType = data.AnalysisType;
	ptr->NetworkRealTime = data.NetworkRealTime;
	ptr->DataType = data.DataType;
	ptr->OutputType = data.OutputType;

	DATE_INFO str;
	ptr->ElaborationData.length(data.ElaborationData.count());
	int seqidx = 0;

	for  (QStringList::Iterator it0 = data.ElaborationData.begin(); it0 != data.ElaborationData.end(); ++it0) {
		c_qstrncpy(str.dateString, qPrintable(*it0), 9);

		(ptr->ElaborationData)[seqidx++] = str;
	}

	ptr->Fep.length(data.Fep.size());
	seqidx = 0;
	foreach(int fepn, data.Fep) {
		(ptr->Fep)[seqidx++] = fepn;
	}

	ptr->Relation = data.Relation;
	ptr->RelationDirection = data.RelationDirection;

	REST_INFO res;
	ptr->Filters.length(data.Filters.count());
	seqidx = 0;

	foreach(QueryParams::Rest flt, data.Filters) {
		res.Level = flt.Level;
		res.Element = flt.Element;
		res.Operator = flt.Operator;
		res.Priority = flt.Priority;

		for(int iNumRes = 0; iNumRes < MAX_N_VALUE; iNumRes++) {
			res.Value[iNumRes] = flt.Value[iNumRes];
			c_qstrncpy(res.AsciiValue[iNumRes], flt.AsciiValue[iNumRes], ASCII_REST_LEN);
		}
		(ptr->Filters)[seqidx++] = res;
	}

	ptr->OppositeRestriction = data.OppositeRestriction;
	ptr->FlagSort = data.FlagSort;
	ptr->ElementToSort = data.ElementToSort;
	ptr->Ascendent = data.Ascendent;
	ptr->Reserved = data.Reserved;
	ptr->SingleRelation = data.SingleRelation;
	ptr->IsPercent = data.IsPercent;
	ptr->HexValue = data.HexValue;
	ptr->isScheduled = data.isScheduled;
	ptr->idUser = data.idUser;
	ptr->idJob = data.idJob;
	c_qstrncpy(ptr->Description, data.Description, JQ_DESC_SIZE);
	ptr->idReportSchema = data.idReportSchema;
	c_qstrncpy(ptr->user, data.user, USR_LOGIN_LEN);
	c_qstrncpy(ptr->user_ip, data.user_ip, IP_STRING_LEN);
	ptr->freeFormat = data.freeFormat;
	for(int i = 0; i < MAX_DIMENSION; i++) {
		ptr->ffRelation[i] = data.ffRelation[i];
	}
}

void QueryParams::fillFromCorba(const STRUCT_PARAMS * ptr)
{
	clear();
	c_qstrncpy(data.Name, ptr->Name, MAX_CONFIG_FILENAME);
	data.AnalysisType = ptr->AnalysisType;
	data.NetworkRealTime = ptr->NetworkRealTime;
	data.DataType = (UsrParamDataType)ptr->DataType;
	data.OutputType = (UsrParamOutputType)ptr->OutputType;

	for(int i = 0; i < ptr->ElaborationData.length(); i++) {
		data.ElaborationData << QString(ptr->ElaborationData[i].dateString);
	}
	for(int i = 0; i < ptr->Fep.length(); i++) {
		data.Fep.append(ptr->Fep[i]);
	}

	data.Relation = ptr->Relation;
	data.RelationDirection = (UsrParamsRelationKind)ptr->RelationDirection;

	QueryParams::Rest * res;
	for(int i = 0; i < ptr->Filters.length(); i++) {
		res = new QueryParams::Rest;
		res->Level = ptr->Filters[i].Level;
		res->Element = ptr->Filters[i].Element;
		res->Operator = ptr->Filters[i].Operator;
		res->Priority = ptr->Filters[i].Priority;
		for(int iNumRes = 0; iNumRes < MAX_N_VALUE; iNumRes++) {
			res->Value[iNumRes] = ptr->Filters[i].Value[iNumRes];
			qstrncpy(res->AsciiValue[iNumRes], ptr->Filters[i].AsciiValue[iNumRes], ASCII_REST_LEN);
		}
		data.Filters.append(*res);
	}

	data.OppositeRestriction = ptr->OppositeRestriction;
	data.FlagSort = ptr->FlagSort;
	data.ElementToSort = ptr->ElementToSort;
	data.Ascendent = ptr->Ascendent;
	data.Reserved = ptr->Reserved;
	data.SingleRelation = ptr->SingleRelation;
	data.IsPercent = ptr->IsPercent;
	data.HexValue = ptr->HexValue;
	data.isScheduled = ptr->isScheduled;
	data.idUser = ptr->idUser;
	data.idJob = ptr->idJob;
	c_qstrncpy(data.Description, ptr->Description, JQ_DESC_SIZE);
	data.idReportSchema = ptr->idReportSchema;
	c_qstrncpy(data.user, ptr->user, USR_LOGIN_LEN);
	c_qstrncpy(data.user_ip, ptr->user_ip, IP_STRING_LEN);
	data.freeFormat = ptr->freeFormat;
	for(int i = 0; i < MAX_DIMENSION; i++) {
		data.ffRelation[i] = ptr->ffRelation[i];
	}
}

QDataStream& QueryParams::write ( QDataStream& ds ) const
{
	quint8 val8;
	quint32 val32;

	ds << QString(data.Name);
	val32 = data.AnalysisType;
	ds << val32;
	val8 = data.NetworkRealTime;
	ds << val8;
	val32 = data.DataType;
	ds << val32;
	val32 = data.OutputType;
	ds << val32;

	val32 = data.ElaborationData.size();
	ds << val32;
	foreach (QString s, data.ElaborationData)
		ds << s;

	val32 = data.Fep.size();
	ds << val32;
	foreach (int f, data.Fep) {
		val32 = f;
		ds << val32;
	}

	val32 = data.Relation;
	ds << val32;
	val32 = data.RelationDirection;
	ds << val32;

	val32 = data.Filters.size();
	ds << val32;
	foreach (Rest r, data.Filters) {
		val32 = r.Level;
		ds << val32;
		val32 = r.Element;
		ds << val32;
		for (int i = 0; i < MAX_N_VALUE; i++) {
			val32 = r.Value[i];
			ds << val32;
		}
		for (int i = 0; i < MAX_N_VALUE; i++) {
			ds << QString(r.AsciiValue[i]);
		}
		val32 = r.Operator;
		ds << val32;
		val32 = r.Priority;
		ds << val32;
	}

	val8 = data.OppositeRestriction;
	ds << val8;
	val8 = data.FlagSort;
	ds << val8;
	val32 = data.ElementToSort;
	ds << val32;
	val8 = data.Ascendent;
	ds << val8;
	val8 = data.Reserved;
	ds << val8;
	val8 = data.SingleRelation;
	ds << val8;
	val8 = data.IsPercent;
	ds << val8;
	val32 = data.HexValue;
	ds << val32;
	val8 = data.isScheduled;
	ds << val8;
	val32 = data.idUser;
	ds << val32;
	val32 = data.idJob;
	ds << val32;
	ds << QString(data.Description);
	val32 = data.idReportSchema;
	ds << val32;
	ds << QString(data.user);
	ds << QString(data.user_ip);
	val8 = data.freeFormat;
	ds << val8;
	for (int i = 0; i < MAX_DIMENSION; i++) {
		val32 = data.ffRelation[i];
		ds << val32;
	}

	return ds;
}

QDataStream& QueryParams::read ( QDataStream& ds )
{
	quint8 val8;
	quint32 val32;
	QString valS;
	int cnt;

	ds >> valS;
	qstrcpy(data.Name, qPrintable(valS));
	ds >> val32;
	data.AnalysisType = val32;
	ds >> val8;
	data.NetworkRealTime = val8;
	ds >> val32;
	data.DataType = (UsrParamDataType)val32;
	ds >> val32;
	data.OutputType = (UsrParamOutputType)val32;

	ds >> val32;
	cnt = val32;
	for (int i = 0; i < cnt; i++) {
		ds >> valS;
		data.ElaborationData << valS;
	}

	ds >> val32;
	cnt = val32;
	for (int i = 0; i < cnt; i++) {
		ds >> val32;
		data.Fep.append(val32);
	}

	ds >> val32;
	data.Relation = val32;
	ds >> val32;
	data.RelationDirection = (UsrParamsRelationKind)val32;

	ds >> val32;
	cnt = val32;
	for (int i = 0; i < cnt; i++) {
		Rest r;
		ds >> val32;
		r.Level = val32;
		ds >> val32;
		r.Element = val32;

		for (int j = 0; j < MAX_N_VALUE; j++) {
			ds >> val32;
			r.Value[j] = val32;
		}
		for (int j = 0; j < MAX_N_VALUE; j++) {
			ds >> valS;
			qstrcpy(r.AsciiValue[j], qPrintable(valS));
		}

		ds >> val32;
		r.Operator = val32;
		ds >> val32;
		r.Priority = val32;

	}

	ds >> val8;
	data.OppositeRestriction = val8;
	ds >> val8;
	data.FlagSort = val8;
	ds >> val32;
	data.ElementToSort = val32;
	ds >> val8;
	data.Ascendent = val8;
	ds >> val8;
	data.Reserved = val8;
	ds >> val8;
	data.SingleRelation = val8;
	ds >> val8;
	data.IsPercent = val8;
	ds >> val32;
	data.HexValue = val32;
	ds >> val8;
	data.isScheduled = val8;
	ds >> val32;
	data.idUser = val32;
	ds >> val32;
	data.idJob = val32;
	ds >> valS;
	qstrcpy(data.Description, qPrintable(valS));
	ds >> val32;
	data.idReportSchema = val32;
	ds >> valS;
	qstrcpy(data.user, qPrintable(valS));
	ds >> valS;
	qstrcpy(data.user_ip, qPrintable(valS));
	ds >> val8;
	data.freeFormat = val8;

	for (int i = 0; i < MAX_DIMENSION; i++) {
		ds >> val32;
		data.ffRelation[i] = val32;
	}

	return ds;
}

QDataStream &operator<< ( QDataStream &out, const QueryParams & query_params )
{
	return query_params.write ( out );
}
QDataStream &operator>> ( QDataStream &in, QueryParams & query_params )
{
	return query_params.read ( in );
}
