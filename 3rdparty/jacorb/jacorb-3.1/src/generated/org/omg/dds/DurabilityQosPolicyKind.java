package org.omg.dds;
/**
 * Generated from IDL enum "DurabilityQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DurabilityQosPolicyKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _VOLATILE_DURABILITY_QOS = 0;
	public static final DurabilityQosPolicyKind VOLATILE_DURABILITY_QOS = new DurabilityQosPolicyKind(_VOLATILE_DURABILITY_QOS);
	public static final int _TRANSIENT_LOCAL_DURABILITY_QOS = 1;
	public static final DurabilityQosPolicyKind TRANSIENT_LOCAL_DURABILITY_QOS = new DurabilityQosPolicyKind(_TRANSIENT_LOCAL_DURABILITY_QOS);
	public static final int _TRANSIENT_DURABILITY_QOS = 2;
	public static final DurabilityQosPolicyKind TRANSIENT_DURABILITY_QOS = new DurabilityQosPolicyKind(_TRANSIENT_DURABILITY_QOS);
	public static final int _PERSISTENT_DURABILITY_QOS = 3;
	public static final DurabilityQosPolicyKind PERSISTENT_DURABILITY_QOS = new DurabilityQosPolicyKind(_PERSISTENT_DURABILITY_QOS);
	public int value()
	{
		return value;
	}
	public static DurabilityQosPolicyKind from_int(int value)
	{
		switch (value) {
			case _VOLATILE_DURABILITY_QOS: return VOLATILE_DURABILITY_QOS;
			case _TRANSIENT_LOCAL_DURABILITY_QOS: return TRANSIENT_LOCAL_DURABILITY_QOS;
			case _TRANSIENT_DURABILITY_QOS: return TRANSIENT_DURABILITY_QOS;
			case _PERSISTENT_DURABILITY_QOS: return PERSISTENT_DURABILITY_QOS;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _VOLATILE_DURABILITY_QOS: return "VOLATILE_DURABILITY_QOS";
			case _TRANSIENT_LOCAL_DURABILITY_QOS: return "TRANSIENT_LOCAL_DURABILITY_QOS";
			case _TRANSIENT_DURABILITY_QOS: return "TRANSIENT_DURABILITY_QOS";
			case _PERSISTENT_DURABILITY_QOS: return "PERSISTENT_DURABILITY_QOS";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected DurabilityQosPolicyKind(int i)
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
