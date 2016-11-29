package org.omg.CosCollection;

/**
 * Generated from IDL interface "SortedMap".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SortedMapHolder	implements org.omg.CORBA.portable.Streamable{
	 public SortedMap value;
	public SortedMapHolder()
	{
	}
	public SortedMapHolder (final SortedMap initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SortedMapHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SortedMapHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SortedMapHelper.write (_out,value);
	}
}
