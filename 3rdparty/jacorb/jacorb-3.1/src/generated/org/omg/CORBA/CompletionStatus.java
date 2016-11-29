package org.omg.CORBA;
/**
 * Generated from IDL enum "CompletionStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class CompletionStatus
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _COMPLETED_YES = 0;
	public static final CompletionStatus COMPLETED_YES = new CompletionStatus(_COMPLETED_YES);
	public static final int _COMPLETED_NO = 1;
	public static final CompletionStatus COMPLETED_NO = new CompletionStatus(_COMPLETED_NO);
	public static final int _COMPLETED_MAYBE = 2;
	public static final CompletionStatus COMPLETED_MAYBE = new CompletionStatus(_COMPLETED_MAYBE);
	public int value()
	{
		return value;
	}
	public static CompletionStatus from_int(int value)
	{
		switch (value) {
			case _COMPLETED_YES: return COMPLETED_YES;
			case _COMPLETED_NO: return COMPLETED_NO;
			case _COMPLETED_MAYBE: return COMPLETED_MAYBE;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _COMPLETED_YES: return "COMPLETED_YES";
			case _COMPLETED_NO: return "COMPLETED_NO";
			case _COMPLETED_MAYBE: return "COMPLETED_MAYBE";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected CompletionStatus(int i)
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
