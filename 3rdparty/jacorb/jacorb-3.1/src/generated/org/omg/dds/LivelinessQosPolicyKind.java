package org.omg.dds;
/**
 * Generated from IDL enum "LivelinessQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LivelinessQosPolicyKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _AUTOMATIC_LIVELINESS_QOS = 0;
	public static final LivelinessQosPolicyKind AUTOMATIC_LIVELINESS_QOS = new LivelinessQosPolicyKind(_AUTOMATIC_LIVELINESS_QOS);
	public static final int _MANUAL_BY_PARTICIPANT_LIVELINESS_QOS = 1;
	public static final LivelinessQosPolicyKind MANUAL_BY_PARTICIPANT_LIVELINESS_QOS = new LivelinessQosPolicyKind(_MANUAL_BY_PARTICIPANT_LIVELINESS_QOS);
	public static final int _MANUAL_BY_TOPIC_LIVELINESS_QOS = 2;
	public static final LivelinessQosPolicyKind MANUAL_BY_TOPIC_LIVELINESS_QOS = new LivelinessQosPolicyKind(_MANUAL_BY_TOPIC_LIVELINESS_QOS);
	public int value()
	{
		return value;
	}
	public static LivelinessQosPolicyKind from_int(int value)
	{
		switch (value) {
			case _AUTOMATIC_LIVELINESS_QOS: return AUTOMATIC_LIVELINESS_QOS;
			case _MANUAL_BY_PARTICIPANT_LIVELINESS_QOS: return MANUAL_BY_PARTICIPANT_LIVELINESS_QOS;
			case _MANUAL_BY_TOPIC_LIVELINESS_QOS: return MANUAL_BY_TOPIC_LIVELINESS_QOS;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _AUTOMATIC_LIVELINESS_QOS: return "AUTOMATIC_LIVELINESS_QOS";
			case _MANUAL_BY_PARTICIPANT_LIVELINESS_QOS: return "MANUAL_BY_PARTICIPANT_LIVELINESS_QOS";
			case _MANUAL_BY_TOPIC_LIVELINESS_QOS: return "MANUAL_BY_TOPIC_LIVELINESS_QOS";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected LivelinessQosPolicyKind(int i)
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
