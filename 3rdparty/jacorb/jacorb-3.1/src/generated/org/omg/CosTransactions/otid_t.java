package org.omg.CosTransactions;

/**
 * Generated from IDL struct "otid_t".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class otid_t
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public otid_t(){}
	public int formatID;
	public int bqual_length;
	public byte[] tid;
	public otid_t(int formatID, int bqual_length, byte[] tid)
	{
		this.formatID = formatID;
		this.bqual_length = bqual_length;
		this.tid = tid;
	}
}
