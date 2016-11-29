package org.omg.CosTransactions;
/**
 * Generated from IDL enum "Vote".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class Vote
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _VoteCommit = 0;
	public static final Vote VoteCommit = new Vote(_VoteCommit);
	public static final int _VoteRollback = 1;
	public static final Vote VoteRollback = new Vote(_VoteRollback);
	public static final int _VoteReadOnly = 2;
	public static final Vote VoteReadOnly = new Vote(_VoteReadOnly);
	public int value()
	{
		return value;
	}
	public static Vote from_int(int value)
	{
		switch (value) {
			case _VoteCommit: return VoteCommit;
			case _VoteRollback: return VoteRollback;
			case _VoteReadOnly: return VoteReadOnly;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _VoteCommit: return "VoteCommit";
			case _VoteRollback: return "VoteRollback";
			case _VoteReadOnly: return "VoteReadOnly";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected Vote(int i)
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
