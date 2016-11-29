package org.omg.CosBridgeAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class BridgeFactoryIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_bridge_with_id", "(in:id org.omg.CosBridgeAdmin.BridgeID)");
		irInfo.put("get_all_bridges", "org.omg.CosBridgeAdmin.BridgeIDSeq()");
		irInfo.put("create_bridge", "(in:source ,in:sink ,out:id org.omg.CosBridgeAdmin.BridgeID)");
	}
}
