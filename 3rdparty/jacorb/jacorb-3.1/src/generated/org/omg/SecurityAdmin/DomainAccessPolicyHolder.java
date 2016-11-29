package org.omg.SecurityAdmin;

/**
 * Generated from IDL interface "DomainAccessPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class DomainAccessPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public DomainAccessPolicy value;
	public DomainAccessPolicyHolder()
	{
	}
	public DomainAccessPolicyHolder (final DomainAccessPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DomainAccessPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DomainAccessPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DomainAccessPolicyHelper.write (_out,value);
	}
}
