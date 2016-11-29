package org.omg.CosNotifyFilter;


/**
 * Generated from IDL interface "FilterFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class FilterFactoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosNotifyFilter.FilterFactoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "create_mapping_filter", Integer.valueOf(0));
		m_opsHash.put ( "create_filter", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:omg.org/CosNotifyFilter/FilterFactory:1.0"};
	public org.omg.CosNotifyFilter.FilterFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyFilter.FilterFactory __r = org.omg.CosNotifyFilter.FilterFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyFilter.FilterFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyFilter.FilterFactory __r = org.omg.CosNotifyFilter.FilterFactoryHelper.narrow(__o);
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
			case 0: // create_mapping_filter
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				org.omg.CosNotifyFilter.MappingFilterHelper.write(_out,create_mapping_filter(_arg0,_arg1));
			}
			catch(org.omg.CosNotifyFilter.InvalidGrammar _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.InvalidGrammarHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // create_filter
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CosNotifyFilter.FilterHelper.write(_out,create_filter(_arg0));
			}
			catch(org.omg.CosNotifyFilter.InvalidGrammar _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.InvalidGrammarHelper.write(_out, _ex0);
			}
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
