package org.omg.dds;


/**
 * Generated from IDL interface "QueryCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface QueryConditionOperations
	extends org.omg.dds.ReadConditionOperations
{
	/* constants */
	/* operations  */
	java.lang.String get_query_expression();
	java.lang.String[] get_query_arguments();
	int set_query_arguments(java.lang.String[] query_arguments);
}
