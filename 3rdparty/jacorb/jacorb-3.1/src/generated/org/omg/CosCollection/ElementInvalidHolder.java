package org.omg.CosCollection;

/**
 * Generated from IDL exception "ElementInvalid".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ElementInvalidHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosCollection.ElementInvalid value;

	public ElementInvalidHolder ()
	{
	}
	public ElementInvalidHolder(final org.omg.CosCollection.ElementInvalid initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosCollection.ElementInvalidHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosCollection.ElementInvalidHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosCollection.ElementInvalidHelper.write(_out, value);
	}
}
