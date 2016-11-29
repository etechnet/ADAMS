package org.omg.CORBA;

/**
 * Generated from IDL interface "ValueMemberDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ValueMemberDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public ValueMemberDef value;
	public ValueMemberDefHolder()
	{
	}
	public ValueMemberDefHolder (final ValueMemberDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ValueMemberDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ValueMemberDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ValueMemberDefHelper.write (_out,value);
	}
}
