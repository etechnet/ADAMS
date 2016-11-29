package org.omg.SecurityLevel2;

/**
 * Generated from IDL interface "AuditChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class AuditChannelHolder	implements org.omg.CORBA.portable.Streamable{
	 public AuditChannel value;
	public AuditChannelHolder()
	{
	}
	public AuditChannelHolder (final AuditChannel initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return AuditChannelHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AuditChannelHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AuditChannelHelper.write (_out,value);
	}
}
