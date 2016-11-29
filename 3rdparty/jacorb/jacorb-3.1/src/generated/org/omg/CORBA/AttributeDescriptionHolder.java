package org.omg.CORBA;

/**
 * Generated from IDL struct "AttributeDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class AttributeDescriptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.AttributeDescription value;

	public AttributeDescriptionHolder ()
	{
	}
	public AttributeDescriptionHolder(final org.omg.CORBA.AttributeDescription initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.AttributeDescriptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.AttributeDescriptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.AttributeDescriptionHelper.write(_out, value);
	}
}
