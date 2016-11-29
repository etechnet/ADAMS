package org.omg.CORBA;
public final class StringValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public java.lang.String value;

	public StringValueHolder ()
	{
	}
	public StringValueHolder (final java.lang.String initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.StringValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.StringValueHelper.read (_in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.StringValueHelper.write (_out,value);
	}
}
