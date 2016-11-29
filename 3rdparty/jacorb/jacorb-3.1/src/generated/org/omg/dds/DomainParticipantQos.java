package org.omg.dds;

/**
 * Generated from IDL struct "DomainParticipantQos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DomainParticipantQos
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DomainParticipantQos(){}
	public org.omg.dds.UserDataQosPolicy user_data;
	public org.omg.dds.EntityFactoryQosPolicy entity_factory;
	public DomainParticipantQos(org.omg.dds.UserDataQosPolicy user_data, org.omg.dds.EntityFactoryQosPolicy entity_factory)
	{
		this.user_data = user_data;
		this.entity_factory = entity_factory;
	}
}
