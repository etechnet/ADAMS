package org.omg.SendingContext;

/**
 * Generated from IDL interface "RunTime".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class RunTimeHolder	implements org.omg.CORBA.portable.Streamable{
	 public RunTime value;
	public RunTimeHolder()
	{
	}
	public RunTimeHolder (final RunTime initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RunTimeHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RunTimeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RunTimeHelper.write (_out,value);
	}
}
