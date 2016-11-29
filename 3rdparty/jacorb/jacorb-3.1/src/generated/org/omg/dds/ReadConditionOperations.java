package org.omg.dds;


/**
 * Generated from IDL interface "ReadCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface ReadConditionOperations
	extends org.omg.dds.ConditionOperations
{
	/* constants */
	/* operations  */
	int get_sample_state_mask();
	int get_view_state_mask();
	int get_instance_state_mask();
	org.omg.dds.DataReader get_datareader();
}
