package org.omg.CORBA;

/**
 * Generated from IDL interface "PrimitiveDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class PrimitiveDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public PrimitiveDef value;
	public PrimitiveDefHolder()
	{
	}
	public PrimitiveDefHolder (final PrimitiveDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PrimitiveDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PrimitiveDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PrimitiveDefHelper.write (_out,value);
	}
}
