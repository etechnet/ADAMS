package org.omg.PortableServer;
/**
 * Generated from IDL enum "LifespanPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class LifespanPolicyValue
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _TRANSIENT = 0;
	public static final LifespanPolicyValue TRANSIENT = new LifespanPolicyValue(_TRANSIENT);
	public static final int _PERSISTENT = 1;
	public static final LifespanPolicyValue PERSISTENT = new LifespanPolicyValue(_PERSISTENT);
	public int value()
	{
		return value;
	}
	public static LifespanPolicyValue from_int(int value)
	{
		switch (value) {
			case _TRANSIENT: return TRANSIENT;
			case _PERSISTENT: return PERSISTENT;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _TRANSIENT: return "TRANSIENT";
			case _PERSISTENT: return "PERSISTENT";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected LifespanPolicyValue(int i)
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
