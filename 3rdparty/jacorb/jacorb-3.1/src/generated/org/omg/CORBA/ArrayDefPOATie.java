package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ArrayDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class ArrayDefPOATie
	extends ArrayDefPOA
{
	private ArrayDefOperations _delegate;

	private POA _poa;
	public ArrayDefPOATie(ArrayDefOperations delegate)
	{
		_delegate = delegate;
	}
	public ArrayDefPOATie(ArrayDefOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.ArrayDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.ArrayDef __r = org.omg.CORBA.ArrayDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.ArrayDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.ArrayDef __r = org.omg.CORBA.ArrayDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ArrayDefOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ArrayDefOperations delegate)
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
	public org.omg.CORBA.TypeCode element_type()
	{
		return _delegate.element_type();
	}

	public int length()
	{
		return _delegate.length();
	}

	public org.omg.CORBA.IDLType element_type_def()
	{
		return _delegate.element_type_def();
	}

	public void length(int a)
	{
		_delegate.length(a);
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

	public void element_type_def(org.omg.CORBA.IDLType a)
	{
		_delegate.element_type_def(a);
	}

}
