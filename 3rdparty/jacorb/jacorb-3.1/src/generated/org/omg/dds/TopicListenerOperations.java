package org.omg.dds;


/**
 * Generated from IDL interface "TopicListener".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface TopicListenerOperations
	extends org.omg.dds.ListenerOperations
{
	/* constants */
	/* operations  */
	void on_inconsistent_topic(org.omg.dds.Topic the_topic, org.omg.dds.InconsistentTopicStatus status);
}
