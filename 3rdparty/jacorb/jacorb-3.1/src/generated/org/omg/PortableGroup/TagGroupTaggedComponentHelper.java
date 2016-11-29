package org.omg.PortableGroup;


/**
 * Generated from IDL struct "TagGroupTaggedComponent".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class TagGroupTaggedComponentHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TagGroupTaggedComponentHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.PortableGroup.TagGroupTaggedComponentHelper.id(),"TagGroupTaggedComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("group_version", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.VersionHelper.id(),"Version",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("major", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("minor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null)}), null),new org.omg.CORBA.StructMember("group_domain_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.GroupDomainIdHelper.id(), "GroupDomainId",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("object_group_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.ObjectGroupIdHelper.id(), "ObjectGroupId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24))), null),new org.omg.CORBA.StructMember("object_group_ref_version", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.ObjectGroupRefVersionHelper.id(), "ObjectGroupRefVersion",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableGroup.TagGroupTaggedComponent s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.PortableGroup.TagGroupTaggedComponent extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/PortableGroup/TagGroupTaggedComponent:1.0";
	}
	public static org.omg.PortableGroup.TagGroupTaggedComponent read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.PortableGroup.TagGroupTaggedComponent result = new org.omg.PortableGroup.TagGroupTaggedComponent();
		result.group_version=org.omg.GIOP.VersionHelper.read(in);
		result.group_domain_id=in.read_string();
		result.object_group_id=in.read_ulonglong();
		result.object_group_ref_version=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.PortableGroup.TagGroupTaggedComponent s)
	{
		org.omg.GIOP.VersionHelper.write(out,s.group_version);
		java.lang.String tmpResult997 = s.group_domain_id;
out.write_string( tmpResult997 );
		out.write_ulonglong(s.object_group_id);
		out.write_ulong(s.object_group_ref_version);
	}
}
