package org.jacorb.imr;

/**
 * Generated from IDL exception "ServerStartupFailed".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ServerStartupFailed
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ServerStartupFailed()
	{
		super(org.jacorb.imr.ServerStartupFailedHelper.id());
	}

	public java.lang.String reason = "";
	public ServerStartupFailed(java.lang.String _reason,java.lang.String reason)
	{
		super(_reason);
		this.reason = reason;
	}
	public ServerStartupFailed(java.lang.String reason)
	{
		super(org.jacorb.imr.ServerStartupFailedHelper.id());
		this.reason = reason;
	}
}
