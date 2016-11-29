package org.omg.dds;


/**
 * Generated from IDL struct "ParticipantBuiltinTopicData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ParticipantBuiltinTopicDataHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ParticipantBuiltinTopicDataHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.ParticipantBuiltinTopicDataHelper.id(),"ParticipantBuiltinTopicData",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("key", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.BuiltinTopicKey_tHelper.id(), "BuiltinTopicKey_t",org.omg.CORBA.ORB.init().create_array_tc(3,org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)))), null),new org.omg.CORBA.StructMember("user_data", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.UserDataQosPolicyHelper.id(),"UserDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.ParticipantBuiltinTopicData s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.ParticipantBuiltinTopicData extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/ParticipantBuiltinTopicData:1.0";
	}
	public static org.omg.dds.ParticipantBuiltinTopicData read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.ParticipantBuiltinTopicData result = new org.omg.dds.ParticipantBuiltinTopicData();
		result.key = org.omg.dds.BuiltinTopicKey_tHelper.read(in);
		result.user_data=org.omg.dds.UserDataQosPolicyHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.ParticipantBuiltinTopicData s)
	{
		org.omg.dds.BuiltinTopicKey_tHelper.write(out,s.key);
		org.omg.dds.UserDataQosPolicyHelper.write(out,s.user_data);
	}
}
