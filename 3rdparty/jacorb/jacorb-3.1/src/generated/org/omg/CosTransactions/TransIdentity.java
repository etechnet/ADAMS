package org.omg.CosTransactions;

/**
 * Generated from IDL struct "TransIdentity".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TransIdentity
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TransIdentity(){}
	public org.omg.CosTransactions.Coordinator coord;
	public org.omg.CosTransactions.Terminator term;
	public org.omg.CosTransactions.otid_t otid;
	public TransIdentity(org.omg.CosTransactions.Coordinator coord, org.omg.CosTransactions.Terminator term, org.omg.CosTransactions.otid_t otid)
	{
		this.coord = coord;
		this.term = term;
		this.otid = otid;
	}
}
