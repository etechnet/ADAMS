package org.omg.dds;


/**
 * Generated from IDL interface "StatusCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class StatusConditionPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.StatusConditionOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "set_enabled_statuses", Integer.valueOf(0));
		m_opsHash.put ( "get_trigger_value", Integer.valueOf(1));
		m_opsHash.put ( "get_entity", Integer.valueOf(2));
		m_opsHash.put ( "get_enabled_statuses", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/dds/StatusCondition:1.0","IDL:omg.org/dds/Condition:1.0"};
	public org.omg.dds.StatusCondition _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.StatusCondition __r = org.omg.dds.StatusConditionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.StatusCondition _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.StatusCondition __r = org.omg.dds.StatusConditionHelper.narrow(__o);
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
			case 0: // set_enabled_statuses
			{
				int _arg0=_input.read_ulong();
				_out = handler.createReply();
				_out.write_long(set_enabled_statuses(_arg0));
				break;
			}
			case 1: // get_trigger_value
			{
				_out = handler.createReply();
				_out.write_boolean(get_trigger_value());
				break;
			}
			case 2: // get_entity
			{
				_out = handler.createReply();
				org.omg.dds.EntityHelper.write(_out,get_entity());
				break;
			}
			case 3: // get_enabled_statuses
			{
				_out = handler.createReply();
				_out.write_ulong(get_enabled_statuses());
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
