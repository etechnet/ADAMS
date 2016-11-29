package org.omg.CosNotifyFilter;


/**
 * Generated from IDL interface "MappingFilter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface MappingFilterOperations
{
	/* constants */
	/* operations  */
	java.lang.String constraint_grammar();
	org.omg.CORBA.TypeCode value_type();
	org.omg.CORBA.Any default_value();
	org.omg.CosNotifyFilter.MappingConstraintInfo[] add_mapping_constraints(org.omg.CosNotifyFilter.MappingConstraintPair[] pair_list) throws org.omg.CosNotifyFilter.InvalidValue,org.omg.CosNotifyFilter.InvalidConstraint;
	void modify_mapping_constraints(int[] del_list, org.omg.CosNotifyFilter.MappingConstraintInfo[] modify_list) throws org.omg.CosNotifyFilter.ConstraintNotFound,org.omg.CosNotifyFilter.InvalidValue,org.omg.CosNotifyFilter.InvalidConstraint;
	org.omg.CosNotifyFilter.MappingConstraintInfo[] get_mapping_constraints(int[] id_list) throws org.omg.CosNotifyFilter.ConstraintNotFound;
	org.omg.CosNotifyFilter.MappingConstraintInfo[] get_all_mapping_constraints();
	void remove_all_mapping_constraints();
	void destroy();
	boolean match(org.omg.CORBA.Any filterable_data, org.omg.CORBA.AnyHolder result_to_set) throws org.omg.CosNotifyFilter.UnsupportedFilterableData;
	boolean match_structured(org.omg.CosNotification.StructuredEvent filterable_data, org.omg.CORBA.AnyHolder result_to_set) throws org.omg.CosNotifyFilter.UnsupportedFilterableData;
	boolean match_typed(org.omg.CosNotification.Property[] filterable_data, org.omg.CORBA.AnyHolder result_to_set) throws org.omg.CosNotifyFilter.UnsupportedFilterableData;
}
