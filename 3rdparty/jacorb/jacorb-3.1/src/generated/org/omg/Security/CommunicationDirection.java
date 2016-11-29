package org.omg.Security;
/**
 * Generated from IDL enum "CommunicationDirection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CommunicationDirection
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecDirectionBoth = 0;
	public static final CommunicationDirection SecDirectionBoth = new CommunicationDirection(_SecDirectionBoth);
	public static final int _SecDirectionRequest = 1;
	public static final CommunicationDirection SecDirectionRequest = new CommunicationDirection(_SecDirectionRequest);
	public static final int _SecDirectionReply = 2;
	public static final CommunicationDirection SecDirectionReply = new CommunicationDirection(_SecDirectionReply);
	public int value()
	{
		return value;
	}
	public static CommunicationDirection from_int(int value)
	{
		switch (value) {
			case _SecDirectionBoth: return SecDirectionBoth;
			case _SecDirectionRequest: return SecDirectionRequest;
			case _SecDirectionReply: return SecDirectionReply;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecDirectionBoth: return "SecDirectionBoth";
			case _SecDirectionRequest: return "SecDirectionRequest";
			case _SecDirectionReply: return "SecDirectionReply";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected CommunicationDirection(int i)
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
