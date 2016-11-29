package org.omg.Security;
/**
 * Generated from IDL enum "SecurityContextType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SecurityContextType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecClientSecurityContext = 0;
	public static final SecurityContextType SecClientSecurityContext = new SecurityContextType(_SecClientSecurityContext);
	public static final int _SecServerSecurityContext = 1;
	public static final SecurityContextType SecServerSecurityContext = new SecurityContextType(_SecServerSecurityContext);
	public int value()
	{
		return value;
	}
	public static SecurityContextType from_int(int value)
	{
		switch (value) {
			case _SecClientSecurityContext: return SecClientSecurityContext;
			case _SecServerSecurityContext: return SecServerSecurityContext;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecClientSecurityContext: return "SecClientSecurityContext";
			case _SecServerSecurityContext: return "SecServerSecurityContext";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected SecurityContextType(int i)
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
