package org.omg.CosNotifyChannelAdmin;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "EventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class EventChannelPOATie
	extends EventChannelPOA
{
	private EventChannelOperations _delegate;

	private POA _poa;
	public EventChannelPOATie(EventChannelOperations delegate)
	{
		_delegate = delegate;
	}
	public EventChannelPOATie(EventChannelOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyChannelAdmin.EventChannel _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyChannelAdmin.EventChannel __r = org.omg.CosNotifyChannelAdmin.EventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyChannelAdmin.EventChannel _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyChannelAdmin.EventChannel __r = org.omg.CosNotifyChannelAdmin.EventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public EventChannelOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(EventChannelOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public org.omg.CosEventChannelAdmin.ConsumerAdmin for_consumers()
	{
		return _delegate.for_consumers();
	}

	public int[] get_all_consumeradmins()
	{
		return _delegate.get_all_consumeradmins();
	}

	public int[] get_all_supplieradmins()
	{
		return _delegate.get_all_supplieradmins();
	}

	public org.omg.CosNotification.Property[] get_qos()
	{
		return _delegate.get_qos();
	}

	public org.omg.CosNotifyChannelAdmin.EventChannelFactory MyFactory()
	{
		return _delegate.MyFactory();
	}

	public org.omg.CosNotifyChannelAdmin.ConsumerAdmin default_consumer_admin()
	{
		return _delegate.default_consumer_admin();
	}

	public org.omg.CosNotifyChannelAdmin.SupplierAdmin new_for_suppliers(org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator op, org.omg.CORBA.IntHolder id)
	{
		return _delegate.new_for_suppliers(op,id);
	}

	public void set_admin(org.omg.CosNotification.Property[] admin) throws org.omg.CosNotification.UnsupportedAdmin
	{
_delegate.set_admin(admin);
	}

	public org.omg.CosNotifyChannelAdmin.SupplierAdmin default_supplier_admin()
	{
		return _delegate.default_supplier_admin();
	}

	public org.omg.CosNotifyChannelAdmin.SupplierAdmin get_supplieradmin(int id) throws org.omg.CosNotifyChannelAdmin.AdminNotFound
	{
		return _delegate.get_supplieradmin(id);
	}

	public org.omg.CosEventChannelAdmin.SupplierAdmin for_suppliers()
	{
		return _delegate.for_suppliers();
	}

	public void set_qos(org.omg.CosNotification.Property[] qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.set_qos(qos);
	}

	public org.omg.CosNotifyFilter.FilterFactory default_filter_factory()
	{
		return _delegate.default_filter_factory();
	}

	public org.omg.CosNotifyChannelAdmin.ConsumerAdmin new_for_consumers(org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator op, org.omg.CORBA.IntHolder id)
	{
		return _delegate.new_for_consumers(op,id);
	}

	public org.omg.CosNotification.Property[] get_admin()
	{
		return _delegate.get_admin();
	}

	public void validate_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.validate_qos(required_qos,available_qos);
	}

	public org.omg.CosNotifyChannelAdmin.ConsumerAdmin get_consumeradmin(int id) throws org.omg.CosNotifyChannelAdmin.AdminNotFound
	{
		return _delegate.get_consumeradmin(id);
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
