package org.omg.CosBridgeAdmin;
/**
 * Generated from IDL enum "MessageType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class MessageType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _JMS_MESSAGE = 0;
	public static final MessageType JMS_MESSAGE = new MessageType(_JMS_MESSAGE);
	public static final int _STRUCTURED_EVENT = 1;
	public static final MessageType STRUCTURED_EVENT = new MessageType(_STRUCTURED_EVENT);
	public static final int _SEQUENCE_EVENT = 2;
	public static final MessageType SEQUENCE_EVENT = new MessageType(_SEQUENCE_EVENT);
	public int value()
	{
		return value;
	}
	public static MessageType from_int(int value)
	{
		switch (value) {
			case _JMS_MESSAGE: return JMS_MESSAGE;
			case _STRUCTURED_EVENT: return STRUCTURED_EVENT;
			case _SEQUENCE_EVENT: return SEQUENCE_EVENT;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _JMS_MESSAGE: return "JMS_MESSAGE";
			case _STRUCTURED_EVENT: return "STRUCTURED_EVENT";
			case _SEQUENCE_EVENT: return "SEQUENCE_EVENT";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected MessageType(int i)
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
