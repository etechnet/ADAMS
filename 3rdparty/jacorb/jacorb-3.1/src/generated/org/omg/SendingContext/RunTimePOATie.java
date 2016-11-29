package org.omg.SendingContext;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "RunTime".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class RunTimePOATie
	extends RunTimePOA
{
	private RunTimeOperations _delegate;

	private POA _poa;
	public RunTimePOATie(RunTimeOperations delegate)
	{
		_delegate = delegate;
	}
	public RunTimePOATie(RunTimeOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.SendingContext.RunTime _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.SendingContext.RunTime __r = org.omg.SendingContext.RunTimeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.SendingContext.RunTime _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.SendingContext.RunTime __r = org.omg.SendingContext.RunTimeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public RunTimeOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RunTimeOperations delegate)
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
}
