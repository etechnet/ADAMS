package org.omg.Security;
/**
 * Generated from IDL enum "AuthenticationStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthenticationStatus
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecAuthSuccess = 0;
	public static final AuthenticationStatus SecAuthSuccess = new AuthenticationStatus(_SecAuthSuccess);
	public static final int _SecAuthFailure = 1;
	public static final AuthenticationStatus SecAuthFailure = new AuthenticationStatus(_SecAuthFailure);
	public static final int _SecAuthContinue = 2;
	public static final AuthenticationStatus SecAuthContinue = new AuthenticationStatus(_SecAuthContinue);
	public static final int _SecAuthExpired = 3;
	public static final AuthenticationStatus SecAuthExpired = new AuthenticationStatus(_SecAuthExpired);
	public int value()
	{
		return value;
	}
	public static AuthenticationStatus from_int(int value)
	{
		switch (value) {
			case _SecAuthSuccess: return SecAuthSuccess;
			case _SecAuthFailure: return SecAuthFailure;
			case _SecAuthContinue: return SecAuthContinue;
			case _SecAuthExpired: return SecAuthExpired;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecAuthSuccess: return "SecAuthSuccess";
			case _SecAuthFailure: return "SecAuthFailure";
			case _SecAuthContinue: return "SecAuthContinue";
			case _SecAuthExpired: return "SecAuthExpired";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected AuthenticationStatus(int i)
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
