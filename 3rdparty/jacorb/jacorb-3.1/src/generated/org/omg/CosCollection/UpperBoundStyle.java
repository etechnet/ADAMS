package org.omg.CosCollection;
/**
 * Generated from IDL enum "UpperBoundStyle".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UpperBoundStyle
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _equal_up = 0;
	public static final UpperBoundStyle equal_up = new UpperBoundStyle(_equal_up);
	public static final int _less = 1;
	public static final UpperBoundStyle less = new UpperBoundStyle(_less);
	public static final int _less_or_equal = 2;
	public static final UpperBoundStyle less_or_equal = new UpperBoundStyle(_less_or_equal);
	public int value()
	{
		return value;
	}
	public static UpperBoundStyle from_int(int value)
	{
		switch (value) {
			case _equal_up: return equal_up;
			case _less: return less;
			case _less_or_equal: return less_or_equal;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _equal_up: return "equal_up";
			case _less: return "less";
			case _less_or_equal: return "less_or_equal";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected UpperBoundStyle(int i)
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
