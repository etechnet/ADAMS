package org.omg.dds;


/**
 * Generated from IDL interface "DomainParticipant".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class DomainParticipantPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.DomainParticipantOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "delete_publisher", Integer.valueOf(0));
		m_opsHash.put ( "get_default_subscriber_qos", Integer.valueOf(1));
		m_opsHash.put ( "get_qos", Integer.valueOf(2));
		m_opsHash.put ( "create_subscriber", Integer.valueOf(3));
		m_opsHash.put ( "ignore_participant", Integer.valueOf(4));
		m_opsHash.put ( "delete_multitopic", Integer.valueOf(5));
		m_opsHash.put ( "get_default_topic_qos", Integer.valueOf(6));
		m_opsHash.put ( "create_topic", Integer.valueOf(7));
		m_opsHash.put ( "ignore_publication", Integer.valueOf(8));
		m_opsHash.put ( "get_builtin_subscriber", Integer.valueOf(9));
		m_opsHash.put ( "ignore_topic", Integer.valueOf(10));
		m_opsHash.put ( "get_statuscondition", Integer.valueOf(11));
		m_opsHash.put ( "ignore_subscription", Integer.valueOf(12));
		m_opsHash.put ( "lookup_topicdescription", Integer.valueOf(13));
		m_opsHash.put ( "find_topic", Integer.valueOf(14));
		m_opsHash.put ( "set_listener", Integer.valueOf(15));
		m_opsHash.put ( "enable", Integer.valueOf(16));
		m_opsHash.put ( "set_default_subscriber_qos", Integer.valueOf(17));
		m_opsHash.put ( "set_default_publisher_qos", Integer.valueOf(18));
		m_opsHash.put ( "delete_contentfilteredtopic", Integer.valueOf(19));
		m_opsHash.put ( "get_default_publisher_qos", Integer.valueOf(20));
		m_opsHash.put ( "delete_topic", Integer.valueOf(21));
		m_opsHash.put ( "create_multitopic", Integer.valueOf(22));
		m_opsHash.put ( "get_status_changes", Integer.valueOf(23));
		m_opsHash.put ( "get_listener", Integer.valueOf(24));
		m_opsHash.put ( "create_publisher", Integer.valueOf(25));
		m_opsHash.put ( "set_qos", Integer.valueOf(26));
		m_opsHash.put ( "get_domain_id", Integer.valueOf(27));
		m_opsHash.put ( "create_contentfilteredtopic", Integer.valueOf(28));
		m_opsHash.put ( "delete_subscriber", Integer.valueOf(29));
		m_opsHash.put ( "set_default_topic_qos", Integer.valueOf(30));
		m_opsHash.put ( "delete_contained_entities", Integer.valueOf(31));
		m_opsHash.put ( "assert_liveliness", Integer.valueOf(32));
	}
	private String[] ids = {"IDL:omg.org/dds/DomainParticipant:1.0","IDL:omg.org/dds/Entity:1.0"};
	public org.omg.dds.DomainParticipant _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DomainParticipant __r = org.omg.dds.DomainParticipantHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DomainParticipant _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DomainParticipant __r = org.omg.dds.DomainParticipantHelper.narrow(__o);
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
			case 0: // delete_publisher
			{
				org.omg.dds.Publisher _arg0=org.omg.dds.PublisherHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_publisher(_arg0));
				break;
			}
			case 1: // get_default_subscriber_qos
			{
				org.omg.dds.SubscriberQosHolder _arg0= new org.omg.dds.SubscriberQosHolder();
				_out = handler.createReply();
				get_default_subscriber_qos(_arg0);
				org.omg.dds.SubscriberQosHelper.write(_out,_arg0.value);
				break;
			}
			case 2: // get_qos
			{
				org.omg.dds.DomainParticipantQosHolder _arg0= new org.omg.dds.DomainParticipantQosHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				get_qos(_arg0);
				org.omg.dds.DomainParticipantQosHelper.write(_out,_arg0.value);
				break;
			}
			case 3: // create_subscriber
			{
				org.omg.dds.SubscriberQos _arg0=org.omg.dds.SubscriberQosHelper.read(_input);
				org.omg.dds.SubscriberListener _arg1=org.omg.dds.SubscriberListenerHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.SubscriberHelper.write(_out,create_subscriber(_arg0,_arg1));
				break;
			}
			case 4: // ignore_participant
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				_out.write_long(ignore_participant(_arg0));
				break;
			}
			case 5: // delete_multitopic
			{
				org.omg.dds.MultiTopic _arg0=org.omg.dds.MultiTopicHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_multitopic(_arg0));
				break;
			}
			case 6: // get_default_topic_qos
			{
				org.omg.dds.TopicQosHolder _arg0= new org.omg.dds.TopicQosHolder();
				_out = handler.createReply();
				get_default_topic_qos(_arg0);
				org.omg.dds.TopicQosHelper.write(_out,_arg0.value);
				break;
			}
			case 7: // create_topic
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				org.omg.dds.TopicQos _arg2=org.omg.dds.TopicQosHelper.read(_input);
				org.omg.dds.TopicListener _arg3=org.omg.dds.TopicListenerHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.TopicHelper.write(_out,create_topic(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 8: // ignore_publication
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				_out.write_long(ignore_publication(_arg0));
				break;
			}
			case 9: // get_builtin_subscriber
			{
				_out = handler.createReply();
				org.omg.dds.SubscriberHelper.write(_out,get_builtin_subscriber());
				break;
			}
			case 10: // ignore_topic
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				_out.write_long(ignore_topic(_arg0));
				break;
			}
			case 11: // get_statuscondition
			{
				_out = handler.createReply();
				org.omg.dds.StatusConditionHelper.write(_out,get_statuscondition());
				break;
			}
			case 12: // ignore_subscription
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				_out.write_long(ignore_subscription(_arg0));
				break;
			}
			case 13: // lookup_topicdescription
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.dds.TopicDescriptionHelper.write(_out,lookup_topicdescription(_arg0));
				break;
			}
			case 14: // find_topic
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.dds.Duration_t _arg1=org.omg.dds.Duration_tHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.TopicHelper.write(_out,find_topic(_arg0,_arg1));
				break;
			}
			case 15: // set_listener
			{
				org.omg.dds.DomainParticipantListener _arg0=org.omg.dds.DomainParticipantListenerHelper.read(_input);
				int _arg1=_input.read_ulong();
				_out = handler.createReply();
				_out.write_long(set_listener(_arg0,_arg1));
				break;
			}
			case 16: // enable
			{
				_out = handler.createReply();
				_out.write_long(enable());
				break;
			}
			case 17: // set_default_subscriber_qos
			{
				org.omg.dds.SubscriberQos _arg0=org.omg.dds.SubscriberQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_default_subscriber_qos(_arg0));
				break;
			}
			case 18: // set_default_publisher_qos
			{
				org.omg.dds.PublisherQos _arg0=org.omg.dds.PublisherQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_default_publisher_qos(_arg0));
				break;
			}
			case 19: // delete_contentfilteredtopic
			{
				org.omg.dds.ContentFilteredTopic _arg0=org.omg.dds.ContentFilteredTopicHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_contentfilteredtopic(_arg0));
				break;
			}
			case 20: // get_default_publisher_qos
			{
				org.omg.dds.PublisherQosHolder _arg0= new org.omg.dds.PublisherQosHolder();
				_out = handler.createReply();
				get_default_publisher_qos(_arg0);
				org.omg.dds.PublisherQosHelper.write(_out,_arg0.value);
				break;
			}
			case 21: // delete_topic
			{
				org.omg.dds.Topic _arg0=org.omg.dds.TopicHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_topic(_arg0));
				break;
			}
			case 22: // create_multitopic
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				java.lang.String[] _arg3=org.omg.dds.StringSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.MultiTopicHelper.write(_out,create_multitopic(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 23: // get_status_changes
			{
				_out = handler.createReply();
				_out.write_ulong(get_status_changes());
				break;
			}
			case 24: // get_listener
			{
				_out = handler.createReply();
				org.omg.dds.DomainParticipantListenerHelper.write(_out,get_listener());
				break;
			}
			case 25: // create_publisher
			{
				org.omg.dds.PublisherQos _arg0=org.omg.dds.PublisherQosHelper.read(_input);
				org.omg.dds.PublisherListener _arg1=org.omg.dds.PublisherListenerHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.PublisherHelper.write(_out,create_publisher(_arg0,_arg1));
				break;
			}
			case 26: // set_qos
			{
				org.omg.dds.DomainParticipantQos _arg0=org.omg.dds.DomainParticipantQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_qos(_arg0));
				break;
			}
			case 27: // get_domain_id
			{
				_out = handler.createReply();
				_out.write_long(get_domain_id());
				break;
			}
			case 28: // create_contentfilteredtopic
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.dds.Topic _arg1=org.omg.dds.TopicHelper.read(_input);
				java.lang.String _arg2=_input.read_string();
				java.lang.String[] _arg3=org.omg.dds.StringSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.dds.ContentFilteredTopicHelper.write(_out,create_contentfilteredtopic(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 29: // delete_subscriber
			{
				org.omg.dds.Subscriber _arg0=org.omg.dds.SubscriberHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(delete_subscriber(_arg0));
				break;
			}
			case 30: // set_default_topic_qos
			{
				org.omg.dds.TopicQos _arg0=org.omg.dds.TopicQosHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_default_topic_qos(_arg0));
				break;
			}
			case 31: // delete_contained_entities
			{
				_out = handler.createReply();
				_out.write_long(delete_contained_entities());
				break;
			}
			case 32: // assert_liveliness
			{
				_out = handler.createReply();
				assert_liveliness();
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
