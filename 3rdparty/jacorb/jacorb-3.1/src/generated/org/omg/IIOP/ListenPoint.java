package org.omg.IIOP;

/**
 * Generated from IDL struct "ListenPoint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ListenPoint
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ListenPoint(){}
	public java.lang.String host = "";
	public short port;
	public ListenPoint(java.lang.String host, short port)
	{
		this.host = host;
		this.port = port;
	}
}
