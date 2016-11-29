package org.omg.Security;

/**
 * Generated from IDL alias "AttributeTypeList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AttributeTypeListHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.Security.AttributeType[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.Security.AttributeType[] extract (final org.omg.CORBA.Any any)
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
			synchronized(AttributeTypeListHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.AttributeTypeListHelper.id(), "AttributeTypeList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.AttributeTypeHelper.id(),"AttributeType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("attribute_family", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.ExtensibleFamilyHelper.id(),"ExtensibleFamily",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("family_definer", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("family", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)}), null),new org.omg.CORBA.StructMember("attribute_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.SecurityAttributeTypeHelper.id(), "SecurityAttributeType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/Security/AttributeTypeList:1.0";
	}
	public static org.omg.Security.AttributeType[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.Security.AttributeType[] _result;
		int _l_result156 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result156 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result156);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.Security.AttributeType[_l_result156];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.Security.AttributeTypeHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.Security.AttributeType[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.Security.AttributeTypeHelper.write(_out,_s[i]);
		}

	}
}
