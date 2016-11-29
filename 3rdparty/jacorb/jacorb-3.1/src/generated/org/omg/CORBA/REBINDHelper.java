package org.omg.CORBA;


/**
 * Generated from IDL exception "REBIND".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class REBINDHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(REBINDHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CORBA.REBINDHelper.id(),"REBIND",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("minor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("completed", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CORBA.CompletionStatusHelper.id(),"CompletionStatus",new String[]{"COMPLETED_YES","COMPLETED_NO","COMPLETED_MAYBE"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.REBIND s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CORBA.REBIND extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CORBA/REBIND:1.0";
	}
	public static org.omg.CORBA.REBIND read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		int x0;
		x0=in.read_ulong();
		org.omg.CORBA.CompletionStatus x1;
		x1=org.omg.CORBA.CompletionStatusHelper.read(in);
		final org.omg.CORBA.REBIND result = new org.omg.CORBA.REBIND(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CORBA.REBIND s)
	{
		out.write_string(id());
		out.write_ulong(s.minor);
		org.omg.CORBA.CompletionStatusHelper.write(out,s.completed);
	}
}
