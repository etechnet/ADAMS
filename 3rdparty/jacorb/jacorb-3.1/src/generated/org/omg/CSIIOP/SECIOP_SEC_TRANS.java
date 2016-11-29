package org.omg.CSIIOP;

/**
 * Generated from IDL struct "SECIOP_SEC_TRANS".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SECIOP_SEC_TRANS
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public SECIOP_SEC_TRANS(){}
	public short target_supports;
	public short target_requires;
	public byte[] mech_oid;
	public byte[] target_name;
	public org.omg.CSIIOP.TransportAddress[] addresses;
	public SECIOP_SEC_TRANS(short target_supports, short target_requires, byte[] mech_oid, byte[] target_name, org.omg.CSIIOP.TransportAddress[] addresses)
	{
		this.target_supports = target_supports;
		this.target_requires = target_requires;
		this.mech_oid = mech_oid;
		this.target_name = target_name;
		this.addresses = addresses;
	}
}
