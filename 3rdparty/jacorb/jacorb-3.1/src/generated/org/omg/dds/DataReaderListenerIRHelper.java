package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class DataReaderListenerIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("on_requested_incompatible_qos", "(in:reader ,in:status )");
		irInfo.put("on_data_available", "(in:reader )");
		irInfo.put("on_requested_deadline_missed", "(in:reader ,in:status )");
		irInfo.put("on_sample_lost", "(in:reader ,in:status )");
		irInfo.put("on_subscription_match", "(in:reader ,in:status )");
		irInfo.put("on_sample_rejected", "(in:reader ,in:status )");
		irInfo.put("on_liveliness_changed", "(in:reader ,in:status )");
	}
}
