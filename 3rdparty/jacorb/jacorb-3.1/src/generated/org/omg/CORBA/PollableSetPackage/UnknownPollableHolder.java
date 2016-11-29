package org.omg.CORBA.PollableSetPackage;

/**
 * Generated from IDL exception "UnknownPollable".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class UnknownPollableHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.PollableSetPackage.UnknownPollable value;

	public UnknownPollableHolder ()
	{
	}
	public UnknownPollableHolder(final org.omg.CORBA.PollableSetPackage.UnknownPollable initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.PollableSetPackage.UnknownPollableHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.PollableSetPackage.UnknownPollableHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.PollableSetPackage.UnknownPollableHelper.write(_out, value);
	}
}
