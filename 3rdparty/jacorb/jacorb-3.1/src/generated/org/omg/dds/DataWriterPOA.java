package org.omg.dds;


/**
 * Generated from IDL interface "DataWriter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class DataWriterPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.DataWriterOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "assert_liveliness", Integer.valueOf(0));
		m_opsHash.put ( "set_qos", Integer.valueOf(1));
		m_opsHash.put ( "get_statuscondition", Integer.valueOf(2));
		m_opsHash.put ( "get_matched_subscription_data", Integer.valueOf(3));
		m_opsHash.put ( "get_offered_incompatible_qos_status", Integer.valueOf(4));
		m_opsHash.put ( "get_topic", Integer.valueOf(5));
		m_opsHash.put ( "get_status_changes", Integer.valueOf(6));
		m_opsHash.put ( "get_qos", Integer.valueOf(7));
		m_opsHash.put ( "get_matched_subscriptions", Integer.valueOf(8));
		m_opsHash.put ( "get_liveliness_lost_status", Integer.valueOf(9));
		m_opsHash.put ( "get_publication_match_status", Integer.valueOf(10));
		m_opsHash.put ( "enable", Integer.valueOf(11));
		m_opsHash.put ( "set_listener", Integer.valueOf(12));
		m_opsHash.put ( "get_offered_deadline_missed_status", Integer.valueOf(13));
		m_opsHash.put ( "get_publisher", Integer.valueOf(14));
		m_opsHash.put ( "get_listener", Integer.valueOf(15));
	}
	private String[] ids = {"IDL:omg.org/dds/DataWriter:1.0","IDL:omg.org/dds/Entity:1.0"};
	public org.omg.dds.DataWriter _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DataWriter __r = org.omg.dds.DataWriterHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DataWriter _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DataWriter __r = org.omg.dds.DataWriterHelper.narrow(__o);
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
			case 0: // assert_liveliness
			{
				_out = handler.createReply();
				assert_liveliness();
				break;
			}
			case 1: // set_qos
			{
				org.omg.dds.DataWriterQos _arg0=org.omg.dds.DataWriterQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_qos(_arg0));
				break;
			}
			case 2: // get_statuscondition
			{
				_out = handler.createReply();
				org.omg.dds.StatusConditionHelper.write(_out,get_statuscondition());
				break;
			}
			case 3: // get_matched_subscription_data
			{
				org.omg.dds.SubscriptionBuiltinTopicDataHolder _arg0= new org.omg.dds.SubscriptionBuiltinTopicDataHolder();
				_arg0._read (_input);
				int _arg1=_input.read_long();
				_out = handler.createReply();
				_out.write_long(get_matched_subscription_data(_arg0,_arg1));
				org.omg.dds.SubscriptionBuiltinTopicDataHelper.write(_out,_arg0.value);
				break;
			}
			case 4: // get_offered_incompatible_qos_status
			{
				_out = handler.createReply();
				org.omg.dds.OfferedIncompatibleQosStatusHelper.write(_out,get_offered_incompatible_qos_status());
				break;
			}
			case 5: // get_topic
			{
				_out = handler.createReply();
				org.omg.dds.TopicHelper.write(_out,get_topic());
				break;
			}
			case 6: // get_status_changes
			{
				_out = handler.createReply();
				_out.write_ulong(get_status_changes());
				break;
			}
			case 7: // get_qos
			{
				org.omg.dds.DataWriterQosHolder _arg0= new org.omg.dds.DataWriterQosHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				get_qos(_arg0);
				org.omg.dds.DataWriterQosHelper.write(_out,_arg0.value);
				break;
			}
			case 8: // get_matched_subscriptions
			{
				org.omg.dds.InstanceHandleSeqHolder _arg0= new org.omg.dds.InstanceHandleSeqHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				_out.write_long(get_matched_subscriptions(_arg0));
				org.omg.dds.InstanceHandleSeqHelper.write(_out,_arg0.value);
				break;
			}
			case 9: // get_liveliness_lost_status
			{
				_out = handler.createReply();
				org.omg.dds.LivelinessLostStatusHelper.write(_out,get_liveliness_lost_status());
				break;
			}
			case 10: // get_publication_match_status
			{
				_out = handler.createReply();
				org.omg.dds.PublicationMatchStatusHelper.write(_out,get_publication_match_status());
				break;
			}
			case 11: // enable
			{
				_out = handler.createReply();
				_out.write_long(enable());
				break;
			}
			case 12: // set_listener
			{
				org.omg.dds.DataWriterListener _arg0=org.omg.dds.DataWriterListenerHelper.read(_input);
				int _arg1=_input.read_ulong();
				_out = handler.createReply();
				_out.write_long(set_listener(_arg0,_arg1));
				break;
			}
			case 13: // get_offered_deadline_missed_status
			{
				_out = handler.createReply();
				org.omg.dds.OfferedDeadlineMissedStatusHelper.write(_out,get_offered_deadline_missed_status());
				break;
			}
			case 14: // get_publisher
			{
				_out = handler.createReply();
				org.omg.dds.PublisherHelper.write(_out,get_publisher());
				break;
			}
			case 15: // get_listener
			{
				_out = handler.createReply();
				org.omg.dds.DataWriterListenerHelper.write(_out,get_listener());
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
