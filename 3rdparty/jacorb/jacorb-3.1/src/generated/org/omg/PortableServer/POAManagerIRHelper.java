package org.omg.PortableServer;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class POAManagerIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_state", "()");
		irInfo.put("discard_requests", "(in:wait_for_completion )");
		irInfo.put("hold_requests", "(in:wait_for_completion )");
		irInfo.put("activate", "()");
		irInfo.put("get_id", "()");
		irInfo.put("deactivate", "(in:etherealize_objects ,in:wait_for_completion )");
	}
}
