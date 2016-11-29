package org.omg.CORBA;

/**
 * Generated from IDL interface "WstringDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class WstringDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public WstringDef value;
	public WstringDefHolder()
	{
	}
	public WstringDefHolder (final WstringDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return WstringDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = WstringDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		WstringDefHelper.write (_out,value);
	}
}
