package org.omg.CosTrading.RegisterPackage;


/**
 * Generated from IDL struct "OfferInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OfferInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(OfferInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.RegisterPackage.OfferInfoHelper.id(),"OfferInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("reference", org.omg.CORBA.ORB.init().get_primitive_tc( org.omg.CORBA.TCKind.tk_objref), null),new org.omg.CORBA.StructMember("type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.ServiceTypeNameHelper.id(), "ServiceTypeName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("properties", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertySeqHelper.id(), "PropertySeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.PropertyHelper.id(),"Property",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.RegisterPackage.OfferInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.RegisterPackage.OfferInfo extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/Register/OfferInfo:1.0";
	}
	public static org.omg.CosTrading.RegisterPackage.OfferInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosTrading.RegisterPackage.OfferInfo result = new org.omg.CosTrading.RegisterPackage.OfferInfo();
		result.reference=in.read_Object();
		result.type=in.read_string();
		result.properties = org.omg.CosTrading.PropertySeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.RegisterPackage.OfferInfo s)
	{
		out.write_Object(s.reference);
		java.lang.String tmpResult1067 = s.type;
out.write_string( tmpResult1067 );
		org.omg.CosTrading.PropertySeqHelper.write(out,s.properties);
	}
}
