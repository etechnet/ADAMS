package org.omg.SendingContext;

/**
 * Generated from IDL interface "CodeBase".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CodeBaseHolder	implements org.omg.CORBA.portable.Streamable{
	 public CodeBase value;
	public CodeBaseHolder()
	{
	}
	public CodeBaseHolder (final CodeBase initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CodeBaseHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CodeBaseHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CodeBaseHelper.write (_out,value);
	}
}
