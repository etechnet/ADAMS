package org.omg.MIOP;

/**
 * Generated from IDL struct "UIPMC_ProfileBody".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class UIPMC_ProfileBody
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public UIPMC_ProfileBody(){}
	public org.omg.GIOP.Version miop_version;
	public java.lang.String the_address;
	public short the_port;
	public org.omg.IOP.TaggedComponent[] components;
	public UIPMC_ProfileBody(org.omg.GIOP.Version miop_version, java.lang.String the_address, short the_port, org.omg.IOP.TaggedComponent[] components)
	{
		this.miop_version = miop_version;
		this.the_address = the_address;
		this.the_port = the_port;
		this.components = components;
	}
}
