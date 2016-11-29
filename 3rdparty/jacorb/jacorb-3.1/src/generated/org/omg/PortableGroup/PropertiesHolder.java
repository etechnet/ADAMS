package org.omg.PortableGroup;

/**
 * Generated from IDL alias "Properties".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PropertiesHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.Property[] value;

	public PropertiesHolder ()
	{
	}
	public PropertiesHolder (final org.omg.PortableGroup.Property[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PropertiesHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PropertiesHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PropertiesHelper.write (out,value);
	}
}
