package org.omg.dds;
/**
 * Generated from IDL enum "ReliabilityQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ReliabilityQosPolicyKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _BEST_EFFORT_RELIABILITY_QOS = 0;
	public static final ReliabilityQosPolicyKind BEST_EFFORT_RELIABILITY_QOS = new ReliabilityQosPolicyKind(_BEST_EFFORT_RELIABILITY_QOS);
	public static final int _RELIABLE_RELIABILITY_QOS = 1;
	public static final ReliabilityQosPolicyKind RELIABLE_RELIABILITY_QOS = new ReliabilityQosPolicyKind(_RELIABLE_RELIABILITY_QOS);
	public int value()
	{
		return value;
	}
	public static ReliabilityQosPolicyKind from_int(int value)
	{
		switch (value) {
			case _BEST_EFFORT_RELIABILITY_QOS: return BEST_EFFORT_RELIABILITY_QOS;
			case _RELIABLE_RELIABILITY_QOS: return RELIABLE_RELIABILITY_QOS;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _BEST_EFFORT_RELIABILITY_QOS: return "BEST_EFFORT_RELIABILITY_QOS";
			case _RELIABLE_RELIABILITY_QOS: return "RELIABLE_RELIABILITY_QOS";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ReliabilityQosPolicyKind(int i)
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
