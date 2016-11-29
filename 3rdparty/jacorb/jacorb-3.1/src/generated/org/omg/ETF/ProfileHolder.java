package org.omg.ETF;

/**
 * Generated from IDL interface "Profile".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ProfileHolder	implements org.omg.CORBA.portable.Streamable{
	 public Profile value;
	public ProfileHolder()
	{
	}
	public ProfileHolder (final Profile initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ProfileHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ProfileHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ProfileHelper.write (_out,value);
	}
}
