package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "WaitSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class WaitSetPOATie
	extends WaitSetPOA
{
	private WaitSetOperations _delegate;

	private POA _poa;
	public WaitSetPOATie(WaitSetOperations delegate)
	{
		_delegate = delegate;
	}
	public WaitSetPOATie(WaitSetOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.WaitSet _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.WaitSet __r = org.omg.dds.WaitSetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.WaitSet _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.WaitSet __r = org.omg.dds.WaitSetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public WaitSetOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(WaitSetOperations delegate)
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
	public int detach_condition(org.omg.dds.Condition cond)
	{
		return _delegate.detach_condition(cond);
	}

	public int _wait(org.omg.dds.ConditionSeqHolder active_conditions, org.omg.dds.Duration_t timeout)
	{
		return _delegate._wait(active_conditions,timeout);
	}

	public int get_conditions(org.omg.dds.ConditionSeqHolder attached_conditions)
	{
		return _delegate.get_conditions(attached_conditions);
	}

	public int attach_condition(org.omg.dds.Condition cond)
	{
		return _delegate.attach_condition(cond);
	}

}
