package org.omg.CosTime;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TimeService".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class TimeServicePOATie
	extends TimeServicePOA
{
	private TimeServiceOperations _delegate;

	private POA _poa;
	public TimeServicePOATie(TimeServiceOperations delegate)
	{
		_delegate = delegate;
	}
	public TimeServicePOATie(TimeServiceOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTime.TimeService _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTime.TimeService __r = org.omg.CosTime.TimeServiceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTime.TimeService _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTime.TimeService __r = org.omg.CosTime.TimeServiceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TimeServiceOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TimeServiceOperations delegate)
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
	public org.omg.CosTime.UTO uto_from_utc(org.omg.TimeBase.UtcT utc)
	{
		return _delegate.uto_from_utc(utc);
	}

	public org.omg.CosTime.UTO universal_time() throws org.omg.CosTime.TimeUnavailable
	{
		return _delegate.universal_time();
	}

	public org.omg.CosTime.UTO secure_universal_time() throws org.omg.CosTime.TimeUnavailable
	{
		return _delegate.secure_universal_time();
	}

	public org.omg.CosTime.UTO new_universal_time(long time, long inaccuracy, short tdf)
	{
		return _delegate.new_universal_time(time,inaccuracy,tdf);
	}

	public org.omg.CosTime.TIO new_interval(long lower, long upper)
	{
		return _delegate.new_interval(lower,upper);
	}

}
