package org.omg.dds;

/**
 * Generated from IDL struct "PublicationMatchStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PublicationMatchStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.PublicationMatchStatus value;

	public PublicationMatchStatusHolder ()
	{
	}
	public PublicationMatchStatusHolder(final org.omg.dds.PublicationMatchStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.PublicationMatchStatusHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.PublicationMatchStatusHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.PublicationMatchStatusHelper.write(_out, value);
	}
}
