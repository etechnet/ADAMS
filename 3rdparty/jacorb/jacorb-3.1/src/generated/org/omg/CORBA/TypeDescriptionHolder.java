package org.omg.CORBA;

/**
 * Generated from IDL struct "TypeDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class TypeDescriptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.TypeDescription value;

	public TypeDescriptionHolder ()
	{
	}
	public TypeDescriptionHolder(final org.omg.CORBA.TypeDescription initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.TypeDescriptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.TypeDescriptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.TypeDescriptionHelper.write(_out, value);
	}
}
