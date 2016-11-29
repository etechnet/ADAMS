package org.omg.SecurityReplaceable;


/**
 * Generated from IDL interface "SecurityContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface SecurityContextOperations
{
	/* constants */
	/* operations  */
	org.omg.Security.SecurityContextType context_type();
	org.omg.Security.SecurityContextState context_state();
	java.lang.String mechanism();
	boolean supports_refresh();
	byte[] chan_binding();
	org.omg.SecurityLevel2.ReceivedCredentials received_credentials();
	org.omg.Security.AssociationStatus continue_security_context(org.omg.Security.OpaqueBuffer in_token, org.omg.Security.OpaqueBufferHolder out_token);
	void protect_message(org.omg.Security.OpaqueBuffer message, org.omg.Security.QOP qop_, org.omg.Security.OpaqueBufferHolder text_buffer, org.omg.Security.OpaqueBufferHolder token);
	boolean reclaim_message(org.omg.Security.OpaqueBuffer text_buffer, org.omg.Security.OpaqueBuffer token, org.omg.Security.QOPHolder qop_, org.omg.Security.OpaqueBufferHolder message);
	boolean is_valid(org.omg.TimeBase.UtcTHolder expiry_time);
	boolean refresh_security_context(byte[] refresh_data, org.omg.Security.OpaqueBufferHolder out_token);
	boolean process_refresh_token(org.omg.Security.OpaqueBuffer refresh_token);
	boolean discard_security_context(byte[] discard_data, org.omg.Security.OpaqueBufferHolder out_token);
	boolean process_discard_token(org.omg.Security.OpaqueBuffer discard_token);
}
