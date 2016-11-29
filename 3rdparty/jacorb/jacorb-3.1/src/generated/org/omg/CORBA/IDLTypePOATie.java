package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "IDLType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class IDLTypePOATie
	extends IDLTypePOA
{
	private IDLTypeOperations _delegate;

	private POA _poa;
	public IDLTypePOATie(IDLTypeOperations delegate)
	{
		_delegate = delegate;
	}
	public IDLTypePOATie(IDLTypeOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.IDLType _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.IDLType __r = org.omg.CORBA.IDLTypeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.IDLType _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.IDLType __r = org.omg.CORBA.IDLTypeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public IDLTypeOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IDLTypeOperations delegate)
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

	public org.omg.CORBA.TypeCode type()
	{
		return _delegate.type();
	}

	public org.omg.CORBA.DefinitionKind def_kind()
	{
		return _delegate.def_kind();
	}

}
