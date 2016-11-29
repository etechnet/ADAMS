package org.omg.GIOP;

/**
 * Generated from IDL struct "FragmentHeader_1_2".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class FragmentHeader_1_2Holder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.GIOP.FragmentHeader_1_2 value;

	public FragmentHeader_1_2Holder ()
	{
	}
	public FragmentHeader_1_2Holder(final org.omg.GIOP.FragmentHeader_1_2 initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.GIOP.FragmentHeader_1_2Helper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.GIOP.FragmentHeader_1_2Helper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.GIOP.FragmentHeader_1_2Helper.write(_out, value);
	}
}
