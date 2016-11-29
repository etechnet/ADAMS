package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "DomainParticipantFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class DomainParticipantFactoryPOATie
	extends DomainParticipantFactoryPOA
{
	private DomainParticipantFactoryOperations _delegate;

	private POA _poa;
	public DomainParticipantFactoryPOATie(DomainParticipantFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public DomainParticipantFactoryPOATie(DomainParticipantFactoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.DomainParticipantFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.DomainParticipantFactory __r = org.omg.dds.DomainParticipantFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.DomainParticipantFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.DomainParticipantFactory __r = org.omg.dds.DomainParticipantFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public DomainParticipantFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(DomainParticipantFactoryOperations delegate)
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
	public org.omg.dds.DomainParticipant lookup_participant(int domainId)
	{
		return _delegate.lookup_participant(domainId);
	}

	public org.omg.dds.DomainParticipant create_participant(int domainId, org.omg.dds.DomainParticipantQos qos, org.omg.dds.DomainParticipantListener a_listener)
	{
		return _delegate.create_participant(domainId,qos,a_listener);
	}

	public void get_default_participant_qos(org.omg.dds.DomainParticipantQosHolder qos)
	{
_delegate.get_default_participant_qos(qos);
	}

	public int delete_participant(org.omg.dds.DomainParticipant a_participant)
	{
		return _delegate.delete_participant(a_participant);
	}

	public int set_default_participant_qos(org.omg.dds.DomainParticipantQos qos)
	{
		return _delegate.set_default_participant_qos(qos);
	}

}
