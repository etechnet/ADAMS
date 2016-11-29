package org.omg.CosNotifyCommAck;

/**
 * Generated from IDL alias "SequenceNumbers".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SequenceNumbersHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public SequenceNumbersHolder ()
	{
	}
	public SequenceNumbersHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return SequenceNumbersHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SequenceNumbersHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		SequenceNumbersHelper.write (out,value);
	}
}
