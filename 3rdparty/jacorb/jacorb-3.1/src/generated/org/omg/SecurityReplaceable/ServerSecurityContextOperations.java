package org.omg.SecurityReplaceable;


/**
 * Generated from IDL interface "ServerSecurityContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface ServerSecurityContextOperations
	extends org.omg.SecurityReplaceable.SecurityContextOperations
{
	/* constants */
	/* operations  */
	short association_options_used();
	org.omg.Security.DelegationMode delegation_mode();
	org.omg.SecurityLevel2.Credentials server_credentials();
	short server_options_supported();
	byte[] server_security_name();
}
