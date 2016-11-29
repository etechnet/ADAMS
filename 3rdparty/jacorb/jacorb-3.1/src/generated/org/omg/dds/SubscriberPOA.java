package org.omg.dds;


/**
 * Generated from IDL interface "Subscriber".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class SubscriberPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.SubscriberOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "set_default_datareader_qos", Integer.valueOf(0));
		m_opsHash.put ( "begin_access", Integer.valueOf(1));
		m_opsHash.put ( "get_statuscondition", Integer.valueOf(2));
		m_opsHash.put ( "get_default_datareader_qos", Integer.valueOf(3));
		m_opsHash.put ( "get_datareaders", Integer.valueOf(4));
		m_opsHash.put ( "enable", Integer.valueOf(5));
		m_opsHash.put ( "notify_datareaders", Integer.valueOf(6));
		m_opsHash.put ( "create_datareader", Integer.valueOf(7));
		m_opsHash.put ( "end_access", Integer.valueOf(8));
		m_opsHash.put ( "get_status_changes", Integer.valueOf(9));
		m_opsHash.put ( "get_listener", Integer.valueOf(10));
		m_opsHash.put ( "copy_from_topic_qos", Integer.valueOf(11));
		m_opsHash.put ( "get_participant", Integer.valueOf(12));
		m_opsHash.put ( "get_qos", Integer.valueOf(13));
		m_opsHash.put ( "delete_contained_entities", Integer.valueOf(14));
		m_opsHash.put ( "delete_datareader", Integer.valueOf(15));
		m_opsHash.put ( "lookup_datareader", Integer.valueOf(16));
		m_opsHash.put ( "set_listener", Integer.valueOf(17));
		m_opsHash.put ( "set_qos", Integer.valueOf(18));
	}
	private String[] ids = {"IDL:omg.org/dds/Subscriber:1.0","IDL:omg.org/dds/Entity:1.0"};
	public org.omg.dds.Subscriber _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.Subscriber __r = org.omg.dds.SubscriberHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.Subscriber _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.Subscriber __r = org.omg.dds.SubscriberHelper.narrow(__o);
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
			case 0: // set_default_datareader_qos
			{
				org.omg.dds.DataReaderQos _arg0=org.omg.dds.DataReaderQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_default_datareader_qos(_arg0));
				break;
			}
			case 1: // begin_access
			{
				_out = handler.createReply();
				_out.write_long(begin_access());
				break;
			}
			case 2: // get_statuscondition
			{
				_out = handler.createReply();
				org.omg.dds.StatusConditionHelper.write(_out,get_statuscondition());
				break;
			}
			case 3: // get_default_datareader_qos
			{
				org.omg.dds.DataReaderQosHolder _arg0= new org.omg.dds.DataReaderQosHolder();
				_out = handler.createReply();
				get_default_datareader_qos(_arg0);
				org.omg.dds.DataReaderQosHelper.write(_out,_arg0.value);
				break;
			}
			case 4: // get_datareaders
			{
				org.omg.dds.DataReaderSeqHolder _arg0= new org.omg.dds.DataReaderSeqHolder();
				int _arg1=_input.read_ulong();
				int _arg2=_input.read_ulong();
				int _arg3=_input.read_ulong();
				_out = handler.createReply();
				_out.write_long(get_datareaders(_arg0,_arg1,_arg2,_arg3));
				org.omg.dds.DataReaderSeqHelper.write(_out,_arg0.value);
				break;
			}
			case 5: // enable
			{
				_out = handler.createReply();
				_out.write_long(enable());
				break;
			}
			case 6: // notify_datareaders
			{
				_out = handler.createReply();
				notify_datareaders();
				break;
			}
			case 7: // create_datareader
			{
				org.omg.dds.TopicDescription _arg0=org.omg.dds.TopicDescriptionHelper.read(_input);
				org.omg.dds.DataReaderQos _arg1=org.omg.dds.DataReaderQosHelper.read(_input);
				org.omg.dds.DataReaderListener _arg2=org.omg.dds.DataReaderListenerHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.DataReaderHelper.write(_out,create_datareader(_arg0,_arg1,_arg2));
				break;
			}
			case 8: // end_access
			{
				_out = handler.createReply();
				_out.write_long(end_access());
				break;
			}
			case 9: // get_status_changes
			{
				_out = handler.createReply();
				_out.write_ulong(get_status_changes());
				break;
			}
			case 10: // get_listener
			{
				_out = handler.createReply();
				org.omg.dds.SubscriberListenerHelper.write(_out,get_listener());
				break;
			}
			case 11: // copy_from_topic_qos
			{
				org.omg.dds.DataReaderQosHolder _arg0= new org.omg.dds.DataReaderQosHolder();
				_arg0._read (_input);
				org.omg.dds.TopicQos _arg1=org.omg.dds.TopicQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(copy_from_topic_qos(_arg0,_arg1));
				org.omg.dds.DataReaderQosHelper.write(_out,_arg0.value);
				break;
			}
			case 12: // get_participant
			{
				_out = handler.createReply();
				org.omg.dds.DomainParticipantHelper.write(_out,get_participant());
				break;
			}
			case 13: // get_qos
			{
				org.omg.dds.SubscriberQosHolder _arg0= new org.omg.dds.SubscriberQosHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				get_qos(_arg0);
				org.omg.dds.SubscriberQosHelper.write(_out,_arg0.value);
				break;
			}
			case 14: // delete_contained_entities
			{
				_out = handler.createReply();
				_out.write_long(delete_contained_entities());
				break;
			}
			case 15: // delete_datareader
			{
				org.omg.dds.DataReader _arg0=org.omg.dds.DataReaderHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_datareader(_arg0));
				break;
			}
			case 16: // lookup_datareader
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.dds.DataReaderHelper.write(_out,lookup_datareader(_arg0));
				break;
			}
			case 17: // set_listener
			{
				org.omg.dds.SubscriberListener _arg0=org.omg.dds.SubscriberListenerHelper.read(_input);
				int _arg1=_input.read_ulong();
				_out = handler.createReply();
				_out.write_long(set_listener(_arg0,_arg1));
				break;
			}
			case 18: // set_qos
			{
				org.omg.dds.SubscriberQos _arg0=org.omg.dds.SubscriberQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_qos(_arg0));
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
