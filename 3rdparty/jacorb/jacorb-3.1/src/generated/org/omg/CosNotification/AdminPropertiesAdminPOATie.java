package org.omg.CosNotification;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "AdminPropertiesAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class AdminPropertiesAdminPOATie
	extends AdminPropertiesAdminPOA
{
	private AdminPropertiesAdminOperations _delegate;

	private POA _poa;
	public AdminPropertiesAdminPOATie(AdminPropertiesAdminOperations delegate)
	{
		_delegate = delegate;
	}
	public AdminPropertiesAdminPOATie(AdminPropertiesAdminOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotification.AdminPropertiesAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotification.AdminPropertiesAdmin __r = org.omg.CosNotification.AdminPropertiesAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotification.AdminPropertiesAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotification.AdminPropertiesAdmin __r = org.omg.CosNotification.AdminPropertiesAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public AdminPropertiesAdminOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AdminPropertiesAdminOperations delegate)
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
	public void set_admin(org.omg.CosNotification.Property[] admin) throws org.omg.CosNotification.UnsupportedAdmin
	{
_delegate.set_admin(admin);
	}

	public org.omg.CosNotification.Property[] get_admin()
	{
		return _delegate.get_admin();
	}

}
