package org.omg.RTCORBA;


/**
 * Generated from IDL interface "ThreadpoolPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ThreadpoolPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ThreadpoolPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:omg.org/RTCORBA/ThreadpoolPolicy:1.0", "ThreadpoolPolicy");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.RTCORBA.ThreadpoolPolicy s)
	{
			any.insert_Object(s);
	}
	public static org.omg.RTCORBA.ThreadpoolPolicy extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/RTCORBA/ThreadpoolPolicy:1.0";
	}
	public static ThreadpoolPolicy read(final org.omg.CORBA.portable.InputStream in)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.RTCORBA.ThreadpoolPolicy s)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static org.omg.RTCORBA.ThreadpoolPolicy narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.RTCORBA.ThreadpoolPolicy)
		{
			return (org.omg.RTCORBA.ThreadpoolPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.RTCORBA.ThreadpoolPolicy unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.RTCORBA.ThreadpoolPolicy)
		{
			return (org.omg.RTCORBA.ThreadpoolPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
