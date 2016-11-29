package org.omg.CORBA;

/**
 * Generated from IDL interface "UnionDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class UnionDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public UnionDef value;
	public UnionDefHolder()
	{
	}
	public UnionDefHolder (final UnionDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return UnionDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = UnionDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		UnionDefHelper.write (_out,value);
	}
}
