package org.omg.CORBA;

/**
 * Generated from IDL interface "ValueDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ValueDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public ValueDef value;
	public ValueDefHolder()
	{
	}
	public ValueDefHolder (final ValueDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ValueDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ValueDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ValueDefHelper.write (_out,value);
	}
}
