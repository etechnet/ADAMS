package org.omg.Security;
/**
 * Generated from IDL enum "DelegationMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class DelegationMode
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecDelModeNoDelegation = 0;
	public static final DelegationMode SecDelModeNoDelegation = new DelegationMode(_SecDelModeNoDelegation);
	public static final int _SecDelModeSimpleDelegation = 1;
	public static final DelegationMode SecDelModeSimpleDelegation = new DelegationMode(_SecDelModeSimpleDelegation);
	public static final int _SecDelModeCompositeDelegation = 2;
	public static final DelegationMode SecDelModeCompositeDelegation = new DelegationMode(_SecDelModeCompositeDelegation);
	public int value()
	{
		return value;
	}
	public static DelegationMode from_int(int value)
	{
		switch (value) {
			case _SecDelModeNoDelegation: return SecDelModeNoDelegation;
			case _SecDelModeSimpleDelegation: return SecDelModeSimpleDelegation;
			case _SecDelModeCompositeDelegation: return SecDelModeCompositeDelegation;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecDelModeNoDelegation: return "SecDelModeNoDelegation";
			case _SecDelModeSimpleDelegation: return "SecDelModeSimpleDelegation";
			case _SecDelModeCompositeDelegation: return "SecDelModeCompositeDelegation";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected DelegationMode(int i)
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
