package org.omg.CosTrading;

/**
 * Generated from IDL interface "Link".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class LinkHolder	implements org.omg.CORBA.portable.Streamable{
	 public Link value;
	public LinkHolder()
	{
	}
	public LinkHolder (final Link initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return LinkHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LinkHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		LinkHelper.write (_out,value);
	}
}
