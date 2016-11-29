package org.omg.CosCollection;

/**
 * Generated from IDL interface "Bag".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BagHolder	implements org.omg.CORBA.portable.Streamable{
	 public Bag value;
	public BagHolder()
	{
	}
	public BagHolder (final Bag initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return BagHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = BagHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		BagHelper.write (_out,value);
	}
}
