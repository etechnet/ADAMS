package org.omg.Security;

/**
 * Generated from IDL alias "OptionsDirectionPairList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class OptionsDirectionPairListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Security.OptionsDirectionPair[] value;

	public OptionsDirectionPairListHolder ()
	{
	}
	public OptionsDirectionPairListHolder (final org.omg.Security.OptionsDirectionPair[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return OptionsDirectionPairListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = OptionsDirectionPairListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		OptionsDirectionPairListHelper.write (out,value);
	}
}
