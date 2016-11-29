package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ORBInitInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ORBInitInfoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ORBInitInfoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:omg.org/PortableInterceptor/ORBInitInfo:1.0", "ORBInitInfo");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableInterceptor.ORBInitInfo s)
	{
			any.insert_Object(s);
	}
	public static org.omg.PortableInterceptor.ORBInitInfo extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/PortableInterceptor/ORBInitInfo:1.0";
	}
	public static ORBInitInfo read(final org.omg.CORBA.portable.InputStream in)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.PortableInterceptor.ORBInitInfo s)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static org.omg.PortableInterceptor.ORBInitInfo narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableInterceptor.ORBInitInfo)
		{
			return (org.omg.PortableInterceptor.ORBInitInfo)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.PortableInterceptor.ORBInitInfo unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableInterceptor.ORBInitInfo)
		{
			return (org.omg.PortableInterceptor.ORBInitInfo)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
