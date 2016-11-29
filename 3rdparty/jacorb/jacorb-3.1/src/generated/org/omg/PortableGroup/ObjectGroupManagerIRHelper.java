package org.omg.PortableGroup;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ObjectGroupManagerIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_object_group_id", "org.omg.PortableGroup.ObjectGroupId(in:object_group org.omg.PortableGroup.ObjectGroup)");
		irInfo.put("get_object_group_ref", "org.omg.PortableGroup.ObjectGroup(in:object_group org.omg.PortableGroup.ObjectGroup)");
		irInfo.put("remove_member", "org.omg.PortableGroup.ObjectGroup(in:object_group org.omg.PortableGroup.ObjectGroup,in:the_location org.omg.PortableGroup.Location)");
		irInfo.put("get_member_ref", "(in:object_group org.omg.PortableGroup.ObjectGroup,in:loc org.omg.PortableGroup.Location)");
		irInfo.put("locations_of_members", "org.omg.PortableGroup.Locations(in:object_group org.omg.PortableGroup.ObjectGroup)");
		irInfo.put("create_member", "org.omg.PortableGroup.ObjectGroup(in:object_group org.omg.PortableGroup.ObjectGroup,in:the_location org.omg.PortableGroup.Location,in:type_id org.omg.PortableGroup.TypeId,in:the_criteria org.omg.PortableGroup.Criteria)");
		irInfo.put("add_member", "org.omg.PortableGroup.ObjectGroup(in:object_group org.omg.PortableGroup.ObjectGroup,in:the_location org.omg.PortableGroup.Location,in:member )");
	}
}
