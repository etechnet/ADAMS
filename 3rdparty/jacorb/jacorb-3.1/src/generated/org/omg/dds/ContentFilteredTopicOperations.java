package org.omg.dds;


/**
 * Generated from IDL interface "ContentFilteredTopic".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface ContentFilteredTopicOperations
	extends org.omg.dds.TopicDescriptionOperations
{
	/* constants */
	/* operations  */
	java.lang.String get_filter_expression();
	java.lang.String[] get_expression_parameters();
	int set_expression_parameters(java.lang.String[] expression_parameters);
	org.omg.dds.Topic get_related_topic();
}
