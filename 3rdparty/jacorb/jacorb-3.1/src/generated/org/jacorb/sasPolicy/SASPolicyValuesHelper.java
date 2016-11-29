package org.jacorb.sasPolicy;


/**
 * Generated from IDL struct "SASPolicyValues".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class SASPolicyValuesHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SASPolicyValuesHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.sasPolicy.SASPolicyValuesHelper.id(),"SASPolicyValues",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("targetRequires", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2)), null),new org.omg.CORBA.StructMember("targetSupports", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2)), null),new org.omg.CORBA.StructMember("stateful", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.sasPolicy.SASPolicyValues s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.sasPolicy.SASPolicyValues extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/sasPolicy/SASPolicyValues:1.0";
	}
	public static org.jacorb.sasPolicy.SASPolicyValues read (final org.omg.CORBA.portable.InputStream in)
	{
		org.jacorb.sasPolicy.SASPolicyValues result = new org.jacorb.sasPolicy.SASPolicyValues();
		result.targetRequires=in.read_short();
		result.targetSupports=in.read_short();
		result.stateful=in.read_boolean();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.sasPolicy.SASPolicyValues s)
	{
		out.write_short(s.targetRequires);
		out.write_short(s.targetSupports);
		out.write_boolean(s.stateful);
	}
}
