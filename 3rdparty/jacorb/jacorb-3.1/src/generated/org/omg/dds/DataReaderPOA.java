package org.omg.dds;


/**
 * Generated from IDL interface "DataReader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class DataReaderPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.DataReaderOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "set_listener", Integer.valueOf(0));
		m_opsHash.put ( "get_qos", Integer.valueOf(1));
		m_opsHash.put ( "get_matched_publications", Integer.valueOf(2));
		m_opsHash.put ( "get_statuscondition", Integer.valueOf(3));
		m_opsHash.put ( "set_qos", Integer.valueOf(4));
		m_opsHash.put ( "enable", Integer.valueOf(5));
		m_opsHash.put ( "get_topicdescription", Integer.valueOf(6));
		m_opsHash.put ( "get_liveliness_changed_status", Integer.valueOf(7));
		m_opsHash.put ( "get_requested_incompatible_qos_status", Integer.valueOf(8));
		m_opsHash.put ( "wait_for_historical_data", Integer.valueOf(9));
		m_opsHash.put ( "create_querycondition", Integer.valueOf(10));
		m_opsHash.put ( "create_readcondition", Integer.valueOf(11));
		m_opsHash.put ( "get_status_changes", Integer.valueOf(12));
		m_opsHash.put ( "get_matched_publication_data", Integer.valueOf(13));
		m_opsHash.put ( "get_listener", Integer.valueOf(14));
		m_opsHash.put ( "get_subscriber", Integer.valueOf(15));
		m_opsHash.put ( "delete_readcondition", Integer.valueOf(16));
		m_opsHash.put ( "delete_contained_entities", Integer.valueOf(17));
		m_opsHash.put ( "get_sample_rejected_status", Integer.valueOf(18));
		m_opsHash.put ( "get_sample_lost_status", Integer.valueOf(19));
		m_opsHash.put ( "get_requested_deadline_missed_status", Integer.valueOf(20));
		m_opsHash.put ( "take_instance_from_subscriber", Integer.valueOf(21));
		m_opsHash.put ( "get_subscription_match_status", Integer.valueOf(22));
	}
	private String[] ids = {"IDL:omg.org/dds/DataReader:1.0","IDL:omg.org/dds/Entity:1.0"};
	public org.omg.dds.DataReader _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DataReader __r = org.omg.dds.DataReaderHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DataReader _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DataReader __r = org.omg.dds.DataReaderHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // set_listener
			{
				org.omg.dds.DataReaderListener _arg0=org.omg.dds.DataReaderListenerHelper.read(_input);
				int _arg1=_input.read_ulong();
				_out = handler.createReply();
				_out.write_long(set_listener(_arg0,_arg1));
				break;
			}
			case 1: // get_qos
			{
				org.omg.dds.DataReaderQosHolder _arg0= new org.omg.dds.DataReaderQosHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				get_qos(_arg0);
				org.omg.dds.DataReaderQosHelper.write(_out,_arg0.value);
				break;
			}
			case 2: // get_matched_publications
			{
				org.omg.dds.InstanceHandleSeqHolder _arg0= new org.omg.dds.InstanceHandleSeqHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				_out.write_long(get_matched_publications(_arg0));
				org.omg.dds.InstanceHandleSeqHelper.write(_out,_arg0.value);
				break;
			}
			case 3: // get_statuscondition
			{
				_out = handler.createReply();
				org.omg.dds.StatusConditionHelper.write(_out,get_statuscondition());
				break;
			}
			case 4: // set_qos
			{
				org.omg.dds.DataReaderQos _arg0=org.omg.dds.DataReaderQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_qos(_arg0));
				break;
			}
			case 5: // enable
			{
				_out = handler.createReply();
				_out.write_long(enable());
				break;
			}
			case 6: // get_topicdescription
			{
				_out = handler.createReply();
				org.omg.dds.TopicDescriptionHelper.write(_out,get_topicdescription());
				break;
			}
			case 7: // get_liveliness_changed_status
			{
				_out = handler.createReply();
				org.omg.dds.LivelinessChangedStatusHelper.write(_out,get_liveliness_changed_status());
				break;
			}
			case 8: // get_requested_incompatible_qos_status
			{
				_out = handler.createReply();
				org.omg.dds.RequestedIncompatibleQosStatusHelper.write(_out,get_requested_incompatible_qos_status());
				break;
			}
			case 9: // wait_for_historical_data
			{
				org.omg.dds.Duration_t _arg0=org.omg.dds.Duration_tHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(wait_for_historical_data(_arg0));
				break;
			}
			case 10: // create_querycondition
			{
				int _arg0=_input.read_ulong();
				int _arg1=_input.read_ulong();
				int _arg2=_input.read_ulong();
				java.lang.String _arg3=_input.read_string();
				java.lang.String[] _arg4=org.omg.dds.StringSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.QueryConditionHelper.write(_out,create_querycondition(_arg0,_arg1,_arg2,_arg3,_arg4));
				break;
			}
			case 11: // create_readcondition
			{
				int _arg0=_input.read_ulong();
				int _arg1=_input.read_ulong();
				int _arg2=_input.read_ulong();
				_out = handler.createReply();
				org.omg.dds.ReadConditionHelper.write(_out,create_readcondition(_arg0,_arg1,_arg2));
				break;
			}
			case 12: // get_status_changes
			{
				_out = handler.createReply();
				_out.write_ulong(get_status_changes());
				break;
			}
			case 13: // get_matched_publication_data
			{
				org.omg.dds.PublicationBuiltinTopicDataHolder _arg0= new org.omg.dds.PublicationBuiltinTopicDataHolder();
				_arg0._read (_input);
				int _arg1=_input.read_long();
				_out = handler.createReply();
				_out.write_long(get_matched_publication_data(_arg0,_arg1));
				org.omg.dds.PublicationBuiltinTopicDataHelper.write(_out,_arg0.value);
				break;
			}
			case 14: // get_listener
			{
				_out = handler.createReply();
				org.omg.dds.DataReaderListenerHelper.write(_out,get_listener());
				break;
			}
			case 15: // get_subscriber
			{
				_out = handler.createReply();
				org.omg.dds.SubscriberHelper.write(_out,get_subscriber());
				break;
			}
			case 16: // delete_readcondition
			{
				org.omg.dds.ReadCondition _arg0=org.omg.dds.ReadConditionHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_readcondition(_arg0));
				break;
			}
			case 17: // delete_contained_entities
			{
				_out = handler.createReply();
				_out.write_long(delete_contained_entities());
				break;
			}
			case 18: // get_sample_rejected_status
			{
				_out = handler.createReply();
				org.omg.dds.SampleRejectedStatusHelper.write(_out,get_sample_rejected_status());
				break;
			}
			case 19: // get_sample_lost_status
			{
				_out = handler.createReply();
				org.omg.dds.SampleLostStatusHelper.write(_out,get_sample_lost_status());
				break;
			}
			case 20: // get_requested_deadline_missed_status
			{
				_out = handler.createReply();
				org.omg.dds.RequestedDeadlineMissedStatusHelper.write(_out,get_requested_deadline_missed_status());
				break;
			}
			case 21: // take_instance_from_subscriber
			{
				_out = handler.createReply();
				take_instance_from_subscriber();
				break;
			}
			case 22: // get_subscription_match_status
			{
				_out = handler.createReply();
				org.omg.dds.SubscriptionMatchStatusHelper.write(_out,get_subscription_match_status());
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
