package org.omg.CORBA;

/**
 * Generated from IDL exception "TRANSACTION_REQUIRED".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class TRANSACTION_REQUIRED
	extends org.omg.CORBA.SystemException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TRANSACTION_REQUIRED()
	{
		super( "", 0 ,org.omg.CORBA.CompletionStatus.COMPLETED_NO ) ;
	}

	public TRANSACTION_REQUIRED( String reason )
	{
		super( reason, 0 ,org.omg.CORBA.CompletionStatus.COMPLETED_NO ) ;
	}

	public TRANSACTION_REQUIRED(int minor, org.omg.CORBA.CompletionStatus completed )
	{
		super( "", minor, completed ) ;
	}

	public TRANSACTION_REQUIRED(String reason, int minor, org.omg.CORBA.CompletionStatus completed )
	{
		super( reason, minor, completed ) ;
	}

}
