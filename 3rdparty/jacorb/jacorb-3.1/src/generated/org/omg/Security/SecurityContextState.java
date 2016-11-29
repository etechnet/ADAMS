package org.omg.Security;
/**
 * Generated from IDL enum "SecurityContextState".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SecurityContextState
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecContextInitialized = 0;
	public static final SecurityContextState SecContextInitialized = new SecurityContextState(_SecContextInitialized);
	public static final int _SecContextContinued = 1;
	public static final SecurityContextState SecContextContinued = new SecurityContextState(_SecContextContinued);
	public static final int _SecContextClientEstablished = 2;
	public static final SecurityContextState SecContextClientEstablished = new SecurityContextState(_SecContextClientEstablished);
	public static final int _SecContextEstablished = 3;
	public static final SecurityContextState SecContextEstablished = new SecurityContextState(_SecContextEstablished);
	public static final int _SecContextEstablishExpired = 4;
	public static final SecurityContextState SecContextEstablishExpired = new SecurityContextState(_SecContextEstablishExpired);
	public static final int _SecContextExpired = 5;
	public static final SecurityContextState SecContextExpired = new SecurityContextState(_SecContextExpired);
	public static final int _SecContextInvalid = 6;
	public static final SecurityContextState SecContextInvalid = new SecurityContextState(_SecContextInvalid);
	public int value()
	{
		return value;
	}
	public static SecurityContextState from_int(int value)
	{
		switch (value) {
			case _SecContextInitialized: return SecContextInitialized;
			case _SecContextContinued: return SecContextContinued;
			case _SecContextClientEstablished: return SecContextClientEstablished;
			case _SecContextEstablished: return SecContextEstablished;
			case _SecContextEstablishExpired: return SecContextEstablishExpired;
			case _SecContextExpired: return SecContextExpired;
			case _SecContextInvalid: return SecContextInvalid;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecContextInitialized: return "SecContextInitialized";
			case _SecContextContinued: return "SecContextContinued";
			case _SecContextClientEstablished: return "SecContextClientEstablished";
			case _SecContextEstablished: return "SecContextEstablished";
			case _SecContextEstablishExpired: return "SecContextEstablishExpired";
			case _SecContextExpired: return "SecContextExpired";
			case _SecContextInvalid: return "SecContextInvalid";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected SecurityContextState(int i)
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
