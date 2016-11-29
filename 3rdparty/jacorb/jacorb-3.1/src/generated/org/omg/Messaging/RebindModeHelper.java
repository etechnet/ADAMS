package org.omg.Messaging;

/**
 * Generated from IDL alias "RebindMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RebindModeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, short s)
	{
		any.insert_short(s);
	}

	public static short extract (final org.omg.CORBA.Any any)
	{
		return any.extract_short();
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RebindModeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Messaging.RebindModeHelper.id(), "RebindMode",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2)));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/Messaging/RebindMode:1.0";
	}
	public static short read (final org.omg.CORBA.portable.InputStream _in)
	{
		short _result;
		_result=_in.read_short();
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, short _s)
	{
		_out.write_short(_s);
	}
}
