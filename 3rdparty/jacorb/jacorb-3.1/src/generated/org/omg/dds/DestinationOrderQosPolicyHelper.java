package org.omg.dds;


/**
 * Generated from IDL struct "DestinationOrderQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DestinationOrderQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DestinationOrderQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DestinationOrderQosPolicyHelper.id(),"DestinationOrderQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.DestinationOrderQosPolicyKindHelper.id(),"DestinationOrderQosPolicyKind",new String[]{"BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS","BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.DestinationOrderQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.DestinationOrderQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/DestinationOrderQosPolicy:1.0";
	}
	public static org.omg.dds.DestinationOrderQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.DestinationOrderQosPolicy result = new org.omg.dds.DestinationOrderQosPolicy();
		result.kind=org.omg.dds.DestinationOrderQosPolicyKindHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.DestinationOrderQosPolicy s)
	{
		org.omg.dds.DestinationOrderQosPolicyKindHelper.write(out,s.kind);
	}
}
