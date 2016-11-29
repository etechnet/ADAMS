package org.omg.SecurityReplaceable;

/**
 * Generated from IDL interface "ClientSecurityContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class ClientSecurityContextHolder	implements org.omg.CORBA.portable.Streamable{
	 public ClientSecurityContext value;
	public ClientSecurityContextHolder()
	{
	}
	public ClientSecurityContextHolder (final ClientSecurityContext initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ClientSecurityContextHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ClientSecurityContextHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ClientSecurityContextHelper.write (_out,value);
	}
}
