package org.omg.PortableGroup;

/**
 * Generated from IDL struct "TagGroupTaggedComponent".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class TagGroupTaggedComponent
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TagGroupTaggedComponent(){}
	public org.omg.GIOP.Version group_version;
	public java.lang.String group_domain_id;
	public long object_group_id;
	public int object_group_ref_version;
	public TagGroupTaggedComponent(org.omg.GIOP.Version group_version, java.lang.String group_domain_id, long object_group_id, int object_group_ref_version)
	{
		this.group_version = group_version;
		this.group_domain_id = group_domain_id;
		this.object_group_id = object_group_id;
		this.object_group_ref_version = object_group_ref_version;
	}
}
