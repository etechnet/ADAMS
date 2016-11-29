package org.omg.CosNotification;

/**
 * Generated from IDL exception "UnsupportedAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class UnsupportedAdmin
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UnsupportedAdmin()
	{
		super(org.omg.CosNotification.UnsupportedAdminHelper.id());
	}

	public org.omg.CosNotification.PropertyError[] admin_err;
	public UnsupportedAdmin(java.lang.String _reason,org.omg.CosNotification.PropertyError[] admin_err)
	{
		super(_reason);
		this.admin_err = admin_err;
	}
	public UnsupportedAdmin(org.omg.CosNotification.PropertyError[] admin_err)
	{
		super(org.omg.CosNotification.UnsupportedAdminHelper.id());
		this.admin_err = admin_err;
	}
}
