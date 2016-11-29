package org.omg.Security;


/**
 * Generated from IDL struct "SelectorValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SelectorValueHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SelectorValueHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.SelectorValueHelper.id(),"SelectorValue",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("selector", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.SelectorTypeHelper.id(), "SelectorType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.SelectorValue s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.SelectorValue extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/SelectorValue:1.0";
	}
	public static org.omg.Security.SelectorValue read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.SelectorValue result = new org.omg.Security.SelectorValue();
		result.selector=in.read_ulong();
		result.value=in.read_any();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.SelectorValue s)
	{
		out.write_ulong(s.selector);
		out.write_any(s.value);
	}
}
