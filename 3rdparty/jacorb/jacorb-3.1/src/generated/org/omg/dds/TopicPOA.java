package org.omg.dds;


/**
 * Generated from IDL interface "Topic".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class TopicPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.TopicOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_inconsistent_topic_status", Integer.valueOf(0));
		m_opsHash.put ( "get_participant", Integer.valueOf(1));
		m_opsHash.put ( "get_type_name", Integer.valueOf(2));
		m_opsHash.put ( "enable", Integer.valueOf(3));
		m_opsHash.put ( "get_name", Integer.valueOf(4));
		m_opsHash.put ( "get_statuscondition", Integer.valueOf(5));
		m_opsHash.put ( "get_status_changes", Integer.valueOf(6));
	}
	private String[] ids = {"IDL:omg.org/dds/Topic:1.0","IDL:omg.org/dds/TopicDescription:1.0","IDL:omg.org/dds/Entity:1.0"};
	public org.omg.dds.Topic _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.Topic __r = org.omg.dds.TopicHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.Topic _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.Topic __r = org.omg.dds.TopicHelper.narrow(__o);
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
			case 0: // get_inconsistent_topic_status
			{
				_out = handler.createReply();
				org.omg.dds.InconsistentTopicStatusHelper.write(_out,get_inconsistent_topic_status());
				break;
			}
			case 1: // get_participant
			{
				_out = handler.createReply();
				org.omg.dds.DomainParticipantHelper.write(_out,get_participant());
				break;
			}
			case 2: // get_type_name
			{
				_out = handler.createReply();
				java.lang.String tmpResult1129 = get_type_name();
_out.write_string( tmpResult1129 );
				break;
			}
			case 3: // enable
			{
				_out = handler.createReply();
				_out.write_long(enable());
				break;
			}
			case 4: // get_name
			{
				_out = handler.createReply();
				java.lang.String tmpResult1130 = get_name();
_out.write_string( tmpResult1130 );
				break;
			}
			case 5: // get_statuscondition
			{
				_out = handler.createReply();
				org.omg.dds.StatusConditionHelper.write(_out,get_statuscondition());
				break;
			}
			case 6: // get_status_changes
			{
				_out = handler.createReply();
				_out.write_ulong(get_status_changes());
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
