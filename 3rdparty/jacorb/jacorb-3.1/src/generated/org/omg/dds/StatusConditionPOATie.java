package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "StatusCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class StatusConditionPOATie
	extends StatusConditionPOA
{
	private StatusConditionOperations _delegate;

	private POA _poa;
	public StatusConditionPOATie(StatusConditionOperations delegate)
	{
		_delegate = delegate;
	}
	public StatusConditionPOATie(StatusConditionOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.StatusCondition _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.StatusCondition __r = org.omg.dds.StatusConditionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.StatusCondition _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.StatusCondition __r = org.omg.dds.StatusConditionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public StatusConditionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(StatusConditionOperations delegate)
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
	public int set_enabled_statuses(int mask)
	{
		return _delegate.set_enabled_statuses(mask);
	}

	public boolean get_trigger_value()
	{
		return _delegate.get_trigger_value();
	}

	public org.omg.dds.Entity get_entity()
	{
		return _delegate.get_entity();
	}

	public int get_enabled_statuses()
	{
		return _delegate.get_enabled_statuses();
	}

}
