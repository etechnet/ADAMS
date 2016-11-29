package org.omg.CosTime;
/**
 * Generated from IDL enum "TimeComparison".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TimeComparison
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _TCEqualTo = 0;
	public static final TimeComparison TCEqualTo = new TimeComparison(_TCEqualTo);
	public static final int _TCLessThan = 1;
	public static final TimeComparison TCLessThan = new TimeComparison(_TCLessThan);
	public static final int _TCGreaterThan = 2;
	public static final TimeComparison TCGreaterThan = new TimeComparison(_TCGreaterThan);
	public static final int _TCIndeterminate = 3;
	public static final TimeComparison TCIndeterminate = new TimeComparison(_TCIndeterminate);
	public int value()
	{
		return value;
	}
	public static TimeComparison from_int(int value)
	{
		switch (value) {
			case _TCEqualTo: return TCEqualTo;
			case _TCLessThan: return TCLessThan;
			case _TCGreaterThan: return TCGreaterThan;
			case _TCIndeterminate: return TCIndeterminate;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _TCEqualTo: return "TCEqualTo";
			case _TCLessThan: return "TCLessThan";
			case _TCGreaterThan: return "TCGreaterThan";
			case _TCIndeterminate: return "TCIndeterminate";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected TimeComparison(int i)
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
