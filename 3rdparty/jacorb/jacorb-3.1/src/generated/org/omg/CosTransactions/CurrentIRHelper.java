package org.omg.CosTransactions;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class CurrentIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_control", "()");
		irInfo.put("get_status", "()");
		irInfo.put("rollback_only", "()");
		irInfo.put("begin", "()");
		irInfo.put("set_timeout", "(in:seconds )");
		irInfo.put("resume", "(in:which )");
		irInfo.put("get_transaction_name", "()");
		irInfo.put("commit", "(in:report_heuristics )");
		irInfo.put("suspend", "()");
		irInfo.put("rollback", "()");
	}
}
