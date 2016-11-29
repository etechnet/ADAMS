package org.omg.Security;
/**
 * Generated from IDL enum "AuditCombinator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuditCombinator
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecAllSelectors = 0;
	public static final AuditCombinator SecAllSelectors = new AuditCombinator(_SecAllSelectors);
	public static final int _SecAnySelector = 1;
	public static final AuditCombinator SecAnySelector = new AuditCombinator(_SecAnySelector);
	public int value()
	{
		return value;
	}
	public static AuditCombinator from_int(int value)
	{
		switch (value) {
			case _SecAllSelectors: return SecAllSelectors;
			case _SecAnySelector: return SecAnySelector;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecAllSelectors: return "SecAllSelectors";
			case _SecAnySelector: return "SecAnySelector";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected AuditCombinator(int i)
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
