package org.omg.dds;


/**
 * Generated from IDL interface "DomainParticipantFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface DomainParticipantFactoryOperations
{
	/* constants */
	/* operations  */
	org.omg.dds.DomainParticipant create_participant(int domainId, org.omg.dds.DomainParticipantQos qos, org.omg.dds.DomainParticipantListener a_listener);
	int delete_participant(org.omg.dds.DomainParticipant a_participant);
	org.omg.dds.DomainParticipant lookup_participant(int domainId);
	int set_default_participant_qos(org.omg.dds.DomainParticipantQos qos);
	void get_default_participant_qos(org.omg.dds.DomainParticipantQosHolder qos);
}
