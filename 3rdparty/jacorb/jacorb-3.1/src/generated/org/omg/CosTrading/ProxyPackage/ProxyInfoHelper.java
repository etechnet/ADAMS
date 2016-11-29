package org.omg.CosTrading.ProxyPackage;


/**
 * Generated from IDL struct "ProxyInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ProxyInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ProxyInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.ProxyPackage.ProxyInfoHelper.id(),"ProxyInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.ServiceTypeNameHelper.id(), "ServiceTypeName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("target", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTrading/Lookup:1.0", "Lookup"), null),new org.omg.CORBA.StructMember("properties", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertySeqHelper.id(), "PropertySeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.PropertyHelper.id(),"Property",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}))), null),new org.omg.CORBA.StructMember("if_match_all", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("recipe", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.ProxyPackage.ConstraintRecipeHelper.id(), "ConstraintRecipe",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("policies_to_pass_on", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicySeqHelper.id(), "PolicySeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.PolicyHelper.id(),"Policy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicyNameHelper.id(), "PolicyName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicyValueHelper.id(), "PolicyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.ProxyPackage.ProxyInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.ProxyPackage.ProxyInfo extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/Proxy/ProxyInfo:1.0";
	}
	public static org.omg.CosTrading.ProxyPackage.ProxyInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosTrading.ProxyPackage.ProxyInfo result = new org.omg.CosTrading.ProxyPackage.ProxyInfo();
		result.type=in.read_string();
		result.target=org.omg.CosTrading.LookupHelper.read(in);
		result.properties = org.omg.CosTrading.PropertySeqHelper.read(in);
		result.if_match_all=in.read_boolean();
		result.recipe=in.read_string();
		result.policies_to_pass_on = org.omg.CosTrading.PolicySeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.ProxyPackage.ProxyInfo s)
	{
		java.lang.String tmpResult1089 = s.type;
out.write_string( tmpResult1089 );
		org.omg.CosTrading.LookupHelper.write(out,s.target);
		org.omg.CosTrading.PropertySeqHelper.write(out,s.properties);
		out.write_boolean(s.if_match_all);
		java.lang.String tmpResult1090 = s.recipe;
out.write_string( tmpResult1090 );
		org.omg.CosTrading.PolicySeqHelper.write(out,s.policies_to_pass_on);
	}
}
