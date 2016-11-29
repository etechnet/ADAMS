package org.omg.PortableGroup;


/**
 * Generated from IDL interface "ObjectGroupManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface ObjectGroupManagerOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.Object create_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location, java.lang.String type_id, org.omg.PortableGroup.Property[] the_criteria) throws org.omg.PortableGroup.ObjectNotCreated,org.omg.PortableGroup.MemberAlreadyPresent,org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.InvalidCriteria,org.omg.PortableGroup.CannotMeetCriteria,org.omg.PortableGroup.NoFactory;
	org.omg.CORBA.Object add_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location, org.omg.CORBA.Object member) throws org.omg.CORBA.INV_OBJREF,org.omg.PortableGroup.MemberAlreadyPresent,org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.ObjectNotAdded;
	org.omg.CORBA.Object remove_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location) throws org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.MemberNotFound;
	org.omg.CosNaming.NameComponent[][] locations_of_members(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound;
	long get_object_group_id(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound;
	org.omg.CORBA.Object get_object_group_ref(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound;
	org.omg.CORBA.Object get_member_ref(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] loc) throws org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.MemberNotFound;
}
