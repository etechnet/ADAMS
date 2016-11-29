package org.omg.dds;

/**
 * Generated from IDL alias "SampleInfoSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SampleInfoSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.dds.SampleInfo[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.dds.SampleInfo[] extract (final org.omg.CORBA.Any any)
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
			synchronized(SampleInfoSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.SampleInfoSeqHelper.id(), "SampleInfoSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.SampleInfoHelper.id(),"SampleInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sample_state", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.SampleStateKindHelper.id(), "SampleStateKind",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("view_state", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.ViewStateKindHelper.id(), "ViewStateKind",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("instance_state", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.InstanceStateKindHelper.id(), "InstanceStateKind",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("source_timestamp", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Time_tHelper.id(),"Time_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null),new org.omg.CORBA.StructMember("instance_handle", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.InstanceHandle_tHelper.id(), "InstanceHandle_t",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null),new org.omg.CORBA.StructMember("disposed_generation_count", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("no_writers_generation_count", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("sample_rank", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("generation_rank", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("absolute_generation_rank", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/dds/SampleInfoSeq:1.0";
	}
	public static org.omg.dds.SampleInfo[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.dds.SampleInfo[] _result;
		int _l_result114 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result114 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result114);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.dds.SampleInfo[_l_result114];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.dds.SampleInfoHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.dds.SampleInfo[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.dds.SampleInfoHelper.write(_out,_s[i]);
		}

	}
}
