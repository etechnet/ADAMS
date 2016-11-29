package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL exception "AdminLimitExceeded".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class AdminLimitExceeded
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public AdminLimitExceeded()
	{
		super(org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.id());
	}

	public org.omg.CosNotifyChannelAdmin.AdminLimit admin_property_err;
	public AdminLimitExceeded(java.lang.String _reason,org.omg.CosNotifyChannelAdmin.AdminLimit admin_property_err)
	{
		super(_reason);
		this.admin_property_err = admin_property_err;
	}
	public AdminLimitExceeded(org.omg.CosNotifyChannelAdmin.AdminLimit admin_property_err)
	{
		super(org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.id());
		this.admin_property_err = admin_property_err;
	}
}
