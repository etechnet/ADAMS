package org.omg.CORBA;
public final class WStringValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public java.lang.String value;

	public WStringValueHolder ()
	{
	}
	public WStringValueHolder (final java.lang.String initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.WStringValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.WStringValueHelper.read (_in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.WStringValueHelper.write (_out,value);
	}
}
