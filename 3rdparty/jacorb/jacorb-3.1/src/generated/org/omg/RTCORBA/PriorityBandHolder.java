package org.omg.RTCORBA;

/**
 * Generated from IDL struct "PriorityBand".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PriorityBandHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.RTCORBA.PriorityBand value;

	public PriorityBandHolder ()
	{
	}
	public PriorityBandHolder(final org.omg.RTCORBA.PriorityBand initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.RTCORBA.PriorityBandHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.RTCORBA.PriorityBandHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.RTCORBA.PriorityBandHelper.write(_out, value);
	}
}
