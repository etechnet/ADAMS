package org.jacorb.sasPolicy;


/**
 * Generated from IDL struct "ATLASPolicyValues".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ATLASPolicyValuesHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ATLASPolicyValuesHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.sasPolicy.ATLASPolicyValuesHelper.id(),"ATLASPolicyValues",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("atlasURL", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("atlasCache", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.sasPolicy.ATLASPolicyValues s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.sasPolicy.ATLASPolicyValues extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/sasPolicy/ATLASPolicyValues:1.0";
	}
	public static org.jacorb.sasPolicy.ATLASPolicyValues read (final org.omg.CORBA.portable.InputStream in)
	{
		org.jacorb.sasPolicy.ATLASPolicyValues result = new org.jacorb.sasPolicy.ATLASPolicyValues();
		result.atlasURL=in.read_string();
		result.atlasCache=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.sasPolicy.ATLASPolicyValues s)
	{
		java.lang.String tmpResult1240 = s.atlasURL;
out.write_string( tmpResult1240 );
		java.lang.String tmpResult1241 = s.atlasCache;
out.write_string( tmpResult1241 );
	}
}
