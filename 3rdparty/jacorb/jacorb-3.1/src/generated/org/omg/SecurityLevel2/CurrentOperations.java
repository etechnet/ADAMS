package org.omg.SecurityLevel2;


/**
 * Generated from IDL interface "Current".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface CurrentOperations
	extends org.omg.SecurityLevel1.CurrentOperations
{
	/* constants */
	/* operations  */
	org.omg.SecurityLevel2.ReceivedCredentials received_credentials();
	void set_credentials(org.omg.Security.CredentialType cred_type, org.omg.SecurityLevel2.Credentials[] creds, org.omg.SecurityLevel2.DelegationMode del);
	org.omg.SecurityLevel2.Credentials[] get_credentials(org.omg.Security.CredentialType cred_type);
	org.omg.CORBA.Policy get_policy(int policy_type);
	void remove_own_credentials(org.omg.SecurityLevel2.Credentials credentials_);
	org.omg.Security.MechandOptions[] supported_mechanisms();
	org.omg.SecurityLevel2.Credentials[] own_credentials();
	org.omg.SecurityLevel2.RequiredRights required_rights_object();
	org.omg.SecurityLevel2.PrincipalAuthenticator principal_authenticator();
	org.omg.SecurityLevel2.AccessDecision access_decision();
	org.omg.SecurityLevel2.AuditDecision audit_decision();
	org.omg.Security.SecurityMechanismData[] get_security_mechanisms(org.omg.CORBA.Object obj_ref);
	org.omg.SecurityLevel2.QOPPolicy create_qop_policy(org.omg.Security.QOP qop_);
	org.omg.SecurityLevel2.MechanismPolicy create_mechanism_policy(java.lang.String[] mechanisms);
	org.omg.SecurityLevel2.InvocationCredentialsPolicy create_invoc_creds_policy(org.omg.SecurityLevel2.Credentials[] creds);
}
