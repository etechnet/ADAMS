package org.omg.CosBridgeAdmin;
/**
 * Generated from IDL enum "FlowStyle".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class FlowStyle
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _PUSH = 0;
	public static final FlowStyle PUSH = new FlowStyle(_PUSH);
	public static final int _PULL = 1;
	public static final FlowStyle PULL = new FlowStyle(_PULL);
	public int value()
	{
		return value;
	}
	public static FlowStyle from_int(int value)
	{
		switch (value) {
			case _PUSH: return PUSH;
			case _PULL: return PULL;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _PUSH: return "PUSH";
			case _PULL: return "PULL";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected FlowStyle(int i)
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
