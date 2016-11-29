package org.omg.PortableServer;


/**
 * Generated from IDL interface "ServantRetentionPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ServantRetentionPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ServantRetentionPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:omg.org/PortableServer/ServantRetentionPolicy:1.0", "ServantRetentionPolicy");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableServer.ServantRetentionPolicy s)
	{
			any.insert_Object(s);
	}
	public static org.omg.PortableServer.ServantRetentionPolicy extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/PortableServer/ServantRetentionPolicy:1.0";
	}
	public static ServantRetentionPolicy read(final org.omg.CORBA.portable.InputStream in)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.PortableServer.ServantRetentionPolicy s)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static org.omg.PortableServer.ServantRetentionPolicy narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableServer.ServantRetentionPolicy)
		{
			return (org.omg.PortableServer.ServantRetentionPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.PortableServer.ServantRetentionPolicy unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableServer.ServantRetentionPolicy)
		{
			return (org.omg.PortableServer.ServantRetentionPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
