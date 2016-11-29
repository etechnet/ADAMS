package org.omg.Messaging;

/**
 * Generated from IDL interface "RebindPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RebindPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public RebindPolicy value;
	public RebindPolicyHolder()
	{
	}
	public RebindPolicyHolder (final RebindPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RebindPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RebindPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RebindPolicyHelper.write (_out,value);
	}
}
