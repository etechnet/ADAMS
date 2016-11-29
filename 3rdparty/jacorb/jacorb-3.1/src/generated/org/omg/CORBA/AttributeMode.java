package org.omg.CORBA;
/**
 * Generated from IDL enum "AttributeMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class AttributeMode
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _ATTR_NORMAL = 0;
	public static final AttributeMode ATTR_NORMAL = new AttributeMode(_ATTR_NORMAL);
	public static final int _ATTR_READONLY = 1;
	public static final AttributeMode ATTR_READONLY = new AttributeMode(_ATTR_READONLY);
	public int value()
	{
		return value;
	}
	public static AttributeMode from_int(int value)
	{
		switch (value) {
			case _ATTR_NORMAL: return ATTR_NORMAL;
			case _ATTR_READONLY: return ATTR_READONLY;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _ATTR_NORMAL: return "ATTR_NORMAL";
			case _ATTR_READONLY: return "ATTR_READONLY";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected AttributeMode(int i)
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
