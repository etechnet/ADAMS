package org.omg.CosNotifyComm;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class SequencePullSupplierIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("pull_structured_events", "org.omg.CosNotification.EventBatch(in:max_number )");
		irInfo.put("disconnect_sequence_pull_supplier", "()");
		irInfo.put("try_pull_structured_events", "org.omg.CosNotification.EventBatch(in:max_number ,out:has_event )");
	}
}
