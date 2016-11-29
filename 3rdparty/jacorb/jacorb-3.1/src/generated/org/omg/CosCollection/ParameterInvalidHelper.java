package org.omg.CosCollection;


/**
 * Generated from IDL exception "ParameterInvalid".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ParameterInvalidHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ParameterInvalidHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosCollection.ParameterInvalidHelper.id(),"ParameterInvalid",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("which", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("why", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosCollection.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosCollection.ParameterInvalid s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosCollection.ParameterInvalid extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosCollection/ParameterInvalid:1.0";
	}
	public static org.omg.CosCollection.ParameterInvalid read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		int x0;
		x0=in.read_ulong();
		java.lang.String x1;
		x1=in.read_string();
		final org.omg.CosCollection.ParameterInvalid result = new org.omg.CosCollection.ParameterInvalid(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosCollection.ParameterInvalid s)
	{
		out.write_string(id());
		out.write_ulong(s.which);
		java.lang.String tmpResult1097 = s.why;
out.write_string( tmpResult1097 );
	}
}
