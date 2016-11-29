package org.omg.CORBA.ContainerPackage;

/**
 * Generated from IDL alias "DescriptionSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class DescriptionSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CORBA.ContainerPackage.Description[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CORBA.ContainerPackage.Description[] extract (final org.omg.CORBA.Any any)
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
			synchronized(DescriptionSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.ContainerPackage.DescriptionSeqHelper.id(), "DescriptionSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CORBA.ContainerPackage.DescriptionHelper.id(),"Description",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("contained_object", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/Contained:1.0", "Contained"), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CORBA.DefinitionKindHelper.id(),"DefinitionKind",new String[]{"dk_none","dk_all","dk_Attribute","dk_Constant","dk_Exception","dk_Interface","dk_Module","dk_Operation","dk_Typedef","dk_Alias","dk_Struct","dk_Union","dk_Enum","dk_Primitive","dk_String","dk_Sequence","dk_Array","dk_Repository","dk_Wstring","dk_Fixed","dk_Value","dk_ValueBox","dk_ValueMember","dk_Native","dk_AbstractInterface","dk_LocalInterface","dk_Component","dk_Home","dk_Factory","dk_Finder","dk_Emits","dk_Publishes","dk_Consumes","dk_Provides","dk_Uses","dk_Event"}), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CORBA/Container/DescriptionSeq:1.0";
	}
	public static org.omg.CORBA.ContainerPackage.Description[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CORBA.ContainerPackage.Description[] _result;
		int _l_result19 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result19 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result19);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CORBA.ContainerPackage.Description[_l_result19];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CORBA.ContainerPackage.DescriptionHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CORBA.ContainerPackage.Description[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CORBA.ContainerPackage.DescriptionHelper.write(_out,_s[i]);
		}

	}
}
