package org.omg.CosTime;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "UTO".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class UTOPOATie
	extends UTOPOA
{
	private UTOOperations _delegate;

	private POA _poa;
	public UTOPOATie(UTOOperations delegate)
	{
		_delegate = delegate;
	}
	public UTOPOATie(UTOOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTime.UTO _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTime.UTO __r = org.omg.CosTime.UTOHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTime.UTO _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTime.UTO __r = org.omg.CosTime.UTOHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public UTOOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(UTOOperations delegate)
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
	public org.omg.CosTime.UTO absolute_time()
	{
		return _delegate.absolute_time();
	}

	public org.omg.TimeBase.UtcT utc_time()
	{
		return _delegate.utc_time();
	}

	public short tdf()
	{
		return _delegate.tdf();
	}

	public long inaccuracy()
	{
		return _delegate.inaccuracy();
	}

	public org.omg.CosTime.TIO time_to_interval(org.omg.CosTime.UTO uto_)
	{
		return _delegate.time_to_interval(uto_);
	}

	public org.omg.CosTime.TimeComparison compare_time(org.omg.CosTime.ComparisonType comparison_type, org.omg.CosTime.UTO uto_)
	{
		return _delegate.compare_time(comparison_type,uto_);
	}

	public org.omg.CosTime.TIO interval()
	{
		return _delegate.interval();
	}

	public long time()
	{
		return _delegate.time();
	}

}
