package org.omg.dds;

/**
 * Generated from IDL struct "OfferedDeadlineMissedStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OfferedDeadlineMissedStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.OfferedDeadlineMissedStatus value;

	public OfferedDeadlineMissedStatusHolder ()
	{
	}
	public OfferedDeadlineMissedStatusHolder(final org.omg.dds.OfferedDeadlineMissedStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.OfferedDeadlineMissedStatusHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.OfferedDeadlineMissedStatusHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.OfferedDeadlineMissedStatusHelper.write(_out, value);
	}
}
