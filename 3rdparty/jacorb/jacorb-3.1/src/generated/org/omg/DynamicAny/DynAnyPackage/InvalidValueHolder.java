package org.omg.DynamicAny.DynAnyPackage;

/**
 * Generated from IDL exception "InvalidValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class InvalidValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.DynamicAny.DynAnyPackage.InvalidValue value;

	public InvalidValueHolder ()
	{
	}
	public InvalidValueHolder(final org.omg.DynamicAny.DynAnyPackage.InvalidValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.DynamicAny.DynAnyPackage.InvalidValueHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.DynamicAny.DynAnyPackage.InvalidValueHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.DynamicAny.DynAnyPackage.InvalidValueHelper.write(_out, value);
	}
}
