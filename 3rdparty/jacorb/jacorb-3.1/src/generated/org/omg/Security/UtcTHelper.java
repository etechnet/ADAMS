package org.omg.Security;

/**
 * Generated from IDL alias "UtcT".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class UtcTHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.TimeBase.UtcT s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.TimeBase.UtcT extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(UtcTHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.UtcTHelper.id(), "UtcT",org.omg.CORBA.ORB.init().create_struct_tc(org.omg.TimeBase.UtcTHelper.id(),"UtcT",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("time", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TimeTHelper.id(), "TimeT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24))), null),new org.omg.CORBA.StructMember("inacclo", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("inacchi", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("tdf", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TdfTHelper.id(), "TdfT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null)}));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/Security/UtcT:1.0";
	}
	public static org.omg.TimeBase.UtcT read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.TimeBase.UtcT _result;
		_result=org.omg.TimeBase.UtcTHelper.read(_in);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.TimeBase.UtcT _s)
	{
		org.omg.TimeBase.UtcTHelper.write(_out,_s);
	}
}
