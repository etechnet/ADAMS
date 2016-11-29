package org.jacorb.imr;


/**
 * Generated from IDL struct "HostInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class HostInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(HostInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.imr.HostInfoHelper.id(),"HostInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.HostNameHelper.id(), "HostName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("ssd_ref", org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/imr/ServerStartupDaemon:1.0", "ServerStartupDaemon"), null),new org.omg.CORBA.StructMember("ior_string", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.IORStringHelper.id(), "IORString",org.omg.CORBA.ORB.init().create_string_tc(0)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.HostInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.imr.HostInfo extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/imr/HostInfo:1.0";
	}
	public static org.jacorb.imr.HostInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		org.jacorb.imr.HostInfo result = new org.jacorb.imr.HostInfo();
		result.name=in.read_string();
		result.ssd_ref=org.jacorb.imr.ServerStartupDaemonHelper.read(in);
		result.ior_string=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.imr.HostInfo s)
	{
		java.lang.String tmpResult1200 = s.name;
out.write_string( tmpResult1200 );
		org.jacorb.imr.ServerStartupDaemonHelper.write(out,s.ssd_ref);
		java.lang.String tmpResult1201 = s.ior_string;
out.write_string( tmpResult1201 );
	}
}
