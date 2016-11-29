package org.omg.Security;
/**
 * Generated from IDL enum "DayOfTheWeek".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class DayOfTheWeek
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _Monday = 0;
	public static final DayOfTheWeek Monday = new DayOfTheWeek(_Monday);
	public static final int _Tuesday = 1;
	public static final DayOfTheWeek Tuesday = new DayOfTheWeek(_Tuesday);
	public static final int _Wednesday = 2;
	public static final DayOfTheWeek Wednesday = new DayOfTheWeek(_Wednesday);
	public static final int _Thursday = 3;
	public static final DayOfTheWeek Thursday = new DayOfTheWeek(_Thursday);
	public static final int _Friday = 4;
	public static final DayOfTheWeek Friday = new DayOfTheWeek(_Friday);
	public static final int _Saturday = 5;
	public static final DayOfTheWeek Saturday = new DayOfTheWeek(_Saturday);
	public static final int _Sunday = 6;
	public static final DayOfTheWeek Sunday = new DayOfTheWeek(_Sunday);
	public int value()
	{
		return value;
	}
	public static DayOfTheWeek from_int(int value)
	{
		switch (value) {
			case _Monday: return Monday;
			case _Tuesday: return Tuesday;
			case _Wednesday: return Wednesday;
			case _Thursday: return Thursday;
			case _Friday: return Friday;
			case _Saturday: return Saturday;
			case _Sunday: return Sunday;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _Monday: return "Monday";
			case _Tuesday: return "Tuesday";
			case _Wednesday: return "Wednesday";
			case _Thursday: return "Thursday";
			case _Friday: return "Friday";
			case _Saturday: return "Saturday";
			case _Sunday: return "Sunday";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected DayOfTheWeek(int i)
	{
		value = i;
	}
	/**
	 * Designate replacement object when deserialized from stream. See
	 * http://www.omg.org/docs/ptc/02-01-03.htm#Issue4271
	 *
	 * @throws java.io.ObjectStreamException
	 */
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
