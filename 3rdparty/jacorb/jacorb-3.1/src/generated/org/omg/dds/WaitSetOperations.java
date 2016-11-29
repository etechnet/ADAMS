package org.omg.dds;


/**
 * Generated from IDL interface "WaitSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface WaitSetOperations
{
	/* constants */
	/* operations  */
	int _wait(org.omg.dds.ConditionSeqHolder active_conditions, org.omg.dds.Duration_t timeout);
	int attach_condition(org.omg.dds.Condition cond);
	int detach_condition(org.omg.dds.Condition cond);
	int get_conditions(org.omg.dds.ConditionSeqHolder attached_conditions);
}
