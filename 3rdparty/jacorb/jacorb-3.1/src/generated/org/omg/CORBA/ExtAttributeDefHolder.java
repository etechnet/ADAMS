package org.omg.CORBA;

/**
 * Generated from IDL interface "ExtAttributeDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtAttributeDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public ExtAttributeDef value;
	public ExtAttributeDefHolder()
	{
	}
	public ExtAttributeDefHolder (final ExtAttributeDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ExtAttributeDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExtAttributeDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ExtAttributeDefHelper.write (_out,value);
	}
}
