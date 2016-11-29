package org.omg.CosNotification;

/**
 * Generated from IDL alias "PropertyErrorSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PropertyErrorSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CosNotification.PropertyError[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CosNotification.PropertyError[] extract (final org.omg.CORBA.Any any)
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
			synchronized(PropertyErrorSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyErrorSeqHelper.id(), "PropertyErrorSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.PropertyErrorHelper.id(),"PropertyError",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("code", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosNotification.QoSError_codeHelper.id(),"QoSError_code",new String[]{"UNSUPPORTED_PROPERTY","UNAVAILABLE_PROPERTY","UNSUPPORTED_VALUE","UNAVAILABLE_VALUE","BAD_PROPERTY","BAD_TYPE","BAD_VALUE"}), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("available_range", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.PropertyRangeHelper.id(),"PropertyRange",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("low_val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null),new org.omg.CORBA.StructMember("high_val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CosNotification/PropertyErrorSeq:1.0";
	}
	public static org.omg.CosNotification.PropertyError[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CosNotification.PropertyError[] _result;
		int _l_result118 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result118 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result118);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CosNotification.PropertyError[_l_result118];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CosNotification.PropertyErrorHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CosNotification.PropertyError[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CosNotification.PropertyErrorHelper.write(_out,_s[i]);
		}

	}
}
