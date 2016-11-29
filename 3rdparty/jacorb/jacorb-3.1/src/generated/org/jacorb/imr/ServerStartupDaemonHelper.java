package org.jacorb.imr;


/**
 * Generated from IDL interface "ServerStartupDaemon".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ServerStartupDaemonHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ServerStartupDaemonHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/imr/ServerStartupDaemon:1.0", "ServerStartupDaemon");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.ServerStartupDaemon s)
	{
			any.insert_Object(s);
	}
	public static org.jacorb.imr.ServerStartupDaemon extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:org/jacorb/imr/ServerStartupDaemon:1.0";
	}
	public static ServerStartupDaemon read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.jacorb.imr._ServerStartupDaemonStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jacorb.imr.ServerStartupDaemon s)
	{
		_out.write_Object(s);
	}
	public static org.jacorb.imr.ServerStartupDaemon narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.ServerStartupDaemon)
		{
			return (org.jacorb.imr.ServerStartupDaemon)obj;
		}
		else if (obj._is_a("IDL:org/jacorb/imr/ServerStartupDaemon:1.0"))
		{
			org.jacorb.imr._ServerStartupDaemonStub stub;
			stub = new org.jacorb.imr._ServerStartupDaemonStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jacorb.imr.ServerStartupDaemon unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.ServerStartupDaemon)
		{
			return (org.jacorb.imr.ServerStartupDaemon)obj;
		}
		else
		{
			org.jacorb.imr._ServerStartupDaemonStub stub;
			stub = new org.jacorb.imr._ServerStartupDaemonStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
