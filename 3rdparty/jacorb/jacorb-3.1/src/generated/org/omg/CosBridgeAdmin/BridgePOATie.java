package org.omg.CosBridgeAdmin;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Bridge".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public class BridgePOATie
	extends BridgePOA
{
	private BridgeOperations _delegate;

	private POA _poa;
	public BridgePOATie(BridgeOperations delegate)
	{
		_delegate = delegate;
	}
	public BridgePOATie(BridgeOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosBridgeAdmin.Bridge _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosBridgeAdmin.Bridge __r = org.omg.CosBridgeAdmin.BridgeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosBridgeAdmin.Bridge _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosBridgeAdmin.Bridge __r = org.omg.CosBridgeAdmin.BridgeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public BridgeOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(BridgeOperations delegate)
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
	public void start_bridge() throws org.omg.CosBridgeAdmin.BridgeAlreadyStarted,org.omg.CosBridgeAdmin.InvalidExternalEndPoints
	{
_delegate.start_bridge();
	}

	public void stop_bridge() throws org.omg.CosBridgeAdmin.BridgeInactive
	{
_delegate.stop_bridge();
	}

	public org.omg.CosBridgeAdmin.ExternalEndpoint end_point_receiver()
	{
		return _delegate.end_point_receiver();
	}

	public org.omg.CosBridgeAdmin.ExternalEndpoint end_point_sender()
	{
		return _delegate.end_point_sender();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
