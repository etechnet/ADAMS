package org.omg.CORBA;

/**
 * Generated from IDL struct "StructMember".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class StructMemberHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.StructMember value;

	public StructMemberHolder ()
	{
	}
	public StructMemberHolder(final org.omg.CORBA.StructMember initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.StructMemberHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.StructMemberHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.StructMemberHelper.write(_out, value);
	}
}
