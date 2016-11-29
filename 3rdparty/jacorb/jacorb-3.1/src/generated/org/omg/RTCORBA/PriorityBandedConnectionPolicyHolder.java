package org.omg.RTCORBA;

/**
 * Generated from IDL interface "PriorityBandedConnectionPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PriorityBandedConnectionPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public PriorityBandedConnectionPolicy value;
	public PriorityBandedConnectionPolicyHolder()
	{
	}
	public PriorityBandedConnectionPolicyHolder (final PriorityBandedConnectionPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PriorityBandedConnectionPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PriorityBandedConnectionPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PriorityBandedConnectionPolicyHelper.write (_out,value);
	}
}
