package org.omg.dds;
/**
 * Generated from IDL enum "OwnershipQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OwnershipQosPolicyKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SHARED_OWNERSHIP_QOS = 0;
	public static final OwnershipQosPolicyKind SHARED_OWNERSHIP_QOS = new OwnershipQosPolicyKind(_SHARED_OWNERSHIP_QOS);
	public static final int _EXCLUSIVE_OWNERSHIP_QOS = 1;
	public static final OwnershipQosPolicyKind EXCLUSIVE_OWNERSHIP_QOS = new OwnershipQosPolicyKind(_EXCLUSIVE_OWNERSHIP_QOS);
	public int value()
	{
		return value;
	}
	public static OwnershipQosPolicyKind from_int(int value)
	{
		switch (value) {
			case _SHARED_OWNERSHIP_QOS: return SHARED_OWNERSHIP_QOS;
			case _EXCLUSIVE_OWNERSHIP_QOS: return EXCLUSIVE_OWNERSHIP_QOS;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SHARED_OWNERSHIP_QOS: return "SHARED_OWNERSHIP_QOS";
			case _EXCLUSIVE_OWNERSHIP_QOS: return "EXCLUSIVE_OWNERSHIP_QOS";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected OwnershipQosPolicyKind(int i)
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
