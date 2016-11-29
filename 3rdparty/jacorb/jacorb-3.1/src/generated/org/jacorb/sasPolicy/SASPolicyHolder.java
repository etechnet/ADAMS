package org.jacorb.sasPolicy;

/**
 * Generated from IDL interface "SASPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class SASPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public SASPolicy value;
	public SASPolicyHolder()
	{
	}
	public SASPolicyHolder (final SASPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SASPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SASPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SASPolicyHelper.write (_out,value);
	}
}
