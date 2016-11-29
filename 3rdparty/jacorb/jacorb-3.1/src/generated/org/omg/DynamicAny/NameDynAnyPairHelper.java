package org.omg.DynamicAny;


/**
 * Generated from IDL struct "NameDynAnyPair".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NameDynAnyPairHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(NameDynAnyPairHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.DynamicAny.NameDynAnyPairHelper.id(),"NameDynAnyPair",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.DynamicAny.FieldNameHelper.id(), "FieldName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:omg.org/DynamicAny/DynAny:1.0", "DynAny"), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.DynamicAny.NameDynAnyPair s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.DynamicAny.NameDynAnyPair extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/DynamicAny/NameDynAnyPair:1.0";
	}
	public static org.omg.DynamicAny.NameDynAnyPair read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.DynamicAny.NameDynAnyPair result = new org.omg.DynamicAny.NameDynAnyPair();
		result.id=in.read_string();
		result.value=org.omg.DynamicAny.DynAnyHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.DynamicAny.NameDynAnyPair s)
	{
		java.lang.String tmpResult993 = s.id;
out.write_string( tmpResult993 );
		org.omg.DynamicAny.DynAnyHelper.write(out,s.value);
	}
}
