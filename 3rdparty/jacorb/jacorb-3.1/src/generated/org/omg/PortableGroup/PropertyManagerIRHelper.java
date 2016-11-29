package org.omg.PortableGroup;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class PropertyManagerIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_properties", "org.omg.PortableGroup.Properties(in:object_group org.omg.PortableGroup.ObjectGroup)");
		irInfo.put("get_type_properties", "org.omg.PortableGroup.Properties(in:type_id org.omg.PortableGroup.TypeId)");
		irInfo.put("remove_type_properties", "(in:type_id org.omg.PortableGroup.TypeId,in:props org.omg.PortableGroup.Properties)");
		irInfo.put("set_default_properties", "(in:props org.omg.PortableGroup.Properties)");
		irInfo.put("set_properties_dynamically", "(in:object_group org.omg.PortableGroup.ObjectGroup,in:overrides org.omg.PortableGroup.Properties)");
		irInfo.put("set_type_properties", "(in:type_id org.omg.PortableGroup.TypeId,in:overrides org.omg.PortableGroup.Properties)");
		irInfo.put("remove_default_properties", "(in:props org.omg.PortableGroup.Properties)");
		irInfo.put("get_default_properties", "org.omg.PortableGroup.Properties()");
	}
}
