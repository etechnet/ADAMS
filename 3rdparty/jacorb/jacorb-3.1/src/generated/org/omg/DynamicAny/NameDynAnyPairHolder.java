package org.omg.DynamicAny;

/**
 * Generated from IDL struct "NameDynAnyPair".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NameDynAnyPairHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.DynamicAny.NameDynAnyPair value;

	public NameDynAnyPairHolder ()
	{
	}
	public NameDynAnyPairHolder(final org.omg.DynamicAny.NameDynAnyPair initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.DynamicAny.NameDynAnyPairHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.DynamicAny.NameDynAnyPairHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.DynamicAny.NameDynAnyPairHelper.write(_out, value);
	}
}
