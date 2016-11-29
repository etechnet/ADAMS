package org.omg.CosTransactions;

/**
 * Generated from IDL interface "Resource".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ResourceHolder	implements org.omg.CORBA.portable.Streamable{
	 public Resource value;
	public ResourceHolder()
	{
	}
	public ResourceHolder (final Resource initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ResourceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ResourceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ResourceHelper.write (_out,value);
	}
}
