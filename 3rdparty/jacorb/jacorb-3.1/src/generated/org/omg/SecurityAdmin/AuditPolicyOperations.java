package org.omg.SecurityAdmin;


/**
 * Generated from IDL interface "AuditPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface AuditPolicyOperations
	extends org.omg.CORBA.PolicyOperations
{
	/* constants */
	/* operations  */
	void set_audit_selectors(java.lang.String object_type, org.omg.Security.AuditEventType[] events, org.omg.Security.SelectorValue[] selectors, org.omg.Security.AuditCombinator audit_combinator);
	void clear_audit_selectors(java.lang.String object_type, org.omg.Security.AuditEventType[] events);
	void replace_audit_selectors(java.lang.String object_type, org.omg.Security.AuditEventType[] events, org.omg.Security.SelectorValue[] selectors, org.omg.Security.AuditCombinator audit_combinator);
	void get_audit_selectors(java.lang.String object_type, org.omg.Security.AuditEventType event_type, org.omg.Security.SelectorValueListHolder selectors, org.omg.Security.AuditCombinatorHolder audit_combinator);
	void set_audit_channel(int audit_channel_id);
}
