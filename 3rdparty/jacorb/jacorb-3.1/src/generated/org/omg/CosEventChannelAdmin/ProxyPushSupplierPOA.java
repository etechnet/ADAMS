package org.omg.CosEventChannelAdmin;


/**
 * Generated from IDL interface "ProxyPushSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class ProxyPushSupplierPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosEventChannelAdmin.ProxyPushSupplierOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "connect_push_consumer", Integer.valueOf(0));
		m_opsHash.put ( "disconnect_push_supplier", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:omg.org/CosEventChannelAdmin/ProxyPushSupplier:1.0","IDL:omg.org/CosEventComm/PushSupplier:1.0"};
	public org.omg.CosEventChannelAdmin.ProxyPushSupplier _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosEventChannelAdmin.ProxyPushSupplier __r = org.omg.CosEventChannelAdmin.ProxyPushSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosEventChannelAdmin.ProxyPushSupplier _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosEventChannelAdmin.ProxyPushSupplier __r = org.omg.CosEventChannelAdmin.ProxyPushSupplierHelper.narrow(__o);
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
			case 0: // connect_push_consumer
			{
			try
			{
				org.omg.CosEventComm.PushConsumer _arg0=org.omg.CosEventComm.PushConsumerHelper.read(_input);
				_out = handler.createReply();
				connect_push_consumer(_arg0);
			}
			catch(org.omg.CosEventChannelAdmin.AlreadyConnected _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosEventChannelAdmin.AlreadyConnectedHelper.write(_out, _ex0);
			}
			catch(org.omg.CosEventChannelAdmin.TypeError _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosEventChannelAdmin.TypeErrorHelper.write(_out, _ex1);
			}
				break;
			}
			case 1: // disconnect_push_supplier
			{
				_out = handler.createReply();
				disconnect_push_supplier();
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
