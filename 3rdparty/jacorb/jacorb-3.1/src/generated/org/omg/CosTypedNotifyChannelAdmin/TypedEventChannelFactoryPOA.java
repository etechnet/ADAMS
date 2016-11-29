package org.omg.CosTypedNotifyChannelAdmin;


/**
 * Generated from IDL interface "TypedEventChannelFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public abstract class TypedEventChannelFactoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_typed_event_channel", Integer.valueOf(0));
		m_opsHash.put ( "get_all_typed_channels", Integer.valueOf(1));
		m_opsHash.put ( "create_typed_channel", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:omg.org/CosTypedNotifyChannelAdmin/TypedEventChannelFactory:1.0"};
	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory __r = org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory __r = org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactoryHelper.narrow(__o);
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
			case 0: // get_typed_event_channel
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelHelper.write(_out,get_typed_event_channel(_arg0));
			}
			catch(org.omg.CosNotifyChannelAdmin.ChannelNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.ChannelNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // get_all_typed_channels
			{
				_out = handler.createReply();
				org.omg.CosNotifyChannelAdmin.ChannelIDSeqHelper.write(_out,get_all_typed_channels());
				break;
			}
			case 2: // create_typed_channel
			{
			try
			{
				org.omg.CosNotification.Property[] _arg0=org.omg.CosNotification.PropertySeqHelper.read(_input);
				org.omg.CosNotification.Property[] _arg1=org.omg.CosNotification.PropertySeqHelper.read(_input);
				org.omg.CORBA.IntHolder _arg2= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelHelper.write(_out,create_typed_channel(_arg0,_arg1,_arg2));
				_out.write_long(_arg2.value);
			}
			catch(org.omg.CosNotification.UnsupportedAdmin _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotification.UnsupportedAdminHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNotification.UnsupportedQoS _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotification.UnsupportedQoSHelper.write(_out, _ex1);
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
