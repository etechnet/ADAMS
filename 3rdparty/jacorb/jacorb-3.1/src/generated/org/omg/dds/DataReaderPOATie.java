package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "DataReader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class DataReaderPOATie
	extends DataReaderPOA
{
	private DataReaderOperations _delegate;

	private POA _poa;
	public DataReaderPOATie(DataReaderOperations delegate)
	{
		_delegate = delegate;
	}
	public DataReaderPOATie(DataReaderOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.DataReader _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DataReader __r = org.omg.dds.DataReaderHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DataReader _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DataReader __r = org.omg.dds.DataReaderHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public DataReaderOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(DataReaderOperations delegate)
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
	public int set_listener(org.omg.dds.DataReaderListener a_listener, int mask)
	{
		return _delegate.set_listener(a_listener,mask);
	}

	public void get_qos(org.omg.dds.DataReaderQosHolder qos)
	{
_delegate.get_qos(qos);
	}

	public int get_matched_publications(org.omg.dds.InstanceHandleSeqHolder publication_handles)
	{
		return _delegate.get_matched_publications(publication_handles);
	}

	public org.omg.dds.StatusCondition get_statuscondition()
	{
		return _delegate.get_statuscondition();
	}

	public int set_qos(org.omg.dds.DataReaderQos qos)
	{
		return _delegate.set_qos(qos);
	}

	public int enable()
	{
		return _delegate.enable();
	}

	public org.omg.dds.TopicDescription get_topicdescription()
	{
		return _delegate.get_topicdescription();
	}

	public org.omg.dds.LivelinessChangedStatus get_liveliness_changed_status()
	{
		return _delegate.get_liveliness_changed_status();
	}

	public org.omg.dds.RequestedIncompatibleQosStatus get_requested_incompatible_qos_status()
	{
		return _delegate.get_requested_incompatible_qos_status();
	}

	public int wait_for_historical_data(org.omg.dds.Duration_t max_wait)
	{
		return _delegate.wait_for_historical_data(max_wait);
	}

	public org.omg.dds.QueryCondition create_querycondition(int sample_states, int view_states, int instance_states, java.lang.String query_expression, java.lang.String[] query_parameters)
	{
		return _delegate.create_querycondition(sample_states,view_states,instance_states,query_expression,query_parameters);
	}

	public org.omg.dds.ReadCondition create_readcondition(int sample_states, int view_states, int instance_states)
	{
		return _delegate.create_readcondition(sample_states,view_states,instance_states);
	}

	public int get_status_changes()
	{
		return _delegate.get_status_changes();
	}

	public int get_matched_publication_data(org.omg.dds.PublicationBuiltinTopicDataHolder publication_data, int publication_handle)
	{
		return _delegate.get_matched_publication_data(publication_data,publication_handle);
	}

	public org.omg.dds.DataReaderListener get_listener()
	{
		return _delegate.get_listener();
	}

	public org.omg.dds.Subscriber get_subscriber()
	{
		return _delegate.get_subscriber();
	}

	public int delete_readcondition(org.omg.dds.ReadCondition a_condition)
	{
		return _delegate.delete_readcondition(a_condition);
	}

	public int delete_contained_entities()
	{
		return _delegate.delete_contained_entities();
	}

	public org.omg.dds.SampleRejectedStatus get_sample_rejected_status()
	{
		return _delegate.get_sample_rejected_status();
	}

	public org.omg.dds.SampleLostStatus get_sample_lost_status()
	{
		return _delegate.get_sample_lost_status();
	}

	public org.omg.dds.RequestedDeadlineMissedStatus get_requested_deadline_missed_status()
	{
		return _delegate.get_requested_deadline_missed_status();
	}

	public void take_instance_from_subscriber()
	{
_delegate.take_instance_from_subscriber();
	}

	public org.omg.dds.SubscriptionMatchStatus get_subscription_match_status()
	{
		return _delegate.get_subscription_match_status();
	}

}
