package org.omg.ATLAS;

/**
 * Generated from IDL struct "AuthTokenData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthTokenData
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public AuthTokenData(){}
	public org.omg.CSI.IdentityToken[] ident_token;
	public org.omg.CSI.AuthorizationElement[] auth_token;
	public org.omg.TimeBase.UtcT[] expiry_time;
	public AuthTokenData(org.omg.CSI.IdentityToken[] ident_token, org.omg.CSI.AuthorizationElement[] auth_token, org.omg.TimeBase.UtcT[] expiry_time)
	{
		this.ident_token = ident_token;
		this.auth_token = auth_token;
		this.expiry_time = expiry_time;
	}
}
