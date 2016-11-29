package org.omg.dds;

/**
 * Generated from IDL struct "EntityFactoryQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class EntityFactoryQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public EntityFactoryQosPolicy(){}
	public boolean autoenable_created_entities;
	public EntityFactoryQosPolicy(boolean autoenable_created_entities)
	{
		this.autoenable_created_entities = autoenable_created_entities;
	}
}
