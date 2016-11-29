package org.omg.IOP;

/**
 * Generated from IDL alias "ProfileId".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ProfileIdHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, int s)
	{
		any.insert_ulong(s);
	}

	public static int extract (final org.omg.CORBA.Any any)
	{
		return any.extract_ulong();
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ProfileIdHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ProfileIdHelper.id(), "ProfileId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/IOP/ProfileId:1.0";
	}
	public static int read (final org.omg.CORBA.portable.InputStream _in)
	{
		int _result;
		_result=_in.read_ulong();
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, int _s)
	{
		_out.write_ulong(_s);
	}
}
