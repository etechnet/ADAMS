package org.omg.PortableServer;
/**
 * Generated from IDL enum "ImplicitActivationPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ImplicitActivationPolicyValue
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _IMPLICIT_ACTIVATION = 0;
	public static final ImplicitActivationPolicyValue IMPLICIT_ACTIVATION = new ImplicitActivationPolicyValue(_IMPLICIT_ACTIVATION);
	public static final int _NO_IMPLICIT_ACTIVATION = 1;
	public static final ImplicitActivationPolicyValue NO_IMPLICIT_ACTIVATION = new ImplicitActivationPolicyValue(_NO_IMPLICIT_ACTIVATION);
	public int value()
	{
		return value;
	}
	public static ImplicitActivationPolicyValue from_int(int value)
	{
		switch (value) {
			case _IMPLICIT_ACTIVATION: return IMPLICIT_ACTIVATION;
			case _NO_IMPLICIT_ACTIVATION: return NO_IMPLICIT_ACTIVATION;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _IMPLICIT_ACTIVATION: return "IMPLICIT_ACTIVATION";
			case _NO_IMPLICIT_ACTIVATION: return "NO_IMPLICIT_ACTIVATION";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ImplicitActivationPolicyValue(int i)
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
