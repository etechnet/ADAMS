package org.omg.CosTypedEventComm;


/**
 * Generated from IDL interface "TypedPullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public abstract class TypedPullSupplierPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTypedEventComm.TypedPullSupplierOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "disconnect_pull_supplier", Integer.valueOf(0));
		m_opsHash.put ( "pull", Integer.valueOf(1));
		m_opsHash.put ( "get_typed_supplier", Integer.valueOf(2));
		m_opsHash.put ( "try_pull", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/CosTypedEventComm/TypedPullSupplier:1.0","IDL:omg.org/CosEventComm/PullSupplier:1.0"};
	public org.omg.CosTypedEventComm.TypedPullSupplier _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedEventComm.TypedPullSupplier __r = org.omg.CosTypedEventComm.TypedPullSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedEventComm.TypedPullSupplier _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedEventComm.TypedPullSupplier __r = org.omg.CosTypedEventComm.TypedPullSupplierHelper.narrow(__o);
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
			case 0: // disconnect_pull_supplier
			{
				_out = handler.createReply();
				disconnect_pull_supplier();
				break;
			}
			case 1: // pull
			{
			try
			{
				_out = handler.createReply();
				_out.write_any(pull());
			}
			catch(org.omg.CosEventComm.Disconnected _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosEventComm.DisconnectedHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // get_typed_supplier
			{
				_out = handler.createReply();
				_out.write_Object(get_typed_supplier());
				break;
			}
			case 3: // try_pull
			{
			try
			{
				org.omg.CORBA.BooleanHolder _arg0= new org.omg.CORBA.BooleanHolder();
				_out = handler.createReply();
				_out.write_any(try_pull(_arg0));
				_out.write_boolean(_arg0.value);
			}
			catch(org.omg.CosEventComm.Disconnected _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosEventComm.DisconnectedHelper.write(_out, _ex0);
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
