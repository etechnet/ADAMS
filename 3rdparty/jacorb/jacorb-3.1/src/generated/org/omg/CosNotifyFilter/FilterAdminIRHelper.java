package org.omg.CosNotifyFilter;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class FilterAdminIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_filter", "(in:filter org.omg.CosNotifyFilter.FilterID)");
		irInfo.put("add_filter", "org.omg.CosNotifyFilter.FilterID(in:new_filter )");
		irInfo.put("remove_all_filters", "()");
		irInfo.put("get_all_filters", "org.omg.CosNotifyFilter.FilterIDSeq()");
		irInfo.put("remove_filter", "(in:filter org.omg.CosNotifyFilter.FilterID)");
	}
}
