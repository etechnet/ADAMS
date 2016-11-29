package org.omg.SecurityReplaceable;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class SecurityContextIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("continue_security_context", "(in:in_token ,out:out_token )");
		irInfo.put("protect_message", "(in:message ,in:qop_ ,out:text_buffer ,out:token )");
		irInfo.put("process_refresh_token", "(in:refresh_token )");
		irInfo.put("context_state", "attribute;org.omg.Security.SecurityContextState");
		irInfo.put("refresh_security_context", "(in:refresh_data org.omg.Security.Opaque,out:out_token )");
		irInfo.put("supports_refresh", "attribute;boolean");
		irInfo.put("process_discard_token", "(in:discard_token )");
		irInfo.put("received_credentials", "attribute;org.omg.SecurityLevel2.ReceivedCredentials");
		irInfo.put("mechanism", "attribute;org.omg.Security.MechanismType");
		irInfo.put("chan_binding", "attribute;org.omg.Security.Opaque");
		irInfo.put("discard_security_context", "(in:discard_data org.omg.Security.Opaque,out:out_token )");
		irInfo.put("is_valid", "(out:expiry_time org.omg.Security.UtcT)");
		irInfo.put("reclaim_message", "(in:text_buffer ,in:token ,out:qop_ ,out:message )");
		irInfo.put("context_type", "attribute;org.omg.Security.SecurityContextType");
	}
}
