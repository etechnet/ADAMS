package org.omg.CORBA;

/**
 * Generated from IDL alias "InitializerSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InitializerSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CORBA.Initializer[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CORBA.Initializer[] extract (final org.omg.CORBA.Any any)
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
			synchronized(InitializerSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.InitializerSeqHelper.id(), "InitializerSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CORBA.InitializerHelper.id(),"Initializer",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("members", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.StructMemberSeqHelper.id(), "StructMemberSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CORBA.StructMemberHelper.id(),"StructMember",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.IdentifierHelper.id(), "Identifier",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("type", org.omg.CORBA.ORB.init().get_primitive_tc( org.omg.CORBA.TCKind.tk_TypeCode), null),new org.omg.CORBA.StructMember("type_def", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/IDLType:1.0", "IDLType"), null)}))), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.IdentifierHelper.id(), "Identifier",org.omg.CORBA.ORB.init().create_string_tc(0)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CORBA/InitializerSeq:1.0";
	}
	public static org.omg.CORBA.Initializer[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CORBA.Initializer[] _result;
		int _l_result14 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result14 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result14);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CORBA.Initializer[_l_result14];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CORBA.InitializerHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CORBA.Initializer[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CORBA.InitializerHelper.write(_out,_s[i]);
		}

	}
}
