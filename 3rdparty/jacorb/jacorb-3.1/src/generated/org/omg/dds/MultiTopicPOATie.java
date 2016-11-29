package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "MultiTopic".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class MultiTopicPOATie
	extends MultiTopicPOA
{
	private MultiTopicOperations _delegate;

	private POA _poa;
	public MultiTopicPOATie(MultiTopicOperations delegate)
	{
		_delegate = delegate;
	}
	public MultiTopicPOATie(MultiTopicOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.MultiTopic _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.MultiTopic __r = org.omg.dds.MultiTopicHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.MultiTopic _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.MultiTopic __r = org.omg.dds.MultiTopicHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public MultiTopicOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(MultiTopicOperations delegate)
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
	public org.omg.dds.DomainParticipant get_participant()
	{
		return _delegate.get_participant();
	}

	public java.lang.String[] get_expression_parameters()
	{
		return _delegate.get_expression_parameters();
	}

	public java.lang.String get_subscription_expression()
	{
		return _delegate.get_subscription_expression();
	}

	public int set_expression_parameters(java.lang.String[] expression_parameters)
	{
		return _delegate.set_expression_parameters(expression_parameters);
	}

	public java.lang.String get_type_name()
	{
		return _delegate.get_type_name();
	}

	public java.lang.String get_name()
	{
		return _delegate.get_name();
	}

}
