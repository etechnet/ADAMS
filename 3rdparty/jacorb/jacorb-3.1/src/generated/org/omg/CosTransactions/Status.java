package org.omg.CosTransactions;
/**
 * Generated from IDL enum "Status".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class Status
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _StatusActive = 0;
	public static final Status StatusActive = new Status(_StatusActive);
	public static final int _StatusMarkedRollback = 1;
	public static final Status StatusMarkedRollback = new Status(_StatusMarkedRollback);
	public static final int _StatusPrepared = 2;
	public static final Status StatusPrepared = new Status(_StatusPrepared);
	public static final int _StatusCommitted = 3;
	public static final Status StatusCommitted = new Status(_StatusCommitted);
	public static final int _StatusRolledBack = 4;
	public static final Status StatusRolledBack = new Status(_StatusRolledBack);
	public static final int _StatusUnknown = 5;
	public static final Status StatusUnknown = new Status(_StatusUnknown);
	public static final int _StatusNoTransaction = 6;
	public static final Status StatusNoTransaction = new Status(_StatusNoTransaction);
	public static final int _StatusPreparing = 7;
	public static final Status StatusPreparing = new Status(_StatusPreparing);
	public static final int _StatusCommitting = 8;
	public static final Status StatusCommitting = new Status(_StatusCommitting);
	public static final int _StatusRollingBack = 9;
	public static final Status StatusRollingBack = new Status(_StatusRollingBack);
	public int value()
	{
		return value;
	}
	public static Status from_int(int value)
	{
		switch (value) {
			case _StatusActive: return StatusActive;
			case _StatusMarkedRollback: return StatusMarkedRollback;
			case _StatusPrepared: return StatusPrepared;
			case _StatusCommitted: return StatusCommitted;
			case _StatusRolledBack: return StatusRolledBack;
			case _StatusUnknown: return StatusUnknown;
			case _StatusNoTransaction: return StatusNoTransaction;
			case _StatusPreparing: return StatusPreparing;
			case _StatusCommitting: return StatusCommitting;
			case _StatusRollingBack: return StatusRollingBack;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _StatusActive: return "StatusActive";
			case _StatusMarkedRollback: return "StatusMarkedRollback";
			case _StatusPrepared: return "StatusPrepared";
			case _StatusCommitted: return "StatusCommitted";
			case _StatusRolledBack: return "StatusRolledBack";
			case _StatusUnknown: return "StatusUnknown";
			case _StatusNoTransaction: return "StatusNoTransaction";
			case _StatusPreparing: return "StatusPreparing";
			case _StatusCommitting: return "StatusCommitting";
			case _StatusRollingBack: return "StatusRollingBack";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected Status(int i)
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
