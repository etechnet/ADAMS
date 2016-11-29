package org.omg.CORBA;
/**
 * Generated from IDL enum "AttributeMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class AttributeModeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public AttributeMode value;

	public AttributeModeHolder ()
	{
	}
	public AttributeModeHolder (final AttributeMode initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return AttributeModeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AttributeModeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AttributeModeHelper.write (out,value);
	}
}
