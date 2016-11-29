package org.omg.CosBridgeAdmin;
/**
 * Generated from IDL enum "JMSDestinationType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class JMSDestinationType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _QUEUE = 0;
	public static final JMSDestinationType QUEUE = new JMSDestinationType(_QUEUE);
	public static final int _TOPIC = 1;
	public static final JMSDestinationType TOPIC = new JMSDestinationType(_TOPIC);
	public int value()
	{
		return value;
	}
	public static JMSDestinationType from_int(int value)
	{
		switch (value) {
			case _QUEUE: return QUEUE;
			case _TOPIC: return TOPIC;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _QUEUE: return "QUEUE";
			case _TOPIC: return "TOPIC";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected JMSDestinationType(int i)
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
