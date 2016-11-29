package org.omg.CORBA;
/**
 * Generated from IDL enum "OperationMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class OperationMode
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _OP_NORMAL = 0;
	public static final OperationMode OP_NORMAL = new OperationMode(_OP_NORMAL);
	public static final int _OP_ONEWAY = 1;
	public static final OperationMode OP_ONEWAY = new OperationMode(_OP_ONEWAY);
	public int value()
	{
		return value;
	}
	public static OperationMode from_int(int value)
	{
		switch (value) {
			case _OP_NORMAL: return OP_NORMAL;
			case _OP_ONEWAY: return OP_ONEWAY;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _OP_NORMAL: return "OP_NORMAL";
			case _OP_ONEWAY: return "OP_ONEWAY";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected OperationMode(int i)
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
