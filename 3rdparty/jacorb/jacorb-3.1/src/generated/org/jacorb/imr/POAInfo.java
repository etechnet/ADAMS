package org.jacorb.imr;

/**
 * Generated from IDL struct "POAInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class POAInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public POAInfo(){}
	public java.lang.String name;
	public java.lang.String host;
	public int port;
	public java.lang.String server;
	public boolean active;
	public POAInfo(java.lang.String name, java.lang.String host, int port, java.lang.String server, boolean active)
	{
		this.name = name;
		this.host = host;
		this.port = port;
		this.server = server;
		this.active = active;
	}
}
