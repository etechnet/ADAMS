package org.jacorb.ssl;
/**
 * Generated from IDL enum "SSLPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class SSLPolicyValue
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SSL_NOT_REQUIRED = 0;
	public static final SSLPolicyValue SSL_NOT_REQUIRED = new SSLPolicyValue(_SSL_NOT_REQUIRED);
	public static final int _SSL_REQUIRED = 1;
	public static final SSLPolicyValue SSL_REQUIRED = new SSLPolicyValue(_SSL_REQUIRED);
	public int value()
	{
		return value;
	}
	public static SSLPolicyValue from_int(int value)
	{
		switch (value) {
			case _SSL_NOT_REQUIRED: return SSL_NOT_REQUIRED;
			case _SSL_REQUIRED: return SSL_REQUIRED;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SSL_NOT_REQUIRED: return "SSL_NOT_REQUIRED";
			case _SSL_REQUIRED: return "SSL_REQUIRED";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected SSLPolicyValue(int i)
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
