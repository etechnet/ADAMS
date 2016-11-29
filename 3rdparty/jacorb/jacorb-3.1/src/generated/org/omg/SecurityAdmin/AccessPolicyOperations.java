package org.omg.SecurityAdmin;


/**
 * Generated from IDL interface "AccessPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface AccessPolicyOperations
	extends org.omg.CORBA.PolicyOperations
{
	/* constants */
	/* operations  */
	org.omg.Security.Right[] get_effective_rights(org.omg.Security.SecAttribute[] attrib_list, org.omg.Security.ExtensibleFamily rights_family);
	org.omg.Security.Right[] get_all_effective_rights(org.omg.Security.SecAttribute[] attrib_list);
}
