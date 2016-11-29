package org.omg.CosCollection;

/**
 * Generated from IDL interface "MapFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class MapFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public MapFactory value;
	public MapFactoryHolder()
	{
	}
	public MapFactoryHolder (final MapFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return MapFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = MapFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		MapFactoryHelper.write (_out,value);
	}
}
