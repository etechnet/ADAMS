package org.omg.PortableGroup;


/**
 * Generated from IDL exception "NoFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NoFactoryHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(NoFactoryHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.PortableGroup.NoFactoryHelper.id(),"NoFactory",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_location", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.LocationHelper.id(), "Location",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)}))))), null),new org.omg.CORBA.StructMember("type_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.TypeIdHelper.id(), "TypeId",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.RepositoryIdHelper.id(), "RepositoryId",org.omg.CORBA.ORB.init().create_string_tc(0))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableGroup.NoFactory s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.PortableGroup.NoFactory extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/PortableGroup/NoFactory:1.0";
	}
	public static org.omg.PortableGroup.NoFactory read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosNaming.NameComponent[] x0;
		x0 = org.omg.CosNaming.NameHelper.read(in);
		java.lang.String x1;
		x1=in.read_string();
		final org.omg.PortableGroup.NoFactory result = new org.omg.PortableGroup.NoFactory(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.PortableGroup.NoFactory s)
	{
		out.write_string(id());
		org.omg.CosNaming.NameHelper.write(out,s.the_location);
		java.lang.String tmpResult999 = s.type_id;
out.write_string( tmpResult999 );
	}
}
