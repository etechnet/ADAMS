package org.omg.SecurityLevel2;


/**
 * Generated from IDL interface "AuditChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public interface AuditChannelOperations
{
	/* constants */
	/* operations  */
	void audit_write(org.omg.Security.AuditEventType event_type, org.omg.SecurityLevel2.Credentials[] creds, org.omg.TimeBase.UtcT time, org.omg.Security.SelectorValue[] descriptors, byte[] event_specific_data);
	int audit_channel_id();
}
