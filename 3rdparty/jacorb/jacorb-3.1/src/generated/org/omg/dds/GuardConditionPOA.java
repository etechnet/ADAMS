package org.omg.dds;


/**
 * Generated from IDL interface "GuardCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class GuardConditionPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.GuardConditionOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "set_trigger_value", Integer.valueOf(0));
		m_opsHash.put ( "get_trigger_value", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:omg.org/dds/GuardCondition:1.0","IDL:omg.org/dds/Condition:1.0"};
	public org.omg.dds.GuardCondition _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.GuardCondition __r = org.omg.dds.GuardConditionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.GuardCondition _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.GuardCondition __r = org.omg.dds.GuardConditionHelper.narrow(__o);
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
			case 0: // set_trigger_value
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				set_trigger_value(_arg0);
				break;
			}
			case 1: // get_trigger_value
			{
				_out = handler.createReply();
				_out.write_boolean(get_trigger_value());
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
