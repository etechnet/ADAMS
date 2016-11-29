package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "AttributeDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class AttributeDefPOATie
	extends AttributeDefPOA
{
	private AttributeDefOperations _delegate;

	private POA _poa;
	public AttributeDefPOATie(AttributeDefOperations delegate)
	{
		_delegate = delegate;
	}
	public AttributeDefPOATie(AttributeDefOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.AttributeDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.AttributeDef __r = org.omg.CORBA.AttributeDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.AttributeDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.AttributeDef __r = org.omg.CORBA.AttributeDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public AttributeDefOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AttributeDefOperations delegate)
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
	public org.omg.CORBA.IDLType type_def()
	{
		return _delegate.type_def();
	}

	public org.omg.CORBA.Repository containing_repository()
	{
		return _delegate.containing_repository();
	}

	public org.omg.CORBA.DefinitionKind def_kind()
	{
		return _delegate.def_kind();
	}

	public void type_def(org.omg.CORBA.IDLType a)
	{
		_delegate.type_def(a);
	}

	public java.lang.String absolute_name()
	{
		return _delegate.absolute_name();
	}

	public java.lang.String name()
	{
		return _delegate.name();
	}

	public org.omg.CORBA.TypeCode type()
	{
		return _delegate.type();
	}

	public void name(java.lang.String a)
	{
		_delegate.name(a);
	}

	public void mode(org.omg.CORBA.AttributeMode a)
	{
		_delegate.mode(a);
	}

	public org.omg.CORBA.AttributeMode mode()
	{
		return _delegate.mode();
	}

	public org.omg.CORBA.Container defined_in()
	{
		return _delegate.defined_in();
	}

	public java.lang.String version()
	{
		return _delegate.version();
	}

	public void version(java.lang.String a)
	{
		_delegate.version(a);
	}

	public org.omg.CORBA.ContainedPackage.Description describe()
	{
		return _delegate.describe();
	}

	public java.lang.String id()
	{
		return _delegate.id();
	}

	public void move(org.omg.CORBA.Container new_container, java.lang.String new_name, java.lang.String new_version)
	{
_delegate.move(new_container,new_name,new_version);
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public void id(java.lang.String a)
	{
		_delegate.id(a);
	}

}
