package org.omg.CosCollection;
/**
 * Generated from IDL enum "IteratorInvalidReason".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IteratorInvalidReason
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _is_invalid = 0;
	public static final IteratorInvalidReason is_invalid = new IteratorInvalidReason(_is_invalid);
	public static final int _is_not_for_collection = 1;
	public static final IteratorInvalidReason is_not_for_collection = new IteratorInvalidReason(_is_not_for_collection);
	public static final int _is_const = 2;
	public static final IteratorInvalidReason is_const = new IteratorInvalidReason(_is_const);
	public int value()
	{
		return value;
	}
	public static IteratorInvalidReason from_int(int value)
	{
		switch (value) {
			case _is_invalid: return is_invalid;
			case _is_not_for_collection: return is_not_for_collection;
			case _is_const: return is_const;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _is_invalid: return "is_invalid";
			case _is_not_for_collection: return "is_not_for_collection";
			case _is_const: return "is_const";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected IteratorInvalidReason(int i)
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
