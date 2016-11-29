package org.omg.CSIIOP;

/**
 * Generated from IDL struct "TransportAddress".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransportAddress
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TransportAddress(){}
	public java.lang.String host_name = "";
	public short port;
	public TransportAddress(java.lang.String host_name, short port)
	{
		this.host_name = host_name;
		this.port = port;
	}
}
