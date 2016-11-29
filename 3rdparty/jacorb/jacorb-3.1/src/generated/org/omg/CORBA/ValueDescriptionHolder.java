package org.omg.CORBA;

/**
 * Generated from IDL struct "ValueDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ValueDescriptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ValueDescription value;

	public ValueDescriptionHolder ()
	{
	}
	public ValueDescriptionHolder(final org.omg.CORBA.ValueDescription initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.ValueDescriptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.ValueDescriptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.ValueDescriptionHelper.write(_out, value);
	}
}
