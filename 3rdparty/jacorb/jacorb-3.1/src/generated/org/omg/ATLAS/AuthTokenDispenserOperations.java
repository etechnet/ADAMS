package org.omg.ATLAS;


/**
 * Generated from IDL interface "AuthTokenDispenser".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public interface AuthTokenDispenserOperations
{
	/* constants */
	/* operations  */
	org.omg.ATLAS.AuthTokenData get_my_authorization_token() throws org.omg.ATLAS.IllegalTokenRequest;
	org.omg.ATLAS.AuthTokenData translate_authorization_token(org.omg.CSI.IdentityToken the_subject, org.omg.CSI.AuthorizationElement[] the_token) throws org.omg.ATLAS.IllegalTokenRequest,org.omg.ATLAS.TokenOkay;
}
