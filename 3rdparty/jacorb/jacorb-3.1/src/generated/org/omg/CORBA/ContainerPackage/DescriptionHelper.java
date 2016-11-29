package org.omg.CORBA.ContainerPackage;


/**
 * Generated from IDL struct "Description".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class DescriptionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DescriptionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CORBA.ContainerPackage.DescriptionHelper.id(),"Description",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("contained_object", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/Contained:1.0", "Contained"), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CORBA.DefinitionKindHelper.id(),"DefinitionKind",new String[]{"dk_none","dk_all","dk_Attribute","dk_Constant","dk_Exception","dk_Interface","dk_Module","dk_Operation","dk_Typedef","dk_Alias","dk_Struct","dk_Union","dk_Enum","dk_Primitive","dk_String","dk_Sequence","dk_Array","dk_Repository","dk_Wstring","dk_Fixed","dk_Value","dk_ValueBox","dk_ValueMember","dk_Native","dk_AbstractInterface","dk_LocalInterface","dk_Component","dk_Home","dk_Factory","dk_Finder","dk_Emits","dk_Publishes","dk_Consumes","dk_Provides","dk_Uses","dk_Event"}), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.ContainerPackage.Description s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CORBA.ContainerPackage.Description extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CORBA/Container/Description:1.0";
	}
	public static org.omg.CORBA.ContainerPackage.Description read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CORBA.ContainerPackage.Description result = new org.omg.CORBA.ContainerPackage.Description();
		result.contained_object=org.omg.CORBA.ContainedHelper.read(in);
		result.kind=org.omg.CORBA.DefinitionKindHelper.read(in);
		result.value=in.read_any();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CORBA.ContainerPackage.Description s)
	{
		org.omg.CORBA.ContainedHelper.write(out,s.contained_object);
		org.omg.CORBA.DefinitionKindHelper.write(out,s.kind);
		out.write_any(s.value);
	}
}
