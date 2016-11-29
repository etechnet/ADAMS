package org.omg.GIOP;
/**
 * Generated from IDL enum "LocateStatusType_1_2".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class LocateStatusType_1_2Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(LocateStatusType_1_2Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.GIOP.LocateStatusType_1_2Helper.id(),"LocateStatusType_1_2",new String[]{"UNKNOWN_OBJECT","OBJECT_HERE","OBJECT_FORWARD","OBJECT_FORWARD_PERM","LOC_SYSTEM_EXCEPTION","LOC_NEEDS_ADDRESSING_MODE"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GIOP.LocateStatusType_1_2 s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GIOP.LocateStatusType_1_2 extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GIOP/LocateStatusType_1_2:1.0";
	}
	public static LocateStatusType_1_2 read (final org.omg.CORBA.portable.InputStream in)
	{
		return LocateStatusType_1_2.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final LocateStatusType_1_2 s)
	{
		out.write_long(s.value());
	}
}
