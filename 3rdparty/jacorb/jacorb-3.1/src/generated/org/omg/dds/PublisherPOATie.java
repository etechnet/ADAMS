package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Publisher".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class PublisherPOATie
	extends PublisherPOA
{
	private PublisherOperations _delegate;

	private POA _poa;
	public PublisherPOATie(PublisherOperations delegate)
	{
		_delegate = delegate;
	}
	public PublisherPOATie(PublisherOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.Publisher _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.Publisher __r = org.omg.dds.PublisherHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.Publisher _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.Publisher __r = org.omg.dds.PublisherHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public PublisherOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PublisherOperations delegate)
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
	public int set_listener(org.omg.dds.PublisherListener a_listener, int mask)
	{
		return _delegate.set_listener(a_listener,mask);
	}

	public int copy_from_topic_qos(org.omg.dds.DataWriterQosHolder a_datawriter_qos, org.omg.dds.TopicQos a_topic_qos)
	{
		return _delegate.copy_from_topic_qos(a_datawriter_qos,a_topic_qos);
	}

	public void get_qos(org.omg.dds.PublisherQosHolder qos)
	{
_delegate.get_qos(qos);
	}

	public org.omg.dds.DataWriter create_datawriter(org.omg.dds.Topic a_topic, org.omg.dds.DataWriterQos qos, org.omg.dds.DataWriterListener a_listener)
	{
		return _delegate.create_datawriter(a_topic,qos,a_listener);
	}

	public org.omg.dds.StatusCondition get_statuscondition()
	{
		return _delegate.get_statuscondition();
	}

	public org.omg.dds.DataWriter lookup_datawriter(java.lang.String topic_name)
	{
		return _delegate.lookup_datawriter(topic_name);
	}

	public int set_qos(org.omg.dds.PublisherQos qos)
	{
		return _delegate.set_qos(qos);
	}

	public int delete_datawriter(org.omg.dds.DataWriter a_datawriter)
	{
		return _delegate.delete_datawriter(a_datawriter);
	}

	public int enable()
	{
		return _delegate.enable();
	}

	public void get_default_datawriter_qos(org.omg.dds.DataWriterQosHolder qos)
	{
_delegate.get_default_datawriter_qos(qos);
	}

	public int end_coherent_changes()
	{
		return _delegate.end_coherent_changes();
	}

	public int get_status_changes()
	{
		return _delegate.get_status_changes();
	}

	public org.omg.dds.PublisherListener get_listener()
	{
		return _delegate.get_listener();
	}

	public org.omg.dds.DomainParticipant get_participant()
	{
		return _delegate.get_participant();
	}

	public int suspend_publications()
	{
		return _delegate.suspend_publications();
	}

	public int set_default_datawriter_qos(org.omg.dds.DataWriterQos qos)
	{
		return _delegate.set_default_datawriter_qos(qos);
	}

	public int delete_contained_entities()
	{
		return _delegate.delete_contained_entities();
	}

	public int begin_coherent_changes()
	{
		return _delegate.begin_coherent_changes();
	}

	public int resume_publications()
	{
		return _delegate.resume_publications();
	}

}
