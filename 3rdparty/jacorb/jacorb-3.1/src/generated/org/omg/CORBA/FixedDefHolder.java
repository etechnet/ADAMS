package org.omg.CORBA;

/**
 * Generated from IDL interface "FixedDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class FixedDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public FixedDef value;
	public FixedDefHolder()
	{
	}
	public FixedDefHolder (final FixedDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return FixedDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = FixedDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		FixedDefHelper.write (_out,value);
	}
}
