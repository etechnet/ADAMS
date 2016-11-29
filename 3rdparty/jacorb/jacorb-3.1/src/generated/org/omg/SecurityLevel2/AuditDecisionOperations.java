package org.omg.SecurityLevel2;


/**
 * Generated from IDL interface "AuditDecision".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface AuditDecisionOperations
{
	/* constants */
	/* operations  */
	boolean audit_needed(org.omg.Security.AuditEventType event_type, org.omg.Security.SelectorValue[] value_list);
	org.omg.SecurityLevel2.AuditChannel audit_channel();
}
