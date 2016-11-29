package org.omg.BridgeTransactionMgmt;

/**
 * Generated from IDL exception "TransactionAlreadyActive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransactionAlreadyActive
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TransactionAlreadyActive()
	{
		super(org.omg.BridgeTransactionMgmt.TransactionAlreadyActiveHelper.id());
	}

	public TransactionAlreadyActive(String value)
	{
		super(value);
	}
}
