package org.omg.CSIIOP;

/**
 * Generated from IDL struct "SAS_ContextSec".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SAS_ContextSec
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public SAS_ContextSec(){}
	public short target_supports;
	public short target_requires;
	public org.omg.CSIIOP.ServiceConfiguration[] privilege_authorities;
	public byte[][] supported_naming_mechanisms;
	public int supported_identity_types;
	public SAS_ContextSec(short target_supports, short target_requires, org.omg.CSIIOP.ServiceConfiguration[] privilege_authorities, byte[][] supported_naming_mechanisms, int supported_identity_types)
	{
		this.target_supports = target_supports;
		this.target_requires = target_requires;
		this.privilege_authorities = privilege_authorities;
		this.supported_naming_mechanisms = supported_naming_mechanisms;
		this.supported_identity_types = supported_identity_types;
	}
}
