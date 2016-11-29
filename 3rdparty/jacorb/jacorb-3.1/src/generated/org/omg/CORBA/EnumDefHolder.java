package org.omg.CORBA;

/**
 * Generated from IDL interface "EnumDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class EnumDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public EnumDef value;
	public EnumDefHolder()
	{
	}
	public EnumDefHolder (final EnumDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EnumDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EnumDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EnumDefHelper.write (_out,value);
	}
}
