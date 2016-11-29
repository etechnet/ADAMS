package org.omg.RTCORBA;
/**
 * Generated from IDL enum "PriorityModel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PriorityModel
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _CLIENT_PROPAGATED = 0;
	public static final PriorityModel CLIENT_PROPAGATED = new PriorityModel(_CLIENT_PROPAGATED);
	public static final int _SERVER_DECLARED = 1;
	public static final PriorityModel SERVER_DECLARED = new PriorityModel(_SERVER_DECLARED);
	public int value()
	{
		return value;
	}
	public static PriorityModel from_int(int value)
	{
		switch (value) {
			case _CLIENT_PROPAGATED: return CLIENT_PROPAGATED;
			case _SERVER_DECLARED: return SERVER_DECLARED;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _CLIENT_PROPAGATED: return "CLIENT_PROPAGATED";
			case _SERVER_DECLARED: return "SERVER_DECLARED";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected PriorityModel(int i)
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
