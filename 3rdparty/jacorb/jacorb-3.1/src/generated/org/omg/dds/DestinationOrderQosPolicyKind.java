package org.omg.dds;
/**
 * Generated from IDL enum "DestinationOrderQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DestinationOrderQosPolicyKind
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS = 0;
	public static final DestinationOrderQosPolicyKind BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS = new DestinationOrderQosPolicyKind(_BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS);
	public static final int _BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS = 1;
	public static final DestinationOrderQosPolicyKind BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS = new DestinationOrderQosPolicyKind(_BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS);
	public int value()
	{
		return value;
	}
	public static DestinationOrderQosPolicyKind from_int(int value)
	{
		switch (value) {
			case _BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS: return BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS;
			case _BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS: return BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS: return "BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS";
			case _BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS: return "BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected DestinationOrderQosPolicyKind(int i)
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
