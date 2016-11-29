package org.omg.Security;
/**
 * Generated from IDL enum "RequiresSupports".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class RequiresSupports
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecRequires = 0;
	public static final RequiresSupports SecRequires = new RequiresSupports(_SecRequires);
	public static final int _SecSupports = 1;
	public static final RequiresSupports SecSupports = new RequiresSupports(_SecSupports);
	public int value()
	{
		return value;
	}
	public static RequiresSupports from_int(int value)
	{
		switch (value) {
			case _SecRequires: return SecRequires;
			case _SecSupports: return SecSupports;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecRequires: return "SecRequires";
			case _SecSupports: return "SecSupports";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected RequiresSupports(int i)
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
