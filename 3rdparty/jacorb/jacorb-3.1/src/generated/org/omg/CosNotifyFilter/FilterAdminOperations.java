package org.omg.CosNotifyFilter;


/**
 * Generated from IDL interface "FilterAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface FilterAdminOperations
{
	/* constants */
	/* operations  */
	int add_filter(org.omg.CosNotifyFilter.Filter new_filter);
	void remove_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound;
	org.omg.CosNotifyFilter.Filter get_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound;
	int[] get_all_filters();
	void remove_all_filters();
}
