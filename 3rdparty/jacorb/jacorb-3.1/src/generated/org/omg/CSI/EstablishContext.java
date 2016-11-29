package org.omg.CSI;

/**
 * Generated from IDL struct "EstablishContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class EstablishContext
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public EstablishContext(){}
	public long client_context_id;
	public org.omg.CSI.AuthorizationElement[] authorization_token;
	public org.omg.CSI.IdentityToken identity_token;
	public byte[] client_authentication_token;
	public EstablishContext(long client_context_id, org.omg.CSI.AuthorizationElement[] authorization_token, org.omg.CSI.IdentityToken identity_token, byte[] client_authentication_token)
	{
		this.client_context_id = client_context_id;
		this.authorization_token = authorization_token;
		this.identity_token = identity_token;
		this.client_authentication_token = client_authentication_token;
	}
}
