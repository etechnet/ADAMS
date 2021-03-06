package org.omg.CosEventChannelAdmin;


/**
 * Generated from IDL interface "EventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class EventChannelPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosEventChannelAdmin.EventChannelOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "for_consumers", Integer.valueOf(0));
		m_opsHash.put ( "destroy", Integer.valueOf(1));
		m_opsHash.put ( "for_suppliers", Integer.valueOf(2));
	}
	private String[] ids = {"IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0"};
	public org.omg.CosEventChannelAdmin.EventChannel _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosEventChannelAdmin.EventChannel __r = org.omg.CosEventChannelAdmin.EventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosEventChannelAdmin.EventChannel _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosEventChannelAdmin.EventChannel __r = org.omg.CosEventChannelAdmin.EventChannelHelper.narrow(__o);
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
			case 0: // for_consumers
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ConsumerAdminHelper.write(_out,for_consumers());
				break;
			}
			case 1: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 2: // for_suppliers
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.SupplierAdminHelper.write(_out,for_suppliers());
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
