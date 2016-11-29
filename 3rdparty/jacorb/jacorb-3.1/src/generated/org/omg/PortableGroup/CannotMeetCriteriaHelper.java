package org.omg.PortableGroup;


/**
 * Generated from IDL exception "CannotMeetCriteria".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class CannotMeetCriteriaHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(CannotMeetCriteriaHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.PortableGroup.CannotMeetCriteriaHelper.id(),"CannotMeetCriteria",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("unmet_criteria", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.CriteriaHelper.id(), "Criteria",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.PropertiesHelper.id(), "Properties",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.PortableGroup.PropertyHelper.id(),"Property",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("nam", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)})))), null),new org.omg.CORBA.StructMember("val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.ValueHelper.id(), "Value",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)})))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableGroup.CannotMeetCriteria s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.PortableGroup.CannotMeetCriteria extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/PortableGroup/CannotMeetCriteria:1.0";
	}
	public static org.omg.PortableGroup.CannotMeetCriteria read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.PortableGroup.Property[] x0;
		x0 = org.omg.PortableGroup.PropertiesHelper.read(in);
		final org.omg.PortableGroup.CannotMeetCriteria result = new org.omg.PortableGroup.CannotMeetCriteria(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.PortableGroup.CannotMeetCriteria s)
	{
		out.write_string(id());
		org.omg.PortableGroup.PropertiesHelper.write(out,s.unmet_criteria);
	}
}
