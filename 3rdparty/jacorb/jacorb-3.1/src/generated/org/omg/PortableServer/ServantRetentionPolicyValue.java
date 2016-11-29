package org.omg.PortableServer;
/**
 * Generated from IDL enum "ServantRetentionPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ServantRetentionPolicyValue
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _RETAIN = 0;
	public static final ServantRetentionPolicyValue RETAIN = new ServantRetentionPolicyValue(_RETAIN);
	public static final int _NON_RETAIN = 1;
	public static final ServantRetentionPolicyValue NON_RETAIN = new ServantRetentionPolicyValue(_NON_RETAIN);
	public int value()
	{
		return value;
	}
	public static ServantRetentionPolicyValue from_int(int value)
	{
		switch (value) {
			case _RETAIN: return RETAIN;
			case _NON_RETAIN: return NON_RETAIN;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _RETAIN: return "RETAIN";
			case _NON_RETAIN: return "NON_RETAIN";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ServantRetentionPolicyValue(int i)
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
