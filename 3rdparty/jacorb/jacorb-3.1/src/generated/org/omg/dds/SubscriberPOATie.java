package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Subscriber".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class SubscriberPOATie
	extends SubscriberPOA
{
	private SubscriberOperations _delegate;

	private POA _poa;
	public SubscriberPOATie(SubscriberOperations delegate)
	{
		_delegate = delegate;
	}
	public SubscriberPOATie(SubscriberOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.Subscriber _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.Subscriber __r = org.omg.dds.SubscriberHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.Subscriber _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.Subscriber __r = org.omg.dds.SubscriberHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public SubscriberOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(SubscriberOperations delegate)
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
	public int set_default_datareader_qos(org.omg.dds.DataReaderQos qos)
	{
		return _delegate.set_default_datareader_qos(qos);
	}

	public int begin_access()
	{
		return _delegate.begin_access();
	}

	public org.omg.dds.StatusCondition get_statuscondition()
	{
		return _delegate.get_statuscondition();
	}

	public void get_default_datareader_qos(org.omg.dds.DataReaderQosHolder qos)
	{
_delegate.get_default_datareader_qos(qos);
	}

	public int get_datareaders(org.omg.dds.DataReaderSeqHolder readers, int sample_states, int view_states, int instance_states)
	{
		return _delegate.get_datareaders(readers,sample_states,view_states,instance_states);
	}

	public int enable()
	{
		return _delegate.enable();
	}

	public void notify_datareaders()
	{
_delegate.notify_datareaders();
	}

	public org.omg.dds.DataReader create_datareader(org.omg.dds.TopicDescription a_topic, org.omg.dds.DataReaderQos qos, org.omg.dds.DataReaderListener a_listener)
	{
		return _delegate.create_datareader(a_topic,qos,a_listener);
	}

	public int end_access()
	{
		return _delegate.end_access();
	}

	public int get_status_changes()
	{
		return _delegate.get_status_changes();
	}

	public org.omg.dds.SubscriberListener get_listener()
	{
		return _delegate.get_listener();
	}

	public int copy_from_topic_qos(org.omg.dds.DataReaderQosHolder a_datareader_qos, org.omg.dds.TopicQos a_topic_qos)
	{
		return _delegate.copy_from_topic_qos(a_datareader_qos,a_topic_qos);
	}

	public org.omg.dds.DomainParticipant get_participant()
	{
		return _delegate.get_participant();
	}

	public void get_qos(org.omg.dds.SubscriberQosHolder qos)
	{
_delegate.get_qos(qos);
	}

	public int delete_contained_entities()
	{
		return _delegate.delete_contained_entities();
	}

	public int delete_datareader(org.omg.dds.DataReader a_datareader)
	{
		return _delegate.delete_datareader(a_datareader);
	}

	public org.omg.dds.DataReader lookup_datareader(java.lang.String topic_name)
	{
		return _delegate.lookup_datareader(topic_name);
	}

	public int set_listener(org.omg.dds.SubscriberListener a_listener, int mask)
	{
		return _delegate.set_listener(a_listener,mask);
	}

	public int set_qos(org.omg.dds.SubscriberQos qos)
	{
		return _delegate.set_qos(qos);
	}

}
