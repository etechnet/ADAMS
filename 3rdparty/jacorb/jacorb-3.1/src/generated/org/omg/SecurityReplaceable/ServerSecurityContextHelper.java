package org.omg.SecurityReplaceable;


/**
 * Generated from IDL interface "ServerSecurityContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class ServerSecurityContextHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ServerSecurityContextHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/SecurityReplaceable/ServerSecurityContext:1.0", "ServerSecurityContext");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.SecurityReplaceable.ServerSecurityContext s)
	{
			any.insert_Object(s);
	}
	public static org.omg.SecurityReplaceable.ServerSecurityContext extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/SecurityReplaceable/ServerSecurityContext:1.0";
	}
	public static ServerSecurityContext read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.SecurityReplaceable.ServerSecurityContext s)
	{
		_out.write_Object(s);
	}
	public static org.omg.SecurityReplaceable.ServerSecurityContext narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.SecurityReplaceable.ServerSecurityContext)
		{
			return (org.omg.SecurityReplaceable.ServerSecurityContext)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.SecurityReplaceable.ServerSecurityContext unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.SecurityReplaceable.ServerSecurityContext)
		{
			return (org.omg.SecurityReplaceable.ServerSecurityContext)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
