package org.omg.PortableGroup;

/**
 * Generated from IDL alias "FactoryInfos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class FactoryInfosHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.PortableGroup.FactoryInfo[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.PortableGroup.FactoryInfo[] extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(FactoryInfosHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.FactoryInfosHelper.id(), "FactoryInfos",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.PortableGroup.FactoryInfoHelper.id(),"FactoryInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_factory", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/PortableGroup/GenericFactory:1.0", "GenericFactory"), null),new org.omg.CORBA.StructMember("the_location", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.LocationHelper.id(), "Location",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)}))))), null),new org.omg.CORBA.StructMember("the_criteria", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.CriteriaHelper.id(), "Criteria",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.PropertiesHelper.id(), "Properties",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.PortableGroup.PropertyHelper.id(),"Property",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("nam", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)})))), null),new org.omg.CORBA.StructMember("val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.ValueHelper.id(), "Value",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)})))), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/PortableGroup/FactoryInfos:1.0";
	}
	public static org.omg.PortableGroup.FactoryInfo[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.PortableGroup.FactoryInfo[] _result;
		int _l_result77 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result77 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result77);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.PortableGroup.FactoryInfo[_l_result77];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.PortableGroup.FactoryInfoHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.PortableGroup.FactoryInfo[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.PortableGroup.FactoryInfoHelper.write(_out,_s[i]);
		}

	}
}
