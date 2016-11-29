package org.omg.CORBA;

/**
 * Generated from IDL struct "ModuleDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ModuleDescriptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ModuleDescription value;

	public ModuleDescriptionHolder ()
	{
	}
	public ModuleDescriptionHolder(final org.omg.CORBA.ModuleDescription initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.ModuleDescriptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.ModuleDescriptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.ModuleDescriptionHelper.write(_out, value);
	}
}
