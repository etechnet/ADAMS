package org.omg.CORBA;
/**
 * Generated from IDL enum "exception_type".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class exception_typeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public exception_type value;

	public exception_typeHolder ()
	{
	}
	public exception_typeHolder (final exception_type initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return exception_typeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = exception_typeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		exception_typeHelper.write (out,value);
	}
}
