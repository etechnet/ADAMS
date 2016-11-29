package org.omg.dds;


/**
 * Generated from IDL struct "HistoryQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class HistoryQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(HistoryQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.HistoryQosPolicyHelper.id(),"HistoryQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.HistoryQosPolicyKindHelper.id(),"HistoryQosPolicyKind",new String[]{"KEEP_LAST_HISTORY_QOS","KEEP_ALL_HISTORY_QOS"}), null),new org.omg.CORBA.StructMember("depth", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.HistoryQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.HistoryQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/HistoryQosPolicy:1.0";
	}
	public static org.omg.dds.HistoryQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.HistoryQosPolicy result = new org.omg.dds.HistoryQosPolicy();
		result.kind=org.omg.dds.HistoryQosPolicyKindHelper.read(in);
		result.depth=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.HistoryQosPolicy s)
	{
		org.omg.dds.HistoryQosPolicyKindHelper.write(out,s.kind);
		out.write_long(s.depth);
	}
}
