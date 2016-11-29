package org.omg.CosNotifyChannelAdminAck;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "SequenceProxyPullSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public class SequenceProxyPullSupplierAckPOATie
	extends SequenceProxyPullSupplierAckPOA
{
	private SequenceProxyPullSupplierAckOperations _delegate;

	private POA _poa;
	public SequenceProxyPullSupplierAckPOATie(SequenceProxyPullSupplierAckOperations delegate)
	{
		_delegate = delegate;
	}
	public SequenceProxyPullSupplierAckPOATie(SequenceProxyPullSupplierAckOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyChannelAdminAck.SequenceProxyPullSupplierAck _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyChannelAdminAck.SequenceProxyPullSupplierAck __r = org.omg.CosNotifyChannelAdminAck.SequenceProxyPullSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyChannelAdminAck.SequenceProxyPullSupplierAck _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyChannelAdminAck.SequenceProxyPullSupplierAck __r = org.omg.CosNotifyChannelAdminAck.SequenceProxyPullSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public SequenceProxyPullSupplierAckOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(SequenceProxyPullSupplierAckOperations delegate)
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
	public int[] get_all_filters()
	{
		return _delegate.get_all_filters();
	}

	public org.omg.CosNotification.Property[] get_qos()
	{
		return _delegate.get_qos();
	}

	public org.omg.CosNotifyFilter.MappingFilter lifetime_filter()
	{
		return _delegate.lifetime_filter();
	}

	public org.omg.CosNotifyChannelAdmin.ProxyType MyType()
	{
		return _delegate.MyType();
	}

	public void connect_sequence_pull_consumer(org.omg.CosNotifyComm.SequencePullConsumer pull_consumer) throws org.omg.CosEventChannelAdmin.AlreadyConnected
	{
_delegate.connect_sequence_pull_consumer(pull_consumer);
	}

	public org.omg.CosNotifyFilter.MappingFilter priority_filter()
	{
		return _delegate.priority_filter();
	}

	public org.omg.CosNotifyFilter.Filter get_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
		return _delegate.get_filter(filter);
	}

	public void set_qos(org.omg.CosNotification.Property[] qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.set_qos(qos);
	}

	public org.omg.CosNotification.EventType[] obtain_offered_types(org.omg.CosNotifyChannelAdmin.ObtainInfoMode mode)
	{
		return _delegate.obtain_offered_types(mode);
	}

	public int add_filter(org.omg.CosNotifyFilter.Filter new_filter)
	{
		return _delegate.add_filter(new_filter);
	}

	public void subscription_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType
	{
_delegate.subscription_change(added,removed);
	}

	public void priority_filter(org.omg.CosNotifyFilter.MappingFilter a)
	{
		_delegate.priority_filter(a);
	}

	public void validate_event_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.validate_event_qos(required_qos,available_qos);
	}

	public void remove_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
_delegate.remove_filter(filter);
	}

	public void remove_all_filters()
	{
_delegate.remove_all_filters();
	}

	public void lifetime_filter(org.omg.CosNotifyFilter.MappingFilter a)
	{
		_delegate.lifetime_filter(a);
	}

	public void validate_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.validate_qos(required_qos,available_qos);
	}

	public org.omg.CosNotification.StructuredEvent[] pull_structured_events(int max_number) throws org.omg.CosEventComm.Disconnected
	{
		return _delegate.pull_structured_events(max_number);
	}

	public void disconnect_sequence_pull_supplier()
	{
_delegate.disconnect_sequence_pull_supplier();
	}

	public org.omg.CosNotification.StructuredEvent[] try_pull_structured_events(int max_number, org.omg.CORBA.BooleanHolder has_event) throws org.omg.CosEventComm.Disconnected
	{
		return _delegate.try_pull_structured_events(max_number,has_event);
	}

	public void acknowledge(int[] sequence_numbers)
	{
_delegate.acknowledge(sequence_numbers);
	}

	public org.omg.CosNotifyChannelAdmin.ConsumerAdmin MyAdmin()
	{
		return _delegate.MyAdmin();
	}

}
