package org.omg.CosTypedEventChannelAdmin;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TypedEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public class TypedEventChannelPOATie
	extends TypedEventChannelPOA
{
	private TypedEventChannelOperations _delegate;

	private POA _poa;
	public TypedEventChannelPOATie(TypedEventChannelOperations delegate)
	{
		_delegate = delegate;
	}
	public TypedEventChannelPOATie(TypedEventChannelOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTypedEventChannelAdmin.TypedEventChannel _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedEventChannelAdmin.TypedEventChannel __r = org.omg.CosTypedEventChannelAdmin.TypedEventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedEventChannelAdmin.TypedEventChannel _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedEventChannelAdmin.TypedEventChannel __r = org.omg.CosTypedEventChannelAdmin.TypedEventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TypedEventChannelOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TypedEventChannelOperations delegate)
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
	public org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin for_consumers()
	{
		return _delegate.for_consumers();
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public org.omg.CosTypedEventChannelAdmin.TypedSupplierAdmin for_suppliers()
	{
		return _delegate.for_suppliers();
	}

}
