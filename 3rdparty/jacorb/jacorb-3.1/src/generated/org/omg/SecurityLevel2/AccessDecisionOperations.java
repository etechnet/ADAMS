package org.omg.SecurityLevel2;


/**
 * Generated from IDL interface "AccessDecision".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface AccessDecisionOperations
{
	/* constants */
	/* operations  */
	boolean access_allowed(org.omg.SecurityLevel2.Credentials[] cred_list, org.omg.CORBA.Object target, java.lang.String operation_name, java.lang.String target_interface_name);
}
