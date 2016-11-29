package org.jacorb.imr;

/**
 * Generated from IDL struct "HostInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class HostInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public HostInfo(){}
	public java.lang.String name;
	public org.jacorb.imr.ServerStartupDaemon ssd_ref;
	public java.lang.String ior_string;
	public HostInfo(java.lang.String name, org.jacorb.imr.ServerStartupDaemon ssd_ref, java.lang.String ior_string)
	{
		this.name = name;
		this.ssd_ref = ssd_ref;
		this.ior_string = ior_string;
	}
}
