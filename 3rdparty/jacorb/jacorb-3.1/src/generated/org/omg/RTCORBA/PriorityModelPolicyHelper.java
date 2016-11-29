package org.omg.RTCORBA;


/**
 * Generated from IDL interface "PriorityModelPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PriorityModelPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PriorityModelPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:omg.org/RTCORBA/PriorityModelPolicy:1.0", "PriorityModelPolicy");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.RTCORBA.PriorityModelPolicy s)
	{
			any.insert_Object(s);
	}
	public static org.omg.RTCORBA.PriorityModelPolicy extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/RTCORBA/PriorityModelPolicy:1.0";
	}
	public static PriorityModelPolicy read(final org.omg.CORBA.portable.InputStream in)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.RTCORBA.PriorityModelPolicy s)
	{
		throw new org.omg.CORBA.MARSHAL();
	}
	public static org.omg.RTCORBA.PriorityModelPolicy narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.RTCORBA.PriorityModelPolicy)
		{
			return (org.omg.RTCORBA.PriorityModelPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.RTCORBA.PriorityModelPolicy unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.RTCORBA.PriorityModelPolicy)
		{
			return (org.omg.RTCORBA.PriorityModelPolicy)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
