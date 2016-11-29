package org.jacorb.transport;

/**
 * Generated from IDL interface "Current".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.56
 */

public final class CurrentHolder	implements org.omg.CORBA.portable.Streamable{
	 public Current value;
	public CurrentHolder()
	{
	}
	public CurrentHolder (final Current initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CurrentHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CurrentHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CurrentHelper.write (_out,value);
	}
}
