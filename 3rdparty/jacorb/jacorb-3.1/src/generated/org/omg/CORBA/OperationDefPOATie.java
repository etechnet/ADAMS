package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "OperationDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class OperationDefPOATie
	extends OperationDefPOA
{
	private OperationDefOperations _delegate;

	private POA _poa;
	public OperationDefPOATie(OperationDefOperations delegate)
	{
		_delegate = delegate;
	}
	public OperationDefPOATie(OperationDefOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.OperationDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.OperationDef __r = org.omg.CORBA.OperationDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.OperationDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.OperationDef __r = org.omg.CORBA.OperationDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public OperationDefOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(OperationDefOperations delegate)
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
	public org.omg.CORBA.Repository containing_repository()
	{
		return _delegate.containing_repository();
	}

	public org.omg.CORBA.DefinitionKind def_kind()
	{
		return _delegate.def_kind();
	}

	public java.lang.String absolute_name()
	{
		return _delegate.absolute_name();
	}

	public java.lang.String name()
	{
		return _delegate.name();
	}

	public void name(java.lang.String a)
	{
		_delegate.name(a);
	}

	public void params(org.omg.CORBA.ParameterDescription[] a)
	{
		_delegate.params(a);
	}

	public org.omg.CORBA.IDLType result_def()
	{
		return _delegate.result_def();
	}

	public java.lang.String[] contexts()
	{
		return _delegate.contexts();
	}

	public org.omg.CORBA.Container defined_in()
	{
		return _delegate.defined_in();
	}

	public org.omg.CORBA.OperationMode mode()
	{
		return _delegate.mode();
	}

	public java.lang.String version()
	{
		return _delegate.version();
	}

	public void result_def(org.omg.CORBA.IDLType a)
	{
		_delegate.result_def(a);
	}

	public void version(java.lang.String a)
	{
		_delegate.version(a);
	}

	public org.omg.CORBA.ContainedPackage.Description describe()
	{
		return _delegate.describe();
	}

	public org.omg.CORBA.ParameterDescription[] params()
	{
		return _delegate.params();
	}

	public java.lang.String id()
	{
		return _delegate.id();
	}

	public void move(org.omg.CORBA.Container new_container, java.lang.String new_name, java.lang.String new_version)
	{
_delegate.move(new_container,new_name,new_version);
	}

	public org.omg.CORBA.TypeCode result()
	{
		return _delegate.result();
	}

	public void contexts(java.lang.String[] a)
	{
		_delegate.contexts(a);
	}

	public void mode(org.omg.CORBA.OperationMode a)
	{
		_delegate.mode(a);
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public org.omg.CORBA.ExceptionDef[] exceptions()
	{
		return _delegate.exceptions();
	}

	public void exceptions(org.omg.CORBA.ExceptionDef[] a)
	{
		_delegate.exceptions(a);
	}

	public void id(java.lang.String a)
	{
		_delegate.id(a);
	}

}
