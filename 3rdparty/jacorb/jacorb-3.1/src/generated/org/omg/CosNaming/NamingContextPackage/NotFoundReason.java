package org.omg.CosNaming.NamingContextPackage;
/**
 * Generated from IDL enum "NotFoundReason".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class NotFoundReason
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _missing_node = 0;
	public static final NotFoundReason missing_node = new NotFoundReason(_missing_node);
	public static final int _not_context = 1;
	public static final NotFoundReason not_context = new NotFoundReason(_not_context);
	public static final int _not_object = 2;
	public static final NotFoundReason not_object = new NotFoundReason(_not_object);
	public int value()
	{
		return value;
	}
	public static NotFoundReason from_int(int value)
	{
		switch (value) {
			case _missing_node: return missing_node;
			case _not_context: return not_context;
			case _not_object: return not_object;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _missing_node: return "missing_node";
			case _not_context: return "not_context";
			case _not_object: return "not_object";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected NotFoundReason(int i)
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
