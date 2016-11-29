package org.omg.dds;
/**
 * Generated from IDL enum "SampleRejectedStatusKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SampleRejectedStatusKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _REJECTED_BY_INSTANCE_LIMIT = 0;
	public static final SampleRejectedStatusKind REJECTED_BY_INSTANCE_LIMIT = new SampleRejectedStatusKind(_REJECTED_BY_INSTANCE_LIMIT);
	public static final int _REJECTED_BY_TOPIC_LIMIT = 1;
	public static final SampleRejectedStatusKind REJECTED_BY_TOPIC_LIMIT = new SampleRejectedStatusKind(_REJECTED_BY_TOPIC_LIMIT);
	public int value()
	{
		return value;
	}
	public static SampleRejectedStatusKind from_int(int value)
	{
		switch (value) {
			case _REJECTED_BY_INSTANCE_LIMIT: return REJECTED_BY_INSTANCE_LIMIT;
			case _REJECTED_BY_TOPIC_LIMIT: return REJECTED_BY_TOPIC_LIMIT;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _REJECTED_BY_INSTANCE_LIMIT: return "REJECTED_BY_INSTANCE_LIMIT";
			case _REJECTED_BY_TOPIC_LIMIT: return "REJECTED_BY_TOPIC_LIMIT";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected SampleRejectedStatusKind(int i)
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
