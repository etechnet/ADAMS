package org.omg.CosTime;
/**
 * Generated from IDL enum "ComparisonType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ComparisonType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _IntervalC = 0;
	public static final ComparisonType IntervalC = new ComparisonType(_IntervalC);
	public static final int _MidC = 1;
	public static final ComparisonType MidC = new ComparisonType(_MidC);
	public int value()
	{
		return value;
	}
	public static ComparisonType from_int(int value)
	{
		switch (value) {
			case _IntervalC: return IntervalC;
			case _MidC: return MidC;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _IntervalC: return "IntervalC";
			case _MidC: return "MidC";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ComparisonType(int i)
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
