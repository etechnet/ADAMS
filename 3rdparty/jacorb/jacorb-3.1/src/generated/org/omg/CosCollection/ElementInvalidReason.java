package org.omg.CosCollection;
/**
 * Generated from IDL enum "ElementInvalidReason".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ElementInvalidReason
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _element_type_invalid = 0;
	public static final ElementInvalidReason element_type_invalid = new ElementInvalidReason(_element_type_invalid);
	public static final int _positioning_property_invalid = 1;
	public static final ElementInvalidReason positioning_property_invalid = new ElementInvalidReason(_positioning_property_invalid);
	public static final int _element_exists = 2;
	public static final ElementInvalidReason element_exists = new ElementInvalidReason(_element_exists);
	public int value()
	{
		return value;
	}
	public static ElementInvalidReason from_int(int value)
	{
		switch (value) {
			case _element_type_invalid: return element_type_invalid;
			case _positioning_property_invalid: return positioning_property_invalid;
			case _element_exists: return element_exists;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _element_type_invalid: return "element_type_invalid";
			case _positioning_property_invalid: return "positioning_property_invalid";
			case _element_exists: return "element_exists";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ElementInvalidReason(int i)
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
