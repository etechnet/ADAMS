package org.omg.CORBA;

/**
 * Generated from IDL interface "ExceptionDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExceptionDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public ExceptionDef value;
	public ExceptionDefHolder()
	{
	}
	public ExceptionDefHolder (final ExceptionDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ExceptionDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExceptionDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ExceptionDefHelper.write (_out,value);
	}
}
