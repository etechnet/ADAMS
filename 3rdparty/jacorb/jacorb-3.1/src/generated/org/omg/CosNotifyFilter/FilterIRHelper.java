package org.omg.CosNotifyFilter;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class FilterIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("remove_all_constraints", "()");
		irInfo.put("get_callbacks", "org.omg.CosNotifyFilter.CallbackIDSeq()");
		irInfo.put("add_constraints", "org.omg.CosNotifyFilter.ConstraintInfoSeq(in:constraint_list org.omg.CosNotifyFilter.ConstraintExpSeq)");
		irInfo.put("get_constraints", "org.omg.CosNotifyFilter.ConstraintInfoSeq(in:id_list org.omg.CosNotifyFilter.ConstraintIDSeq)");
		irInfo.put("match_structured", "(in:filterable_data )");
		irInfo.put("match_typed", "(in:filterable_data org.omg.CosNotification.PropertySeq)");
		irInfo.put("attach_callback", "org.omg.CosNotifyFilter.CallbackID(in:callback )");
		irInfo.put("get_all_constraints", "org.omg.CosNotifyFilter.ConstraintInfoSeq()");
		irInfo.put("match", "(in:filterable_data )");
		irInfo.put("modify_constraints", "(in:del_list org.omg.CosNotifyFilter.ConstraintIDSeq,in:modify_list org.omg.CosNotifyFilter.ConstraintInfoSeq)");
		irInfo.put("detach_callback", "(in:callback org.omg.CosNotifyFilter.CallbackID)");
		irInfo.put("destroy", "()");
		irInfo.put("constraint_grammar", "attribute;java.lang.String");
	}
}
