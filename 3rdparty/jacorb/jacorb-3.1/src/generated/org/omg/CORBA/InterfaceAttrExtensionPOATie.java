package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "InterfaceAttrExtension".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class InterfaceAttrExtensionPOATie
	extends InterfaceAttrExtensionPOA
{
	private InterfaceAttrExtensionOperations _delegate;

	private POA _poa;
	public InterfaceAttrExtensionPOATie(InterfaceAttrExtensionOperations delegate)
	{
		_delegate = delegate;
	}
	public InterfaceAttrExtensionPOATie(InterfaceAttrExtensionOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.InterfaceAttrExtension _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.InterfaceAttrExtension __r = org.omg.CORBA.InterfaceAttrExtensionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.InterfaceAttrExtension _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.InterfaceAttrExtension __r = org.omg.CORBA.InterfaceAttrExtensionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public InterfaceAttrExtensionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(InterfaceAttrExtensionOperations delegate)
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
	public org.omg.CORBA.InterfaceAttrExtensionPackage.ExtFullInterfaceDescription describe_ext_interface()
	{
		return _delegate.describe_ext_interface();
	}

	public org.omg.CORBA.ExtAttributeDef create_ext_attribute(java.lang.String id, java.lang.String name, java.lang.String version, org.omg.CORBA.IDLType type, org.omg.CORBA.AttributeMode mode, org.omg.CORBA.ExceptionDef[] get_exceptions, org.omg.CORBA.ExceptionDef[] set_exceptions)
	{
		return _delegate.create_ext_attribute(id,name,version,type,mode,get_exceptions,set_exceptions);
	}

}
