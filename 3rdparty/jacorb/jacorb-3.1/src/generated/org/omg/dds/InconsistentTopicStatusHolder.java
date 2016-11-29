package org.omg.dds;

/**
 * Generated from IDL struct "InconsistentTopicStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InconsistentTopicStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.InconsistentTopicStatus value;

	public InconsistentTopicStatusHolder ()
	{
	}
	public InconsistentTopicStatusHolder(final org.omg.dds.InconsistentTopicStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.InconsistentTopicStatusHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.InconsistentTopicStatusHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.InconsistentTopicStatusHelper.write(_out, value);
	}
}
