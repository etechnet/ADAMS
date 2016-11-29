package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "IllegalTraderName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalTraderName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalTraderName()
	{
		super(org.omg.CosTrading.RegisterPackage.IllegalTraderNameHelper.id());
	}

	public java.lang.String[] name;
	public IllegalTraderName(java.lang.String _reason,java.lang.String[] name)
	{
		super(_reason);
		this.name = name;
	}
	public IllegalTraderName(java.lang.String[] name)
	{
		super(org.omg.CosTrading.RegisterPackage.IllegalTraderNameHelper.id());
		this.name = name;
	}
}
