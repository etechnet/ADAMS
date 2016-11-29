package org.omg.PortableServer;
/**
 * Generated from IDL enum "IdAssignmentPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class IdAssignmentPolicyValue
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _USER_ID = 0;
	public static final IdAssignmentPolicyValue USER_ID = new IdAssignmentPolicyValue(_USER_ID);
	public static final int _SYSTEM_ID = 1;
	public static final IdAssignmentPolicyValue SYSTEM_ID = new IdAssignmentPolicyValue(_SYSTEM_ID);
	public int value()
	{
		return value;
	}
	public static IdAssignmentPolicyValue from_int(int value)
	{
		switch (value) {
			case _USER_ID: return USER_ID;
			case _SYSTEM_ID: return SYSTEM_ID;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _USER_ID: return "USER_ID";
			case _SYSTEM_ID: return "SYSTEM_ID";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected IdAssignmentPolicyValue(int i)
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
