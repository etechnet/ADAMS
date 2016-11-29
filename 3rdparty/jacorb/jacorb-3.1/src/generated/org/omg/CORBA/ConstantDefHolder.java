package org.omg.CORBA;

/**
 * Generated from IDL interface "ConstantDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ConstantDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public ConstantDef value;
	public ConstantDefHolder()
	{
	}
	public ConstantDefHolder (final ConstantDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ConstantDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ConstantDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ConstantDefHelper.write (_out,value);
	}
}
