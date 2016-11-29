package org.omg.CosNotifyFilter;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class MappingFilterIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("remove_all_mapping_constraints", "()");
		irInfo.put("get_all_mapping_constraints", "org.omg.CosNotifyFilter.MappingConstraintInfoSeq()");
		irInfo.put("match_structured", "(in:filterable_data ,out:result_to_set )");
		irInfo.put("match_typed", "(in:filterable_data org.omg.CosNotification.PropertySeq,out:result_to_set )");
		irInfo.put("match", "(in:filterable_data ,out:result_to_set )");
		irInfo.put("destroy", "()");
		irInfo.put("value_type", "attribute;org.omg.CORBA.TypeCode");
		irInfo.put("default_value", "attribute;org.omg.CORBA.Any");
		irInfo.put("add_mapping_constraints", "org.omg.CosNotifyFilter.MappingConstraintInfoSeq(in:pair_list org.omg.CosNotifyFilter.MappingConstraintPairSeq)");
		irInfo.put("modify_mapping_constraints", "(in:del_list org.omg.CosNotifyFilter.ConstraintIDSeq,in:modify_list org.omg.CosNotifyFilter.MappingConstraintInfoSeq)");
		irInfo.put("constraint_grammar", "attribute;java.lang.String");
		irInfo.put("get_mapping_constraints", "org.omg.CosNotifyFilter.MappingConstraintInfoSeq(in:id_list org.omg.CosNotifyFilter.ConstraintIDSeq)");
	}
}
