package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "PublisherListener".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class PublisherListenerPOATie
	extends PublisherListenerPOA
{
	private PublisherListenerOperations _delegate;

	private POA _poa;
	public PublisherListenerPOATie(PublisherListenerOperations delegate)
	{
		_delegate = delegate;
	}
	public PublisherListenerPOATie(PublisherListenerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.PublisherListener _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.PublisherListener __r = org.omg.dds.PublisherListenerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.PublisherListener _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.PublisherListener __r = org.omg.dds.PublisherListenerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public PublisherListenerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PublisherListenerOperations delegate)
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
	public void on_offered_deadline_missed(org.omg.dds.DataWriter writer, org.omg.dds.OfferedDeadlineMissedStatus status)
	{
_delegate.on_offered_deadline_missed(writer,status);
	}

	public void on_liveliness_lost(org.omg.dds.DataWriter writer, org.omg.dds.LivelinessLostStatus status)
	{
_delegate.on_liveliness_lost(writer,status);
	}

	public void on_offered_incompatible_qos(org.omg.dds.DataWriter writer, org.omg.dds.OfferedIncompatibleQosStatus status)
	{
_delegate.on_offered_incompatible_qos(writer,status);
	}

	public void on_publication_match(org.omg.dds.DataWriter writer, org.omg.dds.PublicationMatchStatus status)
	{
_delegate.on_publication_match(writer,status);
	}

}
