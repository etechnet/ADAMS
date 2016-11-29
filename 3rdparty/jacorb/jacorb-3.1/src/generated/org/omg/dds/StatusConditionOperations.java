package org.omg.dds;


/**
 * Generated from IDL interface "StatusCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface StatusConditionOperations
	extends org.omg.dds.ConditionOperations
{
	/* constants */
	/* operations  */
	int get_enabled_statuses();
	int set_enabled_statuses(int mask);
	org.omg.dds.Entity get_entity();
}
