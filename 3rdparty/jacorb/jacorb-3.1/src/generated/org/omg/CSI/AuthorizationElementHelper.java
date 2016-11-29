package org.omg.CSI;


/**
 * Generated from IDL struct "AuthorizationElement".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthorizationElementHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AuthorizationElementHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSI.AuthorizationElementHelper.id(),"AuthorizationElement",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationElementTypeHelper.id(), "AuthorizationElementType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("the_element", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationElementContentsHelper.id(), "AuthorizationElementContents",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CSI.AuthorizationElement s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CSI.AuthorizationElement extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CSI/AuthorizationElement:1.0";
	}
	public static org.omg.CSI.AuthorizationElement read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CSI.AuthorizationElement result = new org.omg.CSI.AuthorizationElement();
		result.the_type=in.read_ulong();
		result.the_element = org.omg.CSI.AuthorizationElementContentsHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CSI.AuthorizationElement s)
	{
		out.write_ulong(s.the_type);
		org.omg.CSI.AuthorizationElementContentsHelper.write(out,s.the_element);
	}
}
