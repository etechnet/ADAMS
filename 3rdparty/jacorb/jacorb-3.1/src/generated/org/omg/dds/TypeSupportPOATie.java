package org.omg.dds;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TypeSupport".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class TypeSupportPOATie
	extends TypeSupportPOA
{
	private TypeSupportOperations _delegate;

	private POA _poa;
	public TypeSupportPOATie(TypeSupportOperations delegate)
	{
		_delegate = delegate;
	}
	public TypeSupportPOATie(TypeSupportOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.dds.TypeSupport _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.TypeSupport __r = org.omg.dds.TypeSupportHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.TypeSupport _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.TypeSupport __r = org.omg.dds.TypeSupportHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TypeSupportOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TypeSupportOperations delegate)
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
