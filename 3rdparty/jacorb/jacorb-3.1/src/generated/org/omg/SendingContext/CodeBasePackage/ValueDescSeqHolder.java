package org.omg.SendingContext.CodeBasePackage;

/**
 * Generated from IDL alias "ValueDescSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ValueDescSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ValueDefPackage.FullValueDescription[] value;

	public ValueDescSeqHolder ()
	{
	}
	public ValueDescSeqHolder (final org.omg.CORBA.ValueDefPackage.FullValueDescription[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ValueDescSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ValueDescSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ValueDescSeqHelper.write (out,value);
	}
}
