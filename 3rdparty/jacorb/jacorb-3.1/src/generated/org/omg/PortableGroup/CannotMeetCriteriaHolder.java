package org.omg.PortableGroup;

/**
 * Generated from IDL exception "CannotMeetCriteria".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class CannotMeetCriteriaHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.CannotMeetCriteria value;

	public CannotMeetCriteriaHolder ()
	{
	}
	public CannotMeetCriteriaHolder(final org.omg.PortableGroup.CannotMeetCriteria initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.CannotMeetCriteriaHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.CannotMeetCriteriaHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.CannotMeetCriteriaHelper.write(_out, value);
	}
}
