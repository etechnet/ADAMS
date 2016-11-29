package org.omg.CosTypedNotifyChannelAdmin;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TypedEventChannelFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public class TypedEventChannelFactoryPOATie
	extends TypedEventChannelFactoryPOA
{
	private TypedEventChannelFactoryOperations _delegate;

	private POA _poa;
	public TypedEventChannelFactoryPOATie(TypedEventChannelFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public TypedEventChannelFactoryPOATie(TypedEventChannelFactoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory __r = org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory __r = org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TypedEventChannelFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TypedEventChannelFactoryOperations delegate)
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
	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannel get_typed_event_channel(int id) throws org.omg.CosNotifyChannelAdmin.ChannelNotFound
	{
		return _delegate.get_typed_event_channel(id);
	}

	public int[] get_all_typed_channels()
	{
		return _delegate.get_all_typed_channels();
	}

	public org.omg.CosTypedNotifyChannelAdmin.TypedEventChannel create_typed_channel(org.omg.CosNotification.Property[] initial_QoS, org.omg.CosNotification.Property[] initial_admin, org.omg.CORBA.IntHolder id) throws org.omg.CosNotification.UnsupportedAdmin,org.omg.CosNotification.UnsupportedQoS
	{
		return _delegate.create_typed_channel(initial_QoS,initial_admin,id);
	}

}
