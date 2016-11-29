package org.omg.CORBA.TypeCodePackage;

/**
 * Generated from IDL exception "Bounds".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class BoundsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.TypeCodePackage.Bounds value;

	public BoundsHolder ()
	{
	}
	public BoundsHolder(final org.omg.CORBA.TypeCodePackage.Bounds initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.TypeCodePackage.BoundsHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.TypeCodePackage.BoundsHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.TypeCodePackage.BoundsHelper.write(_out, value);
	}
}
