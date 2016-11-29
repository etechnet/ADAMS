package org.omg.dds;


/**
 * Generated from IDL interface "MultiTopic".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class MultiTopicPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.MultiTopicOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_participant", Integer.valueOf(0));
		m_opsHash.put ( "get_expression_parameters", Integer.valueOf(1));
		m_opsHash.put ( "get_subscription_expression", Integer.valueOf(2));
		m_opsHash.put ( "set_expression_parameters", Integer.valueOf(3));
		m_opsHash.put ( "get_type_name", Integer.valueOf(4));
		m_opsHash.put ( "get_name", Integer.valueOf(5));
	}
	private String[] ids = {"IDL:omg.org/dds/MultiTopic:1.0","IDL:omg.org/dds/TopicDescription:1.0"};
	public org.omg.dds.MultiTopic _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.MultiTopic __r = org.omg.dds.MultiTopicHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.MultiTopic _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.MultiTopic __r = org.omg.dds.MultiTopicHelper.narrow(__o);
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
			case 0: // get_participant
			{
				_out = handler.createReply();
				org.omg.dds.DomainParticipantHelper.write(_out,get_participant());
				break;
			}
			case 1: // get_expression_parameters
			{
				_out = handler.createReply();
				org.omg.dds.StringSeqHelper.write(_out,get_expression_parameters());
				break;
			}
			case 2: // get_subscription_expression
			{
				_out = handler.createReply();
				java.lang.String tmpResult1134 = get_subscription_expression();
_out.write_string( tmpResult1134 );
				break;
			}
			case 3: // set_expression_parameters
			{
				java.lang.String[] _arg0=org.omg.dds.StringSeqHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_expression_parameters(_arg0));
				break;
			}
			case 4: // get_type_name
			{
				_out = handler.createReply();
				java.lang.String tmpResult1135 = get_type_name();
_out.write_string( tmpResult1135 );
				break;
			}
			case 5: // get_name
			{
				_out = handler.createReply();
				java.lang.String tmpResult1136 = get_name();
_out.write_string( tmpResult1136 );
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
