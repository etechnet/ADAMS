package org.omg.PortableServer.POAManagerPackage;
/**
 * Generated from IDL enum "State".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class State
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _HOLDING = 0;
	public static final State HOLDING = new State(_HOLDING);
	public static final int _ACTIVE = 1;
	public static final State ACTIVE = new State(_ACTIVE);
	public static final int _DISCARDING = 2;
	public static final State DISCARDING = new State(_DISCARDING);
	public static final int _INACTIVE = 3;
	public static final State INACTIVE = new State(_INACTIVE);
	public int value()
	{
		return value;
	}
	public static State from_int(int value)
	{
		switch (value) {
			case _HOLDING: return HOLDING;
			case _ACTIVE: return ACTIVE;
			case _DISCARDING: return DISCARDING;
			case _INACTIVE: return INACTIVE;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _HOLDING: return "HOLDING";
			case _ACTIVE: return "ACTIVE";
			case _DISCARDING: return "DISCARDING";
			case _INACTIVE: return "INACTIVE";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected State(int i)
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
