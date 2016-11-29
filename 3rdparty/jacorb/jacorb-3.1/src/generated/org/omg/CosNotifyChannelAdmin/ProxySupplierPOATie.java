package org.omg.CosNotifyChannelAdmin;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ProxySupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class ProxySupplierPOATie
	extends ProxySupplierPOA
{
	private ProxySupplierOperations _delegate;

	private POA _poa;
	public ProxySupplierPOATie(ProxySupplierOperations delegate)
	{
		_delegate = delegate;
	}
	public ProxySupplierPOATie(ProxySupplierOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyChannelAdmin.ProxySupplier _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyChannelAdmin.ProxySupplier __r = org.omg.CosNotifyChannelAdmin.ProxySupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyChannelAdmin.ProxySupplier _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyChannelAdmin.ProxySupplier __r = org.omg.CosNotifyChannelAdmin.ProxySupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ProxySupplierOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ProxySupplierOperations delegate)
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
	public org.omg.CosNotifyChannelAdmin.ProxyType MyType()
	{
		return _delegate.MyType();
	}

	public void set_qos(org.omg.CosNotification.Property[] qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.set_qos(qos);
	}

	public void validate_event_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.validate_event_qos(required_qos,available_qos);
	}

	public org.omg.CosNotifyFilter.MappingFilter priority_filter()
	{
		return _delegate.priority_filter();
	}

	public org.omg.CosNotifyFilter.MappingFilter lifetime_filter()
	{
		return _delegate.lifetime_filter();
	}

	public void lifetime_filter(org.omg.CosNotifyFilter.MappingFilter a)
	{
		_delegate.lifetime_filter(a);
	}

	public int add_filter(org.omg.CosNotifyFilter.Filter new_filter)
	{
		return _delegate.add_filter(new_filter);
	}

	public void remove_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
_delegate.remove_filter(filter);
	}

	public void remove_all_filters()
	{
_delegate.remove_all_filters();
	}

	public int[] get_all_filters()
	{
		return _delegate.get_all_filters();
	}

	public org.omg.CosNotification.EventType[] obtain_offered_types(org.omg.CosNotifyChannelAdmin.ObtainInfoMode mode)
	{
		return _delegate.obtain_offered_types(mode);
	}

	public org.omg.CosNotifyChannelAdmin.ConsumerAdmin MyAdmin()
	{
		return _delegate.MyAdmin();
	}

	public void priority_filter(org.omg.CosNotifyFilter.MappingFilter a)
	{
		_delegate.priority_filter(a);
	}

	public org.omg.CosNotifyFilter.Filter get_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
		return _delegate.get_filter(filter);
	}

	public org.omg.CosNotification.Property[] get_qos()
	{
		return _delegate.get_qos();
	}

	public void validate_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.validate_qos(required_qos,available_qos);
	}

}
