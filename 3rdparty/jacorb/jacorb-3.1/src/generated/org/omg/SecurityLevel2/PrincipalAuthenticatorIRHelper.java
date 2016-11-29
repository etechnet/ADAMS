package org.omg.SecurityLevel2;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class PrincipalAuthenticatorIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("authenticate", "(in:method org.omg.Security.AuthenticationMethod,in:mechanism org.omg.Security.MechanismType,in:security_name org.omg.Security.SecurityName,in:auth_data org.omg.Security.Opaque,in:privileges org.omg.Security.AttributeList,out:creds ,out:continuation_data org.omg.Security.Opaque,out:auth_specific_data org.omg.Security.Opaque)");
		irInfo.put("get_supported_authen_methods", "org.omg.Security.AuthenticationMethodList(in:mechanism org.omg.Security.MechanismType)");
		irInfo.put("continue_authentication", "(in:response_data org.omg.Security.Opaque,in:creds ,out:continuation_data org.omg.Security.Opaque,out:auth_specific_data org.omg.Security.Opaque)");
	}
}
