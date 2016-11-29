package org.omg.DynamicAny.DynAnyPackage;

/**
 * Generated from IDL exception "TypeMismatch".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class TypeMismatchHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.DynamicAny.DynAnyPackage.TypeMismatch value;

	public TypeMismatchHolder ()
	{
	}
	public TypeMismatchHolder(final org.omg.DynamicAny.DynAnyPackage.TypeMismatch initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.DynamicAny.DynAnyPackage.TypeMismatchHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.DynamicAny.DynAnyPackage.TypeMismatchHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.DynamicAny.DynAnyPackage.TypeMismatchHelper.write(_out, value);
	}
}
