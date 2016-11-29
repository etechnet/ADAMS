package org.omg.MIOP;


/**
 * Generated from IDL struct "UIPMC_ProfileBody".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class UIPMC_ProfileBodyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(UIPMC_ProfileBodyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.MIOP.UIPMC_ProfileBodyHelper.id(),"UIPMC_ProfileBody",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("miop_version", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.MIOP.VersionHelper.id(), "Version",org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.VersionHelper.id(),"Version",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("major", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("minor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null)})), null),new org.omg.CORBA.StructMember("the_address", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.MIOP.AddressHelper.id(), "Address",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("the_port", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2)), null),new org.omg.CORBA.StructMember("components", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.TaggedComponentHelper.id(),"TaggedComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("tag", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ComponentIdHelper.id(), "ComponentId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("component_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)})), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.MIOP.UIPMC_ProfileBody s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.MIOP.UIPMC_ProfileBody extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/MIOP/UIPMC_ProfileBody:1.0";
	}
	public static org.omg.MIOP.UIPMC_ProfileBody read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.MIOP.UIPMC_ProfileBody result = new org.omg.MIOP.UIPMC_ProfileBody();
		result.miop_version=org.omg.GIOP.VersionHelper.read(in);
		result.the_address=in.read_string();
		result.the_port=in.read_short();
		int _lresult_components73 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_components73 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_components73);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.components = new org.omg.IOP.TaggedComponent[_lresult_components73];
		for (int i=0;i<result.components.length;i++)
		{
			result.components[i]=org.omg.IOP.TaggedComponentHelper.read(in);
		}

		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.MIOP.UIPMC_ProfileBody s)
	{
		org.omg.GIOP.VersionHelper.write(out,s.miop_version);
		java.lang.String tmpResult995 = s.the_address;
out.write_string( tmpResult995 );
		out.write_short(s.the_port);
		
		out.write_long(s.components.length);
		for (int i=0; i<s.components.length;i++)
		{
			org.omg.IOP.TaggedComponentHelper.write(out,s.components[i]);
		}

	}
}
