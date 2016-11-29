package org.omg.Messaging;

/**
 * Generated from IDL struct "PolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class PolicyValue
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PolicyValue(){}
	public int ptype;
	public byte[] pvalue;
	public PolicyValue(int ptype, byte[] pvalue)
	{
		this.ptype = ptype;
		this.pvalue = pvalue;
	}
}
