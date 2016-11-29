package org.omg.Security;

/**
 * Generated from IDL alias "AttributeTypeList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AttributeTypeListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Security.AttributeType[] value;

	public AttributeTypeListHolder ()
	{
	}
	public AttributeTypeListHolder (final org.omg.Security.AttributeType[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return AttributeTypeListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AttributeTypeListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AttributeTypeListHelper.write (out,value);
	}
}
