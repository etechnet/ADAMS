package org.omg.SecurityAdmin;


/**
 * Generated from IDL interface "DelegationPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface DelegationPolicyOperations
	extends org.omg.CORBA.PolicyOperations
{
	/* constants */
	/* operations  */
	void set_delegation_mode(org.omg.CORBA.InterfaceDef object_type, org.omg.Security.DelegationMode mode);
	org.omg.Security.DelegationMode get_delegation_mode(org.omg.CORBA.InterfaceDef object_type);
}
