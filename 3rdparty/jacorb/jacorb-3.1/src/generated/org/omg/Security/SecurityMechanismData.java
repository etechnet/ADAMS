package org.omg.Security;

/**
 * Generated from IDL struct "SecurityMechanismData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SecurityMechanismData
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public SecurityMechanismData(){}
	public java.lang.String mechanism;
	public java.lang.String security_name;
	public short options_supported;
	public short options_required;
	public SecurityMechanismData(java.lang.String mechanism, java.lang.String security_name, short options_supported, short options_required)
	{
		this.mechanism = mechanism;
		this.security_name = security_name;
		this.options_supported = options_supported;
		this.options_required = options_required;
	}
}
