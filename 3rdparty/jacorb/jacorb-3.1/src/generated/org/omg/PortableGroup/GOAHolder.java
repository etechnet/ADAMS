package org.omg.PortableGroup;

/**
 * Generated from IDL interface "GOA".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class GOAHolder	implements org.omg.CORBA.portable.Streamable{
	 public GOA value;
	public GOAHolder()
	{
	}
	public GOAHolder (final GOA initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return GOAHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = GOAHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		GOAHelper.write (_out,value);
	}
}
