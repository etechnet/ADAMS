package org.omg.SecurityAdmin;


/**
 * Generated from IDL interface "SecureInvocationPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface SecureInvocationPolicyOperations
	extends org.omg.CORBA.PolicyOperations
{
	/* constants */
	/* operations  */
	void set_association_options(org.omg.CORBA.InterfaceDef object_type, org.omg.Security.RequiresSupports requires_supports, org.omg.Security.CommunicationDirection direction, short options);
	short get_association_options(org.omg.CORBA.InterfaceDef object_type, org.omg.Security.RequiresSupports requires_supports, org.omg.Security.CommunicationDirection direction);
}
