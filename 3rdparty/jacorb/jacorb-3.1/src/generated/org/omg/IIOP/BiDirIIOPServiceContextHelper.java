package org.omg.IIOP;


/**
 * Generated from IDL struct "BiDirIIOPServiceContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BiDirIIOPServiceContextHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(BiDirIIOPServiceContextHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.BiDirIIOPServiceContextHelper.id(),"BiDirIIOPServiceContext",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("listen_points", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IIOP.ListenPointListHelper.id(), "ListenPointList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.ListenPointHelper.id(),"ListenPoint",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IIOP.BiDirIIOPServiceContext s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IIOP.BiDirIIOPServiceContext extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/IIOP/BiDirIIOPServiceContext:1.0";
	}
	public static org.omg.IIOP.BiDirIIOPServiceContext read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.IIOP.BiDirIIOPServiceContext result = new org.omg.IIOP.BiDirIIOPServiceContext();
		result.listen_points = org.omg.IIOP.ListenPointListHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IIOP.BiDirIIOPServiceContext s)
	{
		org.omg.IIOP.ListenPointListHelper.write(out,s.listen_points);
	}
}
