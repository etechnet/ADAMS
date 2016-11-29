package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL struct "ExternalEndpointError".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointError
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ExternalEndpointError(){}
	public org.omg.CosBridgeAdmin.ExternalEndpointRole role;
	public org.omg.CosBridgeAdmin.ExternalEndpointErrorCode code;
	public ExternalEndpointError(org.omg.CosBridgeAdmin.ExternalEndpointRole role, org.omg.CosBridgeAdmin.ExternalEndpointErrorCode code)
	{
		this.role = role;
		this.code = code;
	}
}
