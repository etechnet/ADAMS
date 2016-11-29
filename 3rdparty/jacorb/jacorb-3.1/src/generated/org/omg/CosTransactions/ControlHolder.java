package org.omg.CosTransactions;

/**
 * Generated from IDL interface "Control".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ControlHolder	implements org.omg.CORBA.portable.Streamable{
	 public Control value;
	public ControlHolder()
	{
	}
	public ControlHolder (final Control initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ControlHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ControlHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ControlHelper.write (_out,value);
	}
}
