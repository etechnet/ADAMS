package org.omg.ETF;

/**
 * Generated from IDL interface "Handle".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class HandleHolder	implements org.omg.CORBA.portable.Streamable{
	 public Handle value;
	public HandleHolder()
	{
	}
	public HandleHolder (final Handle initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return HandleHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = HandleHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		HandleHelper.write (_out,value);
	}
}
