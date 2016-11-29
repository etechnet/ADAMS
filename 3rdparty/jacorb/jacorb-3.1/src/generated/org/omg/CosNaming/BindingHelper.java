package org.omg.CosNaming;


/**
 * Generated from IDL struct "Binding".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BindingHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(BindingHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.BindingHelper.id(),"Binding",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("binding_name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)}))), null),new org.omg.CORBA.StructMember("binding_type", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosNaming.BindingTypeHelper.id(),"BindingType",new String[]{"nobject","ncontext"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNaming.Binding s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNaming.Binding extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNaming/Binding:1.0";
	}
	public static org.omg.CosNaming.Binding read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosNaming.Binding result = new org.omg.CosNaming.Binding();
		result.binding_name = org.omg.CosNaming.NameHelper.read(in);
		result.binding_type=org.omg.CosNaming.BindingTypeHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNaming.Binding s)
	{
		org.omg.CosNaming.NameHelper.write(out,s.binding_name);
		org.omg.CosNaming.BindingTypeHelper.write(out,s.binding_type);
	}
}
