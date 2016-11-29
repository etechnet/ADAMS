package org.jacorb.events;


/**
 * Generated from IDL interface "JacORBEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.03.02
 */

public abstract class JacORBEventChannelPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.jacorb.events.JacORBEventChannelOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "obtain_pull_supplier", Integer.valueOf(0));
		m_opsHash.put ( "obtain_push_consumer", Integer.valueOf(1));
		m_opsHash.put ( "obtain_pull_consumer", Integer.valueOf(2));
		m_opsHash.put ( "for_consumers", Integer.valueOf(3));
		m_opsHash.put ( "obtain_push_supplier", Integer.valueOf(4));
		m_opsHash.put ( "destroy", Integer.valueOf(5));
		m_opsHash.put ( "for_suppliers", Integer.valueOf(6));
	}
	private String[] ids = {"IDL:org/jacorb/events/JacORBEventChannel:1.0","IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0","IDL:omg.org/CosEventChannelAdmin/ConsumerAdmin:1.0","IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0"};
	public org.jacorb.events.JacORBEventChannel _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.events.JacORBEventChannel __r = org.jacorb.events.JacORBEventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.events.JacORBEventChannel _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.events.JacORBEventChannel __r = org.jacorb.events.JacORBEventChannelHelper.narrow(__o);
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
			case 1: // obtain_push_consumer
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPushConsumerHelper.write(_out,obtain_push_consumer());
				break;
			}
			case 2: // obtain_pull_consumer
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPullConsumerHelper.write(_out,obtain_pull_consumer());
				break;
			}
			case 3: // for_consumers
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ConsumerAdminHelper.write(_out,for_consumers());
				break;
			}
			case 4: // obtain_push_supplier
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPushSupplierHelper.write(_out,obtain_push_supplier());
				break;
			}
			case 5: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 6: // for_suppliers
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
