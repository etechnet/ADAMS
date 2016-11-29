package org.omg.dds;

/**
 * Generated from IDL alias "QosPolicyCountSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class QosPolicyCountSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.QosPolicyCount[] value;

	public QosPolicyCountSeqHolder ()
	{
	}
	public QosPolicyCountSeqHolder (final org.omg.dds.QosPolicyCount[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return QosPolicyCountSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = QosPolicyCountSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		QosPolicyCountSeqHelper.write (out,value);
	}
}
