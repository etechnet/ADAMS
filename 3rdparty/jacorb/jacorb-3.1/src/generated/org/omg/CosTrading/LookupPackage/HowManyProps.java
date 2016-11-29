package org.omg.CosTrading.LookupPackage;
/**
 * Generated from IDL enum "HowManyProps".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class HowManyProps
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _none = 0;
	public static final HowManyProps none = new HowManyProps(_none);
	public static final int _some = 1;
	public static final HowManyProps some = new HowManyProps(_some);
	public static final int _all = 2;
	public static final HowManyProps all = new HowManyProps(_all);
	public int value()
	{
		return value;
	}
	public static HowManyProps from_int(int value)
	{
		switch (value) {
			case _none: return none;
			case _some: return some;
			case _all: return all;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _none: return "none";
			case _some: return "some";
			case _all: return "all";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected HowManyProps(int i)
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
