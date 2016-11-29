package org.omg.CORBA;

/**
 * Generated from IDL interface "PolicyManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class PolicyManagerHolder	implements org.omg.CORBA.portable.Streamable{
	 public PolicyManager value;
	public PolicyManagerHolder()
	{
	}
	public PolicyManagerHolder (final PolicyManager initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PolicyManagerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PolicyManagerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PolicyManagerHelper.write (_out,value);
	}
}
