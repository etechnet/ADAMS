package org.omg.CosTypedEventComm;


/**
 * Generated from IDL interface "TypedPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public abstract class TypedPushConsumerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTypedEventComm.TypedPushConsumerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_typed_consumer", Integer.valueOf(0));
		m_opsHash.put ( "disconnect_push_consumer", Integer.valueOf(1));
		m_opsHash.put ( "push", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:omg.org/CosTypedEventComm/TypedPushConsumer:1.0","IDL:omg.org/CosEventComm/PushConsumer:1.0"};
	public org.omg.CosTypedEventComm.TypedPushConsumer _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedEventComm.TypedPushConsumer __r = org.omg.CosTypedEventComm.TypedPushConsumerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedEventComm.TypedPushConsumer _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedEventComm.TypedPushConsumer __r = org.omg.CosTypedEventComm.TypedPushConsumerHelper.narrow(__o);
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
			case 0: // get_typed_consumer
			{
				_out = handler.createReply();
				_out.write_Object(get_typed_consumer());
				break;
			}
			case 1: // disconnect_push_consumer
			{
				_out = handler.createReply();
				disconnect_push_consumer();
				break;
			}
			case 2: // push
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				push(_arg0);
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
