package org.omg.Security;
/**
 * Generated from IDL enum "DayOfTheWeek".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class DayOfTheWeekHolder
	implements org.omg.CORBA.portable.Streamable
{
	public DayOfTheWeek value;

	public DayOfTheWeekHolder ()
	{
	}
	public DayOfTheWeekHolder (final DayOfTheWeek initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return DayOfTheWeekHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DayOfTheWeekHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DayOfTheWeekHelper.write (out,value);
	}
}