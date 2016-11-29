package org.omg.CosTrading;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class LinkIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("describe_link", "(in:name org.omg.CosTrading.LinkName)");
		irInfo.put("remove_link", "(in:name org.omg.CosTrading.LinkName)");
		irInfo.put("list_links", "org.omg.CosTrading.LinkNameSeq()");
		irInfo.put("modify_link", "(in:name org.omg.CosTrading.LinkName,in:def_pass_on_follow_rule ,in:limiting_follow_rule )");
		irInfo.put("add_link", "(in:name org.omg.CosTrading.LinkName,in:target ,in:def_pass_on_follow_rule ,in:limiting_follow_rule )");
	}
}
