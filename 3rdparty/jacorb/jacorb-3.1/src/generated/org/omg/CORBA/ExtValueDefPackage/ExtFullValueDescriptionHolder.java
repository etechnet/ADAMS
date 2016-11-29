package org.omg.CORBA.ExtValueDefPackage;

/**
 * Generated from IDL struct "ExtFullValueDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtFullValueDescriptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ExtValueDefPackage.ExtFullValueDescription value;

	public ExtFullValueDescriptionHolder ()
	{
	}
	public ExtFullValueDescriptionHolder(final org.omg.CORBA.ExtValueDefPackage.ExtFullValueDescription initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.ExtValueDefPackage.ExtFullValueDescriptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.ExtValueDefPackage.ExtFullValueDescriptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.ExtValueDefPackage.ExtFullValueDescriptionHelper.write(_out, value);
	}
}
