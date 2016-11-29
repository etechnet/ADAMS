package org.omg.CosCollection;

/**
 * Generated from IDL interface "RACollectionFactories".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class RACollectionFactoriesHolder	implements org.omg.CORBA.portable.Streamable{
	 public RACollectionFactories value;
	public RACollectionFactoriesHolder()
	{
	}
	public RACollectionFactoriesHolder (final RACollectionFactories initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RACollectionFactoriesHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RACollectionFactoriesHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RACollectionFactoriesHelper.write (_out,value);
	}
}
