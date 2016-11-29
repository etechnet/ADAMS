package org.omg.SendingContext;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "CodeBase".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class CodeBasePOATie
	extends CodeBasePOA
{
	private CodeBaseOperations _delegate;

	private POA _poa;
	public CodeBasePOATie(CodeBaseOperations delegate)
	{
		_delegate = delegate;
	}
	public CodeBasePOATie(CodeBaseOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.SendingContext.CodeBase _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.SendingContext.CodeBase __r = org.omg.SendingContext.CodeBaseHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.SendingContext.CodeBase _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.SendingContext.CodeBase __r = org.omg.SendingContext.CodeBaseHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public CodeBaseOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(CodeBaseOperations delegate)
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
	public org.omg.CORBA.Repository get_ir()
	{
		return _delegate.get_ir();
	}

	public java.lang.String[] bases(java.lang.String x)
	{
		return _delegate.bases(x);
	}

	public org.omg.CORBA.ValueDefPackage.FullValueDescription meta(java.lang.String x)
	{
		return _delegate.meta(x);
	}

	public java.lang.String[] implementations(java.lang.String[] x)
	{
		return _delegate.implementations(x);
	}

	public java.lang.String implementation(java.lang.String x)
	{
		return _delegate.implementation(x);
	}

	public java.lang.String implementationx(java.lang.String x)
	{
		return _delegate.implementationx(x);
	}

	public org.omg.CORBA.ValueDefPackage.FullValueDescription[] metas(java.lang.String[] x)
	{
		return _delegate.metas(x);
	}

}
