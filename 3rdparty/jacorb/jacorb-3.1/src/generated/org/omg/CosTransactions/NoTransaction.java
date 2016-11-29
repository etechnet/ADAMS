package org.omg.CosTransactions;

/**
 * Generated from IDL exception "NoTransaction".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class NoTransaction
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NoTransaction()
	{
		super(org.omg.CosTransactions.NoTransactionHelper.id());
	}

	public NoTransaction(String value)
	{
		super(value);
	}
}
