package org.omg.PortableGroup;

/**
 * Generated from IDL exception "InvalidCriteria".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class InvalidCriteriaHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.InvalidCriteria value;

	public InvalidCriteriaHolder ()
	{
	}
	public InvalidCriteriaHolder(final org.omg.PortableGroup.InvalidCriteria initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.InvalidCriteriaHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.InvalidCriteriaHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.InvalidCriteriaHelper.write(_out, value);
	}
}
