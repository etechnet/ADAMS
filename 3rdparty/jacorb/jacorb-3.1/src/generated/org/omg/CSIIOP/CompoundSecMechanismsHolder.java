package org.omg.CSIIOP;

/**
 * Generated from IDL alias "CompoundSecMechanisms".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CompoundSecMechanismsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSIIOP.CompoundSecMech[] value;

	public CompoundSecMechanismsHolder ()
	{
	}
	public CompoundSecMechanismsHolder (final org.omg.CSIIOP.CompoundSecMech[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return CompoundSecMechanismsHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CompoundSecMechanismsHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CompoundSecMechanismsHelper.write (out,value);
	}
}
