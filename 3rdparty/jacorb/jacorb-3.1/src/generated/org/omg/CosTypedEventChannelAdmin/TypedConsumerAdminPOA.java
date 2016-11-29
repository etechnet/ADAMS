package org.omg.CosTypedEventChannelAdmin;


/**
 * Generated from IDL interface "TypedConsumerAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public abstract class TypedConsumerAdminPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTypedEventChannelAdmin.TypedConsumerAdminOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "obtain_pull_supplier", Integer.valueOf(0));
		m_opsHash.put ( "obtain_typed_pull_supplier", Integer.valueOf(1));
		m_opsHash.put ( "obtain_typed_push_supplier", Integer.valueOf(2));
		m_opsHash.put ( "obtain_push_supplier", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/CosTypedEventChannelAdmin/TypedConsumerAdmin:1.0","IDL:omg.org/CosEventChannelAdmin/ConsumerAdmin:1.0"};
	public org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin __r = org.omg.CosTypedEventChannelAdmin.TypedConsumerAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin __r = org.omg.CosTypedEventChannelAdmin.TypedConsumerAdminHelper.narrow(__o);
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
			case 0: // obtain_pull_supplier
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPullSupplierHelper.write(_out,obtain_pull_supplier());
				break;
			}
			case 1: // obtain_typed_pull_supplier
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CosTypedEventChannelAdmin.TypedProxyPullSupplierHelper.write(_out,obtain_typed_pull_supplier(_arg0));
			}
			catch(org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTypedEventChannelAdmin.InterfaceNotSupportedHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // obtain_typed_push_supplier
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPushSupplierHelper.write(_out,obtain_typed_push_supplier(_arg0));
			}
			catch(org.omg.CosTypedEventChannelAdmin.NoSuchImplementation _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTypedEventChannelAdmin.NoSuchImplementationHelper.write(_out, _ex0);
			}
				break;
			}
			case 3: // obtain_push_supplier
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPushSupplierHelper.write(_out,obtain_push_supplier());
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
