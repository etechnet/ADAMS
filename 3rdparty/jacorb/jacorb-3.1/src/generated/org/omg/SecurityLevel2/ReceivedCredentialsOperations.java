package org.omg.SecurityLevel2;


/**
 * Generated from IDL interface "ReceivedCredentials".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface ReceivedCredentialsOperations
	extends org.omg.SecurityLevel2.CredentialsOperations
{
	/* constants */
	/* operations  */
	org.omg.SecurityLevel2.Credentials accepting_credentials();
	short association_options_used();
	org.omg.Security.DelegationState delegation_state();
	org.omg.Security.DelegationMode delegation_mode();
}
