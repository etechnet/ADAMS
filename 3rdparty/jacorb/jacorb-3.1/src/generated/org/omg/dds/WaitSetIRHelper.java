package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class WaitSetIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_conditions", "org.omg.dds.ReturnCode_t(out:attached_conditions org.omg.dds.ConditionSeq)");
		irInfo.put("detach_condition", "org.omg.dds.ReturnCode_t(in:cond )");
		irInfo.put("_wait", "org.omg.dds.ReturnCode_t(out:active_conditions org.omg.dds.ConditionSeq,in:timeout )");
		irInfo.put("attach_condition", "org.omg.dds.ReturnCode_t(in:cond )");
	}
}
