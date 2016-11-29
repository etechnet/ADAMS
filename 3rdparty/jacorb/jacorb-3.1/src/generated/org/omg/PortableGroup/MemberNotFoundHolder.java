package org.omg.PortableGroup;

/**
 * Generated from IDL exception "MemberNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class MemberNotFoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.MemberNotFound value;

	public MemberNotFoundHolder ()
	{
	}
	public MemberNotFoundHolder(final org.omg.PortableGroup.MemberNotFound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.MemberNotFoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.MemberNotFoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.MemberNotFoundHelper.write(_out, value);
	}
}
