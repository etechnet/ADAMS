package org.omg.dds;


/**
 * Generated from IDL interface "TopicDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class TopicDescriptionPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.TopicDescriptionOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_participant", Integer.valueOf(0));
		m_opsHash.put ( "get_type_name", Integer.valueOf(1));
		m_opsHash.put ( "get_name", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:omg.org/dds/TopicDescription:1.0"};
	public org.omg.dds.TopicDescription _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.TopicDescription __r = org.omg.dds.TopicDescriptionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.TopicDescription _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.TopicDescription __r = org.omg.dds.TopicDescriptionHelper.narrow(__o);
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
			case 1: // get_type_name
			{
				_out = handler.createReply();
				java.lang.String tmpResult1127 = get_type_name();
_out.write_string( tmpResult1127 );
				break;
			}
			case 2: // get_name
			{
				_out = handler.createReply();
				java.lang.String tmpResult1128 = get_name();
_out.write_string( tmpResult1128 );
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
