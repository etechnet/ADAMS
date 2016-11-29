package org.omg.CosNaming.NamingContextPackage;


/**
 * Generated from IDL exception "CannotProceed".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CannotProceedHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(CannotProceedHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.id(),"CannotProceed",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("cxt", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNaming/NamingContext:1.0", "NamingContext"), null),new org.omg.CORBA.StructMember("rest_of_name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNaming.NamingContextPackage.CannotProceed s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNaming.NamingContextPackage.CannotProceed extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNaming/NamingContext/CannotProceed:1.0";
	}
	public static org.omg.CosNaming.NamingContextPackage.CannotProceed read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosNaming.NamingContext x0;
		x0=org.omg.CosNaming.NamingContextHelper.read(in);
		org.omg.CosNaming.NameComponent[] x1;
		x1 = org.omg.CosNaming.NameHelper.read(in);
		final org.omg.CosNaming.NamingContextPackage.CannotProceed result = new org.omg.CosNaming.NamingContextPackage.CannotProceed(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNaming.NamingContextPackage.CannotProceed s)
	{
		out.write_string(id());
		org.omg.CosNaming.NamingContextHelper.write(out,s.cxt);
		org.omg.CosNaming.NameHelper.write(out,s.rest_of_name);
	}
}
