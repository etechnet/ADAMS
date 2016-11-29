package org.omg.SecurityReplaceable;


/**
 * Generated from IDL interface "Vault".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface VaultOperations
{
	/* constants */
	/* operations  */
	int[] get_supported_authen_methods(java.lang.String mechanism);
	org.omg.Security.AuthenticationStatus acquire_credentials(int method, java.lang.String mechanism, java.lang.String security_name, byte[] auth_data, org.omg.Security.SecAttribute[] privileges, org.omg.SecurityLevel2.CredentialsHolder creds, org.omg.Security.OpaqueHolder continuation_data, org.omg.Security.OpaqueHolder auth_specific_data);
	org.omg.Security.AuthenticationStatus continue_credentials_acquisition(byte[] response_data, org.omg.SecurityLevel2.Credentials creds, org.omg.Security.OpaqueHolder continuation_data, org.omg.Security.OpaqueHolder auth_specific_data);
	org.omg.Security.AssociationStatus init_security_context(org.omg.SecurityLevel2.Credentials creds, java.lang.String target_security_name, org.omg.CORBA.Object target, org.omg.Security.DelegationMode delegation_mode, org.omg.Security.OptionsDirectionPair[] association_options, java.lang.String mechanism, byte[] mech_data, byte[] chan_binding, org.omg.Security.OpaqueBufferHolder security_token, org.omg.SecurityReplaceable.ClientSecurityContextHolder security_context);
	org.omg.Security.AssociationStatus accept_security_context(org.omg.SecurityLevel2.Credentials[] creds_list, byte[] chan_bindings, org.omg.Security.OpaqueBuffer in_token, org.omg.Security.OpaqueBufferHolder out_token, org.omg.SecurityReplaceable.ServerSecurityContextHolder security_context);
	org.omg.Security.MechandOptions[] get_supported_mechs();
}
