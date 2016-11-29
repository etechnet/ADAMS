package org.omg.dds;
/**
 * Generated from IDL enum "HistoryQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class HistoryQosPolicyKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _KEEP_LAST_HISTORY_QOS = 0;
	public static final HistoryQosPolicyKind KEEP_LAST_HISTORY_QOS = new HistoryQosPolicyKind(_KEEP_LAST_HISTORY_QOS);
	public static final int _KEEP_ALL_HISTORY_QOS = 1;
	public static final HistoryQosPolicyKind KEEP_ALL_HISTORY_QOS = new HistoryQosPolicyKind(_KEEP_ALL_HISTORY_QOS);
	public int value()
	{
		return value;
	}
	public static HistoryQosPolicyKind from_int(int value)
	{
		switch (value) {
			case _KEEP_LAST_HISTORY_QOS: return KEEP_LAST_HISTORY_QOS;
			case _KEEP_ALL_HISTORY_QOS: return KEEP_ALL_HISTORY_QOS;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _KEEP_LAST_HISTORY_QOS: return "KEEP_LAST_HISTORY_QOS";
			case _KEEP_ALL_HISTORY_QOS: return "KEEP_ALL_HISTORY_QOS";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected HistoryQosPolicyKind(int i)
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
