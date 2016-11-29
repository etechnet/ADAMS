package org.omg.CosTrading;
/**
 * Generated from IDL enum "FollowOption".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class FollowOption
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _local_only = 0;
	public static final FollowOption local_only = new FollowOption(_local_only);
	public static final int _if_no_local = 1;
	public static final FollowOption if_no_local = new FollowOption(_if_no_local);
	public static final int _always = 2;
	public static final FollowOption always = new FollowOption(_always);
	public int value()
	{
		return value;
	}
	public static FollowOption from_int(int value)
	{
		switch (value) {
			case _local_only: return local_only;
			case _if_no_local: return if_no_local;
			case _always: return always;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _local_only: return "local_only";
			case _if_no_local: return "if_no_local";
			case _always: return "always";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected FollowOption(int i)
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
