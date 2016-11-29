package org.omg.SecurityLevel2;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class AuditChannelIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("audit_write", "(in:event_type ,in:creds org.omg.SecurityLevel2.CredentialsList,in:time org.omg.Security.UtcT,in:descriptors org.omg.Security.SelectorValueList,in:event_specific_data org.omg.Security.Opaque)");
		irInfo.put("audit_channel_id", "attribute;org.omg.Security.AuditChannelId");
	}
}
