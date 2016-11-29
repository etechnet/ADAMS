package org.omg.SecurityReplaceable;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class VaultIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_supported_authen_methods", "org.omg.Security.AuthenticationMethodList(in:mechanism org.omg.Security.MechanismType)");
		irInfo.put("get_supported_mechs", "org.omg.Security.MechandOptionsList()");
		irInfo.put("accept_security_context", "(in:creds_list org.omg.SecurityLevel2.CredentialsList,in:chan_bindings org.omg.Security.Opaque,in:in_token ,out:out_token ,out:security_context )");
		irInfo.put("continue_credentials_acquisition", "(in:response_data org.omg.Security.Opaque,in:creds ,out:continuation_data org.omg.Security.Opaque,out:auth_specific_data org.omg.Security.Opaque)");
		irInfo.put("acquire_credentials", "(in:method org.omg.Security.AuthenticationMethod,in:mechanism org.omg.Security.MechanismType,in:security_name org.omg.Security.SecurityName,in:auth_data org.omg.Security.Opaque,in:privileges org.omg.Security.AttributeList,out:creds ,out:continuation_data org.omg.Security.Opaque,out:auth_specific_data org.omg.Security.Opaque)");
		irInfo.put("init_security_context", "(in:creds ,in:target_security_name org.omg.Security.SecurityName,in:target ,in:delegation_mode ,in:association_options org.omg.Security.OptionsDirectionPairList,in:mechanism org.omg.Security.MechanismType,in:mech_data org.omg.Security.Opaque,in:chan_binding org.omg.Security.Opaque,out:security_token ,out:security_context )");
	}
}
