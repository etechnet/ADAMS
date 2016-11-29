package org.omg.CosTime;

/**
 * Generated from IDL interface "UTO".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class UTOHolder	implements org.omg.CORBA.portable.Streamable{
	 public UTO value;
	public UTOHolder()
	{
	}
	public UTOHolder (final UTO initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return UTOHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = UTOHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		UTOHelper.write (_out,value);
	}
}
