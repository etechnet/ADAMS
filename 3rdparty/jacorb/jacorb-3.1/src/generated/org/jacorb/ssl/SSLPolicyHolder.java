package org.jacorb.ssl;

/**
 * Generated from IDL interface "SSLPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class SSLPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public SSLPolicy value;
	public SSLPolicyHolder()
	{
	}
	public SSLPolicyHolder (final SSLPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SSLPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SSLPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SSLPolicyHelper.write (_out,value);
	}
}
