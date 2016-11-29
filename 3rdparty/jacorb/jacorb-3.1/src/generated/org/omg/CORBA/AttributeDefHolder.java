package org.omg.CORBA;

/**
 * Generated from IDL interface "AttributeDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class AttributeDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public AttributeDef value;
	public AttributeDefHolder()
	{
	}
	public AttributeDefHolder (final AttributeDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return AttributeDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AttributeDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AttributeDefHelper.write (_out,value);
	}
}
