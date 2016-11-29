package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "IRObject".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class IRObjectPOATie
	extends IRObjectPOA
{
	private IRObjectOperations _delegate;

	private POA _poa;
	public IRObjectPOATie(IRObjectOperations delegate)
	{
		_delegate = delegate;
	}
	public IRObjectPOATie(IRObjectOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.IRObject _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.IRObject __r = org.omg.CORBA.IRObjectHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.IRObject _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.IRObject __r = org.omg.CORBA.IRObjectHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public IRObjectOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IRObjectOperations delegate)
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
	public void destroy()
	{
_delegate.destroy();
	}

	public org.omg.CORBA.DefinitionKind def_kind()
	{
		return _delegate.def_kind();
	}

}
