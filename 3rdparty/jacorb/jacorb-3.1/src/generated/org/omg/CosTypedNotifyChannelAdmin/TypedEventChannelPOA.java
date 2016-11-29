package org.omg.CosTypedNotifyChannelAdmin;


/**
 * Generated from IDL interface "TypedEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public abstract class TypedEventChannelPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "for_consumers", Integer.valueOf(0));
		m_opsHash.put ( "get_all_consumeradmins", Integer.valueOf(1));
		m_opsHash.put ( "get_all_supplieradmins", Integer.valueOf(2));
		m_opsHash.put ( "get_qos", Integer.valueOf(3));
		m_opsHash.put ( "_get_MyFactory", Integer.valueOf(4));
		m_opsHash.put ( "_get_default_consumer_admin", Integer.valueOf(5));
		m_opsHash.put ( "new_for_typed_notification_suppliers", Integer.valueOf(6));
		m_opsHash.put ( "set_admin", Integer.valueOf(7));
		m_opsHash.put ( "_get_default_supplier_admin", Integer.valueOf(8));
		m_opsHash.put ( "get_supplieradmin", Integer.valueOf(9));
		m_opsHash.put ( "for_suppliers", Integer.valueOf(10));
		m_opsHash.put ( "set_qos", Integer.valueOf(11));
		m_opsHash.put ( "_get_default_filter_factory", Integer.valueOf(12));
		m_opsHash.put ( "new_for_typed_notification_consumers", Integer.valueOf(13));
		m_opsHash.put ( "get_admin", Integer.valueOf(14));
		m_opsHash.put ( "validate_qos", Integer.valueOf(15));
		m_opsHash.put ( "get_consumeradmin", Integer.valueOf(16));
		m_opsHash.put ( "destroy", Integer.valueOf(17));
	}
	private String[] ids = {"IDL:omg.org/CosTypedNotifyChannelAdmin/TypedEventChannel:1.0","IDL:omg.org/CosNotification/QoSAdmin:1.0","IDL:omg.org/CosNotification/AdminPropertiesAdmin:1.0","IDL:omg.org/CosTypedEventChannelAdmin/TypedEventChannel:1.0"};
	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannel _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedNotifyChannelAdmin.TypedEventChannel __r = org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannel _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedNotifyChannelAdmin.TypedEventChannel __r = org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelHelper.narrow(__o);
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
				org.omg.CosTypedEventChannelAdmin.TypedConsumerAdminHelper.write(_out,for_consumers());
				break;
			}
			case 1: // get_all_consumeradmins
			{
				_out = handler.createReply();
				org.omg.CosNotifyChannelAdmin.AdminIDSeqHelper.write(_out,get_all_consumeradmins());
				break;
			}
			case 2: // get_all_supplieradmins
			{
				_out = handler.createReply();
				org.omg.CosNotifyChannelAdmin.AdminIDSeqHelper.write(_out,get_all_supplieradmins());
				break;
			}
			case 3: // get_qos
			{
				_out = handler.createReply();
				org.omg.CosNotification.PropertySeqHelper.write(_out,get_qos());
				break;
			}
			case 4: // _get_MyFactory
			{
			_out = handler.createReply();
			org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactoryHelper.write(_out,MyFactory());
				break;
			}
			case 5: // _get_default_consumer_admin
			{
			_out = handler.createReply();
			org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdminHelper.write(_out,default_consumer_admin());
				break;
			}
			case 6: // new_for_typed_notification_suppliers
			{
				org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator _arg0=org.omg.CosNotifyChannelAdmin.InterFilterGroupOperatorHelper.read(_input);
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminHelper.write(_out,new_for_typed_notification_suppliers(_arg0,_arg1));
				_out.write_long(_arg1.value);
				break;
			}
			case 7: // set_admin
			{
			try
			{
				org.omg.CosNotification.Property[] _arg0=org.omg.CosNotification.PropertySeqHelper.read(_input);
				_out = handler.createReply();
				set_admin(_arg0);
			}
			catch(org.omg.CosNotification.UnsupportedAdmin _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotification.UnsupportedAdminHelper.write(_out, _ex0);
			}
				break;
			}
			case 8: // _get_default_supplier_admin
			{
			_out = handler.createReply();
			org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminHelper.write(_out,default_supplier_admin());
				break;
			}
			case 9: // get_supplieradmin
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminHelper.write(_out,get_supplieradmin(_arg0));
			}
			catch(org.omg.CosNotifyChannelAdmin.AdminNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.AdminNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 10: // for_suppliers
			{
				_out = handler.createReply();
				org.omg.CosTypedEventChannelAdmin.TypedSupplierAdminHelper.write(_out,for_suppliers());
				break;
			}
			case 11: // set_qos
			{
			try
			{
				org.omg.CosNotification.Property[] _arg0=org.omg.CosNotification.PropertySeqHelper.read(_input);
				_out = handler.createReply();
				set_qos(_arg0);
			}
			catch(org.omg.CosNotification.UnsupportedQoS _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotification.UnsupportedQoSHelper.write(_out, _ex0);
			}
				break;
			}
			case 12: // _get_default_filter_factory
			{
			_out = handler.createReply();
			org.omg.CosNotifyFilter.FilterFactoryHelper.write(_out,default_filter_factory());
				break;
			}
			case 13: // new_for_typed_notification_consumers
			{
				org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator _arg0=org.omg.CosNotifyChannelAdmin.InterFilterGroupOperatorHelper.read(_input);
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdminHelper.write(_out,new_for_typed_notification_consumers(_arg0,_arg1));
				_out.write_long(_arg1.value);
				break;
			}
			case 14: // get_admin
			{
				_out = handler.createReply();
				org.omg.CosNotification.PropertySeqHelper.write(_out,get_admin());
				break;
			}
			case 15: // validate_qos
			{
			try
			{
				org.omg.CosNotification.Property[] _arg0=org.omg.CosNotification.PropertySeqHelper.read(_input);
				org.omg.CosNotification.NamedPropertyRangeSeqHolder _arg1= new org.omg.CosNotification.NamedPropertyRangeSeqHolder();
				_out = handler.createReply();
				validate_qos(_arg0,_arg1);
				org.omg.CosNotification.NamedPropertyRangeSeqHelper.write(_out,_arg1.value);
			}
			catch(org.omg.CosNotification.UnsupportedQoS _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotification.UnsupportedQoSHelper.write(_out, _ex0);
			}
				break;
			}
			case 16: // get_consumeradmin
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdminHelper.write(_out,get_consumeradmin(_arg0));
			}
			catch(org.omg.CosNotifyChannelAdmin.AdminNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.AdminNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 17: // destroy
			{
				_out = handler.createReply();
				destroy();
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
