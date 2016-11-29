package org.jacorb.sasPolicy;

/**
 * Generated from IDL interface "ATLASPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ATLASPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public ATLASPolicy value;
	public ATLASPolicyHolder()
	{
	}
	public ATLASPolicyHolder (final ATLASPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ATLASPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ATLASPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ATLASPolicyHelper.write (_out,value);
	}
}
