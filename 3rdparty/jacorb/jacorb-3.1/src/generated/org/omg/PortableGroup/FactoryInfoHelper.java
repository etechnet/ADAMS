package org.omg.PortableGroup;


/**
 * Generated from IDL struct "FactoryInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class FactoryInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(FactoryInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.PortableGroup.FactoryInfoHelper.id(),"FactoryInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_factory", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/PortableGroup/GenericFactory:1.0", "GenericFactory"), null),new org.omg.CORBA.StructMember("the_location", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.LocationHelper.id(), "Location",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)}))))), null),new org.omg.CORBA.StructMember("the_criteria", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.CriteriaHelper.id(), "Criteria",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.PropertiesHelper.id(), "Properties",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.PortableGroup.PropertyHelper.id(),"Property",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("nam", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)})))), null),new org.omg.CORBA.StructMember("val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.ValueHelper.id(), "Value",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)})))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableGroup.FactoryInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.PortableGroup.FactoryInfo extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/PortableGroup/FactoryInfo:1.0";
	}
	public static org.omg.PortableGroup.FactoryInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.PortableGroup.FactoryInfo result = new org.omg.PortableGroup.FactoryInfo();
		result.the_factory=org.omg.PortableGroup.GenericFactoryHelper.read(in);
		result.the_location = org.omg.CosNaming.NameHelper.read(in);
		result.the_criteria = org.omg.PortableGroup.PropertiesHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.PortableGroup.FactoryInfo s)
	{
		org.omg.PortableGroup.GenericFactoryHelper.write(out,s.the_factory);
		org.omg.CosNaming.NameHelper.write(out,s.the_location);
		org.omg.PortableGroup.PropertiesHelper.write(out,s.the_criteria);
	}
}
