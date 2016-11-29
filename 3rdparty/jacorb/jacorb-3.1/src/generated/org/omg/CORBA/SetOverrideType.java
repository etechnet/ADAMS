package org.omg.CORBA;
/**
 * Generated from IDL enum "SetOverrideType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class SetOverrideType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SET_OVERRIDE = 0;
	public static final SetOverrideType SET_OVERRIDE = new SetOverrideType(_SET_OVERRIDE);
	public static final int _ADD_OVERRIDE = 1;
	public static final SetOverrideType ADD_OVERRIDE = new SetOverrideType(_ADD_OVERRIDE);
	public int value()
	{
		return value;
	}
	public static SetOverrideType from_int(int value)
	{
		switch (value) {
			case _SET_OVERRIDE: return SET_OVERRIDE;
			case _ADD_OVERRIDE: return ADD_OVERRIDE;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SET_OVERRIDE: return "SET_OVERRIDE";
			case _ADD_OVERRIDE: return "ADD_OVERRIDE";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected SetOverrideType(int i)
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
