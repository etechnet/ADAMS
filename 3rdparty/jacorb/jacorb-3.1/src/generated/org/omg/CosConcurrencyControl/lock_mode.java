package org.omg.CosConcurrencyControl;
/**
 * Generated from IDL enum "lock_mode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class lock_mode
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _read = 0;
	public static final lock_mode read = new lock_mode(_read);
	public static final int _write = 1;
	public static final lock_mode write = new lock_mode(_write);
	public static final int _upgrade = 2;
	public static final lock_mode upgrade = new lock_mode(_upgrade);
	public static final int _intention_read = 3;
	public static final lock_mode intention_read = new lock_mode(_intention_read);
	public static final int _intention_write = 4;
	public static final lock_mode intention_write = new lock_mode(_intention_write);
	public int value()
	{
		return value;
	}
	public static lock_mode from_int(int value)
	{
		switch (value) {
			case _read: return read;
			case _write: return write;
			case _upgrade: return upgrade;
			case _intention_read: return intention_read;
			case _intention_write: return intention_write;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _read: return "read";
			case _write: return "write";
			case _upgrade: return "upgrade";
			case _intention_read: return "intention_read";
			case _intention_write: return "intention_write";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected lock_mode(int i)
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
