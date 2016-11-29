package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "DataWriter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class DataWriterPOATie
	extends DataWriterPOA
{
	private DataWriterOperations _delegate;

	private POA _poa;
	public DataWriterPOATie(DataWriterOperations delegate)
	{
		_delegate = delegate;
	}
	public DataWriterPOATie(DataWriterOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.DataWriter _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DataWriter __r = org.omg.dds.DataWriterHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DataWriter _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DataWriter __r = org.omg.dds.DataWriterHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public DataWriterOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(DataWriterOperations delegate)
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
	public void assert_liveliness()
	{
_delegate.assert_liveliness();
	}

	public int set_qos(org.omg.dds.DataWriterQos qos)
	{
		return _delegate.set_qos(qos);
	}

	public org.omg.dds.StatusCondition get_statuscondition()
	{
		return _delegate.get_statuscondition();
	}

	public int get_matched_subscription_data(org.omg.dds.SubscriptionBuiltinTopicDataHolder subscription_data, int subscription_handle)
	{
		return _delegate.get_matched_subscription_data(subscription_data,subscription_handle);
	}

	public org.omg.dds.OfferedIncompatibleQosStatus get_offered_incompatible_qos_status()
	{
		return _delegate.get_offered_incompatible_qos_status();
	}

	public org.omg.dds.Topic get_topic()
	{
		return _delegate.get_topic();
	}

	public int get_status_changes()
	{
		return _delegate.get_status_changes();
	}

	public void get_qos(org.omg.dds.DataWriterQosHolder qos)
	{
_delegate.get_qos(qos);
	}

	public int get_matched_subscriptions(org.omg.dds.InstanceHandleSeqHolder subscription_handles)
	{
		return _delegate.get_matched_subscriptions(subscription_handles);
	}

	public org.omg.dds.LivelinessLostStatus get_liveliness_lost_status()
	{
		return _delegate.get_liveliness_lost_status();
	}

	public org.omg.dds.PublicationMatchStatus get_publication_match_status()
	{
		return _delegate.get_publication_match_status();
	}

	public int enable()
	{
		return _delegate.enable();
	}

	public int set_listener(org.omg.dds.DataWriterListener a_listener, int mask)
	{
		return _delegate.set_listener(a_listener,mask);
	}

	public org.omg.dds.OfferedDeadlineMissedStatus get_offered_deadline_missed_status()
	{
		return _delegate.get_offered_deadline_missed_status();
	}

	public org.omg.dds.Publisher get_publisher()
	{
		return _delegate.get_publisher();
	}

	public org.omg.dds.DataWriterListener get_listener()
	{
		return _delegate.get_listener();
	}

}
