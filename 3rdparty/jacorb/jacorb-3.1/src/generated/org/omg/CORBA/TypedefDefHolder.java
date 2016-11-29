package org.omg.CORBA;

/**
 * Generated from IDL interface "TypedefDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class TypedefDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public TypedefDef value;
	public TypedefDefHolder()
	{
	}
	public TypedefDefHolder (final TypedefDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TypedefDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TypedefDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TypedefDefHelper.write (_out,value);
	}
}
