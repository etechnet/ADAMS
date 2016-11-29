package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "WstringDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class WstringDefPOATie
	extends WstringDefPOA
{
	private WstringDefOperations _delegate;

	private POA _poa;
	public WstringDefPOATie(WstringDefOperations delegate)
	{
		_delegate = delegate;
	}
	public WstringDefPOATie(WstringDefOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.WstringDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.WstringDef __r = org.omg.CORBA.WstringDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.WstringDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.WstringDef __r = org.omg.CORBA.WstringDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public WstringDefOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(WstringDefOperations delegate)
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
	public void bound(int a)
	{
		_delegate.bound(a);
	}

	public int bound()
	{
		return _delegate.bound();
	}

	public org.omg.CORBA.TypeCode type()
	{
		return _delegate.type();
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
