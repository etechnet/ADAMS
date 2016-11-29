package org.omg.SecurityLevel2;


/**
 * Generated from IDL interface "PrincipalAuthenticator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface PrincipalAuthenticatorOperations
{
	/* constants */
	/* operations  */
	int[] get_supported_authen_methods(java.lang.String mechanism);
	org.omg.Security.AuthenticationStatus authenticate(int method, java.lang.String mechanism, java.lang.String security_name, byte[] auth_data, org.omg.Security.SecAttribute[] privileges, org.omg.SecurityLevel2.CredentialsHolder creds, org.omg.Security.OpaqueHolder continuation_data, org.omg.Security.OpaqueHolder auth_specific_data);
	org.omg.Security.AuthenticationStatus continue_authentication(byte[] response_data, org.omg.SecurityLevel2.Credentials creds, org.omg.Security.OpaqueHolder continuation_data, org.omg.Security.OpaqueHolder auth_specific_data);
}
