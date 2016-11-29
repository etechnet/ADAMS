package org.omg.dds;

/**
 * Generated from IDL alias "BuiltinTopicKey_t".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class BuiltinTopicKey_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public BuiltinTopicKey_tHolder ()
	{
	}
	public BuiltinTopicKey_tHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return BuiltinTopicKey_tHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = BuiltinTopicKey_tHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		BuiltinTopicKey_tHelper.write (out,value);
	}
}
