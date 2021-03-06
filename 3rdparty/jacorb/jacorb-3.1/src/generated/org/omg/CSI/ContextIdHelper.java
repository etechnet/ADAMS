package org.omg.CSI;

/**
 * Generated from IDL alias "ContextId".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ContextIdHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, long s)
	{
		any.insert_ulonglong(s);
	}

	public static long extract (final org.omg.CORBA.Any any)
	{
		return any.extract_ulonglong();
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ContextIdHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.ContextIdHelper.id(), "ContextId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24)));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CSI/ContextId:1.0";
	}
	public static long read (final org.omg.CORBA.portable.InputStream _in)
	{
		long _result;
		_result=_in.read_ulonglong();
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, long _s)
	{
		_out.write_ulonglong(_s);
	}
}
