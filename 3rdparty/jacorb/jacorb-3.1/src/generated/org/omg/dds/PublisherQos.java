package org.omg.dds;

/**
 * Generated from IDL struct "PublisherQos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PublisherQos
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PublisherQos(){}
	public org.omg.dds.PresentationQosPolicy presentation;
	public org.omg.dds.PartitionQosPolicy partition;
	public org.omg.dds.GroupDataQosPolicy group_data;
	public org.omg.dds.EntityFactoryQosPolicy entity_factory;
	public PublisherQos(org.omg.dds.PresentationQosPolicy presentation, org.omg.dds.PartitionQosPolicy partition, org.omg.dds.GroupDataQosPolicy group_data, org.omg.dds.EntityFactoryQosPolicy entity_factory)
	{
		this.presentation = presentation;
		this.partition = partition;
		this.group_data = group_data;
		this.entity_factory = entity_factory;
	}
}
