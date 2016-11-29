package org.omg.dds;

/**
 * Generated from IDL struct "QosPolicyCount".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class QosPolicyCountHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.QosPolicyCount value;

	public QosPolicyCountHolder ()
	{
	}
	public QosPolicyCountHolder(final org.omg.dds.QosPolicyCount initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.QosPolicyCountHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.QosPolicyCountHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.QosPolicyCountHelper.write(_out, value);
	}
}
