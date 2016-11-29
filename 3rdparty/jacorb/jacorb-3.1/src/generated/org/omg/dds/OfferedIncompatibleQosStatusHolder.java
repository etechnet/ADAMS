package org.omg.dds;

/**
 * Generated from IDL struct "OfferedIncompatibleQosStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OfferedIncompatibleQosStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.OfferedIncompatibleQosStatus value;

	public OfferedIncompatibleQosStatusHolder ()
	{
	}
	public OfferedIncompatibleQosStatusHolder(final org.omg.dds.OfferedIncompatibleQosStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.OfferedIncompatibleQosStatusHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.OfferedIncompatibleQosStatusHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.OfferedIncompatibleQosStatusHelper.write(_out, value);
	}
}
