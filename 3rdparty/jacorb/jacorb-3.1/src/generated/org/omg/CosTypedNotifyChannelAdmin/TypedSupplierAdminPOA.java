package org.omg.CosTypedNotifyChannelAdmin;


/**
 * Generated from IDL interface "TypedSupplierAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public abstract class TypedSupplierAdminPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "obtain_typed_notification_push_consumer", Integer.valueOf(0));
		m_opsHash.put ( "get_all_filters", Integer.valueOf(1));
		m_opsHash.put ( "obtain_pull_consumer", Integer.valueOf(2));
		m_opsHash.put ( "_get_pull_consumers", Integer.valueOf(3));
		m_opsHash.put ( "_get_MyID", Integer.valueOf(4));
		m_opsHash.put ( "get_qos", Integer.valueOf(5));
		m_opsHash.put ( "obtain_push_consumer", Integer.valueOf(6));
		m_opsHash.put ( "obtain_typed_notification_pull_consumer", Integer.valueOf(7));
		m_opsHash.put ( "obtain_typed_pull_consumer", Integer.valueOf(8));
		m_opsHash.put ( "obtain_typed_push_consumer", Integer.valueOf(9));
		m_opsHash.put ( "get_filter", Integer.valueOf(10));
		m_opsHash.put ( "_get_push_consumers", Integer.valueOf(11));
		m_opsHash.put ( "_get_MyOperator", Integer.valueOf(12));
		m_opsHash.put ( "obtain_notification_pull_consumer", Integer.valueOf(13));
		m_opsHash.put ( "set_qos", Integer.valueOf(14));
		m_opsHash.put ( "get_proxy_consumer", Integer.valueOf(15));
		m_opsHash.put ( "add_filter", Integer.valueOf(16));
		m_opsHash.put ( "obtain_notification_push_consumer", Integer.valueOf(17));
		m_opsHash.put ( "remove_filter", Integer.valueOf(18));
		m_opsHash.put ( "offer_change", Integer.valueOf(19));
		m_opsHash.put ( "remove_all_filters", Integer.valueOf(20));
		m_opsHash.put ( "_get_MyChannel", Integer.valueOf(21));
		m_opsHash.put ( "validate_qos", Integer.valueOf(22));
		m_opsHash.put ( "destroy", Integer.valueOf(23));
	}
	private String[] ids = {"IDL:omg.org/CosTypedNotifyChannelAdmin/TypedSupplierAdmin:1.0","IDL:omg.org/CosTypedEventChannelAdmin/TypedSupplierAdmin:1.0","IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0","IDL:omg.org/CosNotifyComm/NotifyPublish:1.0","IDL:omg.org/CosNotifyFilter/FilterAdmin:1.0","IDL:omg.org/CosNotification/QoSAdmin:1.0","IDL:omg.org/CosNotifyChannelAdmin/SupplierAdmin:1.0"};
	public org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin __r = org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin __r = org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminHelper.narrow(__o);
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
			case 0: // obtain_typed_notification_push_consumer
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedProxyPushConsumerHelper.write(_out,obtain_typed_notification_push_consumer(_arg0,_arg1));
				_out.write_long(_arg1.value);
			}
			catch(org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTypedEventChannelAdmin.InterfaceNotSupportedHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNotifyChannelAdmin.AdminLimitExceeded _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.write(_out, _ex1);
			}
				break;
			}
			case 1: // get_all_filters
			{
				_out = handler.createReply();
				org.omg.CosNotifyFilter.FilterIDSeqHelper.write(_out,get_all_filters());
				break;
			}
			case 2: // obtain_pull_consumer
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPullConsumerHelper.write(_out,obtain_pull_consumer());
				break;
			}
			case 3: // _get_pull_consumers
			{
			_out = handler.createReply();
			org.omg.CosNotifyChannelAdmin.ProxyIDSeqHelper.write(_out,pull_consumers());
				break;
			}
			case 4: // _get_MyID
			{
			_out = handler.createReply();
			_out.write_long(MyID());
				break;
			}
			case 5: // get_qos
			{
				_out = handler.createReply();
				org.omg.CosNotification.PropertySeqHelper.write(_out,get_qos());
				break;
			}
			case 6: // obtain_push_consumer
			{
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPushConsumerHelper.write(_out,obtain_push_consumer());
				break;
			}
			case 7: // obtain_typed_notification_pull_consumer
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumerHelper.write(_out,obtain_typed_notification_pull_consumer(_arg0,_arg1));
				_out.write_long(_arg1.value);
			}
			catch(org.omg.CosTypedEventChannelAdmin.NoSuchImplementation _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTypedEventChannelAdmin.NoSuchImplementationHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNotifyChannelAdmin.AdminLimitExceeded _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.write(_out, _ex1);
			}
				break;
			}
			case 8: // obtain_typed_pull_consumer
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CosEventChannelAdmin.ProxyPullConsumerHelper.write(_out,obtain_typed_pull_consumer(_arg0));
			}
			catch(org.omg.CosTypedEventChannelAdmin.NoSuchImplementation _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTypedEventChannelAdmin.NoSuchImplementationHelper.write(_out, _ex0);
			}
				break;
			}
			case 9: // obtain_typed_push_consumer
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumerHelper.write(_out,obtain_typed_push_consumer(_arg0));
			}
			catch(org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTypedEventChannelAdmin.InterfaceNotSupportedHelper.write(_out, _ex0);
			}
				break;
			}
			case 10: // get_filter
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				org.omg.CosNotifyFilter.FilterHelper.write(_out,get_filter(_arg0));
			}
			catch(org.omg.CosNotifyFilter.FilterNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.FilterNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 11: // _get_push_consumers
			{
			_out = handler.createReply();
			org.omg.CosNotifyChannelAdmin.ProxyIDSeqHelper.write(_out,push_consumers());
				break;
			}
			case 12: // _get_MyOperator
			{
			_out = handler.createReply();
			org.omg.CosNotifyChannelAdmin.InterFilterGroupOperatorHelper.write(_out,MyOperator());
				break;
			}
			case 13: // obtain_notification_pull_consumer
			{
			try
			{
				org.omg.CosNotifyChannelAdmin.ClientType _arg0=org.omg.CosNotifyChannelAdmin.ClientTypeHelper.read(_input);
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosNotifyChannelAdmin.ProxyConsumerHelper.write(_out,obtain_notification_pull_consumer(_arg0,_arg1));
				_out.write_long(_arg1.value);
			}
			catch(org.omg.CosNotifyChannelAdmin.AdminLimitExceeded _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.write(_out, _ex0);
			}
				break;
			}
			case 14: // set_qos
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
			case 15: // get_proxy_consumer
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				org.omg.CosNotifyChannelAdmin.ProxyConsumerHelper.write(_out,get_proxy_consumer(_arg0));
			}
			catch(org.omg.CosNotifyChannelAdmin.ProxyNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.ProxyNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 16: // add_filter
			{
				org.omg.CosNotifyFilter.Filter _arg0=org.omg.CosNotifyFilter.FilterHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(add_filter(_arg0));
				break;
			}
			case 17: // obtain_notification_push_consumer
			{
			try
			{
				org.omg.CosNotifyChannelAdmin.ClientType _arg0=org.omg.CosNotifyChannelAdmin.ClientTypeHelper.read(_input);
				org.omg.CORBA.IntHolder _arg1= new org.omg.CORBA.IntHolder();
				_out = handler.createReply();
				org.omg.CosNotifyChannelAdmin.ProxyConsumerHelper.write(_out,obtain_notification_push_consumer(_arg0,_arg1));
				_out.write_long(_arg1.value);
			}
			catch(org.omg.CosNotifyChannelAdmin.AdminLimitExceeded _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.write(_out, _ex0);
			}
				break;
			}
			case 18: // remove_filter
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				remove_filter(_arg0);
			}
			catch(org.omg.CosNotifyFilter.FilterNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.FilterNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 19: // offer_change
			{
			try
			{
				org.omg.CosNotification.EventType[] _arg0=org.omg.CosNotification.EventTypeSeqHelper.read(_input);
				org.omg.CosNotification.EventType[] _arg1=org.omg.CosNotification.EventTypeSeqHelper.read(_input);
				_out = handler.createReply();
				offer_change(_arg0,_arg1);
			}
			catch(org.omg.CosNotifyComm.InvalidEventType _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyComm.InvalidEventTypeHelper.write(_out, _ex0);
			}
				break;
			}
			case 20: // remove_all_filters
			{
				_out = handler.createReply();
				remove_all_filters();
				break;
			}
			case 21: // _get_MyChannel
			{
			_out = handler.createReply();
			org.omg.CosNotifyChannelAdmin.EventChannelHelper.write(_out,MyChannel());
				break;
			}
			case 22: // validate_qos
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
			case 23: // destroy
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
