package org.omg.RTCORBA;

/**
 * Generated from IDL interface "RTORB".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class RTORBHolder	implements org.omg.CORBA.portable.Streamable{
	 public RTORB value;
	public RTORBHolder()
	{
	}
	public RTORBHolder (final RTORB initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RTORBHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RTORBHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RTORBHelper.write (_out,value);
	}
}
