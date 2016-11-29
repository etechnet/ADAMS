package org.omg.CosTime;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TIO".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class TIOPOATie
	extends TIOPOA
{
	private TIOOperations _delegate;

	private POA _poa;
	public TIOPOATie(TIOOperations delegate)
	{
		_delegate = delegate;
	}
	public TIOPOATie(TIOOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTime.TIO _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTime.TIO __r = org.omg.CosTime.TIOHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTime.TIO _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTime.TIO __r = org.omg.CosTime.TIOHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TIOOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TIOOperations delegate)
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
	public org.omg.CosTime.OverlapType overlaps(org.omg.CosTime.TIO interval, org.omg.CosTime.TIOHolder overlap)
	{
		return _delegate.overlaps(interval,overlap);
	}

	public org.omg.CosTime.OverlapType spans(org.omg.CosTime.UTO time, org.omg.CosTime.TIOHolder overlap)
	{
		return _delegate.spans(time,overlap);
	}

	public org.omg.TimeBase.IntervalT time_interval()
	{
		return _delegate.time_interval();
	}

	public org.omg.CosTime.UTO time()
	{
		return _delegate.time();
	}

}
