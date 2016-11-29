package org.omg.CosCollection;

/**
 * Generated from IDL interface "Stack".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class StackHolder	implements org.omg.CORBA.portable.Streamable{
	 public Stack value;
	public StackHolder()
	{
	}
	public StackHolder (final Stack initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StackHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StackHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StackHelper.write (_out,value);
	}
}
