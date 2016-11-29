package org.jacorb.imr;

/**
 * Generated from IDL struct "ImRInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ImRInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ImRInfo(){}
	public java.lang.String host;
	public int port;
	public ImRInfo(java.lang.String host, int port)
	{
		this.host = host;
		this.port = port;
	}
}
