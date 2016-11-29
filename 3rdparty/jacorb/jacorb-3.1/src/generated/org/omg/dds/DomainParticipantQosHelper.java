package org.omg.dds;


/**
 * Generated from IDL struct "DomainParticipantQos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DomainParticipantQosHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DomainParticipantQosHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DomainParticipantQosHelper.id(),"DomainParticipantQos",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("user_data", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.UserDataQosPolicyHelper.id(),"UserDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null),new org.omg.CORBA.StructMember("entity_factory", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.EntityFactoryQosPolicyHelper.id(),"EntityFactoryQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("autoenable_created_entities", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.DomainParticipantQos s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.DomainParticipantQos extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/DomainParticipantQos:1.0";
	}
	public static org.omg.dds.DomainParticipantQos read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.DomainParticipantQos result = new org.omg.dds.DomainParticipantQos();
		result.user_data=org.omg.dds.UserDataQosPolicyHelper.read(in);
		result.entity_factory=org.omg.dds.EntityFactoryQosPolicyHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.DomainParticipantQos s)
	{
		org.omg.dds.UserDataQosPolicyHelper.write(out,s.user_data);
		org.omg.dds.EntityFactoryQosPolicyHelper.write(out,s.entity_factory);
	}
}
