package org.omg.CosTrading.LookupPackage;


/**
 * Generated from IDL exception "InvalidPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InvalidPolicyValueHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidPolicyValueHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosTrading.LookupPackage.InvalidPolicyValueHelper.id(),"InvalidPolicyValue",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_policy", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.PolicyHelper.id(),"Policy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicyNameHelper.id(), "PolicyName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicyValueHelper.id(), "PolicyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.LookupPackage.InvalidPolicyValue s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.LookupPackage.InvalidPolicyValue extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/Lookup/InvalidPolicyValue:1.0";
	}
	public static org.omg.CosTrading.LookupPackage.InvalidPolicyValue read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosTrading.Policy x0;
		x0=org.omg.CosTrading.PolicyHelper.read(in);
		final org.omg.CosTrading.LookupPackage.InvalidPolicyValue result = new org.omg.CosTrading.LookupPackage.InvalidPolicyValue(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.LookupPackage.InvalidPolicyValue s)
	{
		out.write_string(id());
		org.omg.CosTrading.PolicyHelper.write(out,s.the_policy);
	}
}
