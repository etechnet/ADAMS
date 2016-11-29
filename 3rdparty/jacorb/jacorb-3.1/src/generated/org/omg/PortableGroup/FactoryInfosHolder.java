package org.omg.PortableGroup;

/**
 * Generated from IDL alias "FactoryInfos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class FactoryInfosHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.FactoryInfo[] value;

	public FactoryInfosHolder ()
	{
	}
	public FactoryInfosHolder (final org.omg.PortableGroup.FactoryInfo[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return FactoryInfosHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = FactoryInfosHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		FactoryInfosHelper.write (out,value);
	}
}
