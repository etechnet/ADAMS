package org.omg.Security;
/**
 * Generated from IDL enum "CredentialType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CredentialType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecInvocationCredentials = 0;
	public static final CredentialType SecInvocationCredentials = new CredentialType(_SecInvocationCredentials);
	public static final int _SecNRCredentials = 1;
	public static final CredentialType SecNRCredentials = new CredentialType(_SecNRCredentials);
	public int value()
	{
		return value;
	}
	public static CredentialType from_int(int value)
	{
		switch (value) {
			case _SecInvocationCredentials: return SecInvocationCredentials;
			case _SecNRCredentials: return SecNRCredentials;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecInvocationCredentials: return "SecInvocationCredentials";
			case _SecNRCredentials: return "SecNRCredentials";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected CredentialType(int i)
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
