package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Topic".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class TopicPOATie
	extends TopicPOA
{
	private TopicOperations _delegate;

	private POA _poa;
	public TopicPOATie(TopicOperations delegate)
	{
		_delegate = delegate;
	}
	public TopicPOATie(TopicOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.Topic _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.Topic __r = org.omg.dds.TopicHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.Topic _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.Topic __r = org.omg.dds.TopicHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TopicOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TopicOperations delegate)
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
	public org.omg.dds.InconsistentTopicStatus get_inconsistent_topic_status()
	{
		return _delegate.get_inconsistent_topic_status();
	}

	public org.omg.dds.DomainParticipant get_participant()
	{
		return _delegate.get_participant();
	}

	public java.lang.String get_type_name()
	{
		return _delegate.get_type_name();
	}

	public int enable()
	{
		return _delegate.enable();
	}

	public java.lang.String get_name()
	{
		return _delegate.get_name();
	}

	public org.omg.dds.StatusCondition get_statuscondition()
	{
		return _delegate.get_statuscondition();
	}

	public int get_status_changes()
	{
		return _delegate.get_status_changes();
	}

}
