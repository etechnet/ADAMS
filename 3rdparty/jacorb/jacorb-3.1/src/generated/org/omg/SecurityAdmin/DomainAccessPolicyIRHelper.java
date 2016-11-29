package org.omg.SecurityAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class DomainAccessPolicyIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("replace_rights", "(in:priv_attr ,in:del_state ,in:rights org.omg.Security.RightsList)");
		irInfo.put("get_all_rights", "org.omg.Security.RightsList(in:priv_attr ,in:del_state )");
		irInfo.put("get_rights", "org.omg.Security.RightsList(in:priv_attr ,in:del_state ,in:rights_family )");
		irInfo.put("revoke_rights", "(in:priv_attr ,in:del_state ,in:rights org.omg.Security.RightsList)");
		irInfo.put("grant_rights", "(in:priv_attr ,in:del_state ,in:rights org.omg.Security.RightsList)");
	}
}
