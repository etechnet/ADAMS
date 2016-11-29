package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "DomainParticipant".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class DomainParticipantPOATie
	extends DomainParticipantPOA
{
	private DomainParticipantOperations _delegate;

	private POA _poa;
	public DomainParticipantPOATie(DomainParticipantOperations delegate)
	{
		_delegate = delegate;
	}
	public DomainParticipantPOATie(DomainParticipantOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.DomainParticipant _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DomainParticipant __r = org.omg.dds.DomainParticipantHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DomainParticipant _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DomainParticipant __r = org.omg.dds.DomainParticipantHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public DomainParticipantOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(DomainParticipantOperations delegate)
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
	public int delete_publisher(org.omg.dds.Publisher p)
	{
		return _delegate.delete_publisher(p);
	}

	public void get_default_subscriber_qos(org.omg.dds.SubscriberQosHolder qos)
	{
_delegate.get_default_subscriber_qos(qos);
	}

	public void get_qos(org.omg.dds.DomainParticipantQosHolder qos)
	{
_delegate.get_qos(qos);
	}

	public org.omg.dds.Subscriber create_subscriber(org.omg.dds.SubscriberQos qos, org.omg.dds.SubscriberListener a_listener)
	{
		return _delegate.create_subscriber(qos,a_listener);
	}

	public int ignore_participant(int handle)
	{
		return _delegate.ignore_participant(handle);
	}

	public int delete_multitopic(org.omg.dds.MultiTopic a_multitopic)
	{
		return _delegate.delete_multitopic(a_multitopic);
	}

	public void get_default_topic_qos(org.omg.dds.TopicQosHolder qos)
	{
_delegate.get_default_topic_qos(qos);
	}

	public org.omg.dds.Topic create_topic(java.lang.String topic_name, java.lang.String type_name, org.omg.dds.TopicQos qos, org.omg.dds.TopicListener a_listener)
	{
		return _delegate.create_topic(topic_name,type_name,qos,a_listener);
	}

	public int ignore_publication(int handle)
	{
		return _delegate.ignore_publication(handle);
	}

	public org.omg.dds.Subscriber get_builtin_subscriber()
	{
		return _delegate.get_builtin_subscriber();
	}

	public int ignore_topic(int handle)
	{
		return _delegate.ignore_topic(handle);
	}

	public org.omg.dds.StatusCondition get_statuscondition()
	{
		return _delegate.get_statuscondition();
	}

	public int ignore_subscription(int handle)
	{
		return _delegate.ignore_subscription(handle);
	}

	public org.omg.dds.TopicDescription lookup_topicdescription(java.lang.String name)
	{
		return _delegate.lookup_topicdescription(name);
	}

	public org.omg.dds.Topic find_topic(java.lang.String topic_name, org.omg.dds.Duration_t timeout)
	{
		return _delegate.find_topic(topic_name,timeout);
	}

	public int set_listener(org.omg.dds.DomainParticipantListener a_listener, int mask)
	{
		return _delegate.set_listener(a_listener,mask);
	}

	public int enable()
	{
		return _delegate.enable();
	}

	public int set_default_subscriber_qos(org.omg.dds.SubscriberQos qos)
	{
		return _delegate.set_default_subscriber_qos(qos);
	}

	public int set_default_publisher_qos(org.omg.dds.PublisherQos qos)
	{
		return _delegate.set_default_publisher_qos(qos);
	}

	public int delete_contentfilteredtopic(org.omg.dds.ContentFilteredTopic a_contentfilteredtopic)
	{
		return _delegate.delete_contentfilteredtopic(a_contentfilteredtopic);
	}

	public void get_default_publisher_qos(org.omg.dds.PublisherQosHolder qos)
	{
_delegate.get_default_publisher_qos(qos);
	}

	public int delete_topic(org.omg.dds.Topic a_topic)
	{
		return _delegate.delete_topic(a_topic);
	}

	public org.omg.dds.MultiTopic create_multitopic(java.lang.String name, java.lang.String type_name, java.lang.String subscription_expression, java.lang.String[] expression_parameters)
	{
		return _delegate.create_multitopic(name,type_name,subscription_expression,expression_parameters);
	}

	public int get_status_changes()
	{
		return _delegate.get_status_changes();
	}

	public org.omg.dds.DomainParticipantListener get_listener()
	{
		return _delegate.get_listener();
	}

	public org.omg.dds.Publisher create_publisher(org.omg.dds.PublisherQos qos, org.omg.dds.PublisherListener a_listener)
	{
		return _delegate.create_publisher(qos,a_listener);
	}

	public int set_qos(org.omg.dds.DomainParticipantQos qos)
	{
		return _delegate.set_qos(qos);
	}

	public int get_domain_id()
	{
		return _delegate.get_domain_id();
	}

	public org.omg.dds.ContentFilteredTopic create_contentfilteredtopic(java.lang.String name, org.omg.dds.Topic related_topic, java.lang.String filter_expression, java.lang.String[] filter_parameters)
	{
		return _delegate.create_contentfilteredtopic(name,related_topic,filter_expression,filter_parameters);
	}

	public int delete_subscriber(org.omg.dds.Subscriber s)
	{
		return _delegate.delete_subscriber(s);
	}

	public int set_default_topic_qos(org.omg.dds.TopicQos qos)
	{
		return _delegate.set_default_topic_qos(qos);
	}

	public int delete_contained_entities()
	{
		return _delegate.delete_contained_entities();
	}

	public void assert_liveliness()
	{
_delegate.assert_liveliness();
	}

}
