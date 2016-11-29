package org.omg.Messaging;

/**
 * Generated from IDL interface "RelativeRoundtripTimeoutPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RelativeRoundtripTimeoutPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public RelativeRoundtripTimeoutPolicy value;
	public RelativeRoundtripTimeoutPolicyHolder()
	{
	}
	public RelativeRoundtripTimeoutPolicyHolder (final RelativeRoundtripTimeoutPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RelativeRoundtripTimeoutPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RelativeRoundtripTimeoutPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RelativeRoundtripTimeoutPolicyHelper.write (_out,value);
	}
}
