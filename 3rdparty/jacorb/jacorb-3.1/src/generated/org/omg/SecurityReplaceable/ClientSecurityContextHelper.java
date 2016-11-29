package org.omg.SecurityReplaceable;


/**
 * Generated from IDL interface "ClientSecurityContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class ClientSecurityContextHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ClientSecurityContextHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/SecurityReplaceable/ClientSecurityContext:1.0", "ClientSecurityContext");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.SecurityReplaceable.ClientSecurityContext s)
	{
			any.insert_Object(s);
	}
	public static org.omg.SecurityReplaceable.ClientSecurityContext extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/SecurityReplaceable/ClientSecurityContext:1.0";
	}
	public static ClientSecurityContext read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.SecurityReplaceable.ClientSecurityContext s)
	{
		_out.write_Object(s);
	}
	public static org.omg.SecurityReplaceable.ClientSecurityContext narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.SecurityReplaceable.ClientSecurityContext)
		{
			return (org.omg.SecurityReplaceable.ClientSecurityContext)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.SecurityReplaceable.ClientSecurityContext unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.SecurityReplaceable.ClientSecurityContext)
		{
			return (org.omg.SecurityReplaceable.ClientSecurityContext)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
