package org.jacorb.notification;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "JacORBEventChannelFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public class JacORBEventChannelFactoryPOATie
	extends JacORBEventChannelFactoryPOA
{
	private JacORBEventChannelFactoryOperations _delegate;

	private POA _poa;
	public JacORBEventChannelFactoryPOATie(JacORBEventChannelFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public JacORBEventChannelFactoryPOATie(JacORBEventChannelFactoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.jacorb.notification.JacORBEventChannelFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.notification.JacORBEventChannelFactory __r = org.jacorb.notification.JacORBEventChannelFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.notification.JacORBEventChannelFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.notification.JacORBEventChannelFactory __r = org.jacorb.notification.JacORBEventChannelFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public JacORBEventChannelFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(JacORBEventChannelFactoryOperations delegate)
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
	public org.omg.CosNotifyChannelAdmin.EventChannel create_channel(org.omg.CosNotification.Property[] initial_qos, org.omg.CosNotification.Property[] initial_admin, org.omg.CORBA.IntHolder id) throws org.omg.CosNotification.UnsupportedAdmin,org.omg.CosNotification.UnsupportedQoS
	{
		return _delegate.create_channel(initial_qos,initial_admin,id);
	}

	public org.omg.CosNotifyChannelAdmin.EventChannel get_event_channel(int id) throws org.omg.CosNotifyChannelAdmin.ChannelNotFound
	{
		return _delegate.get_event_channel(id);
	}

	public int[] get_all_channels()
	{
		return _delegate.get_all_channels();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
