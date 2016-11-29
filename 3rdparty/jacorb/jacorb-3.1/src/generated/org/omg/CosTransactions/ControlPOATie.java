package org.omg.CosTransactions;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Control".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class ControlPOATie
	extends ControlPOA
{
	private ControlOperations _delegate;

	private POA _poa;
	public ControlPOATie(ControlOperations delegate)
	{
		_delegate = delegate;
	}
	public ControlPOATie(ControlOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTransactions.Control _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTransactions.Control __r = org.omg.CosTransactions.ControlHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTransactions.Control _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTransactions.Control __r = org.omg.CosTransactions.ControlHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ControlOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ControlOperations delegate)
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
	public org.omg.CosTransactions.Terminator get_terminator() throws org.omg.CosTransactions.Unavailable
	{
		return _delegate.get_terminator();
	}

	public org.omg.CosTransactions.Coordinator get_coordinator() throws org.omg.CosTransactions.Unavailable
	{
		return _delegate.get_coordinator();
	}

}
