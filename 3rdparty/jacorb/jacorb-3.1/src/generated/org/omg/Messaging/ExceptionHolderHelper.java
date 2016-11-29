package org.omg.Messaging;

/**
 * Generated from IDL valuetype "ExceptionHolder".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public abstract class ExceptionHolderHelper
{
	private volatile static org.omg.CORBA.TypeCode _type = null;
	public static void insert (org.omg.CORBA.Any a, org.omg.Messaging.ExceptionHolder v)
	{
		a.insert_Value (v, v._type());
	}
	public static org.omg.Messaging.ExceptionHolder extract (org.omg.CORBA.Any a)
	{
		return (org.omg.Messaging.ExceptionHolder)a.extract_Value();
	}
	public static org.omg.CORBA.TypeCode type()
	{
		if (_type == null)
		{
			synchronized(ExceptionHolderHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_value_tc ("IDL:omg.org/Messaging/ExceptionHolder:1.0", "ExceptionHolder", (short)0, null, new org.omg.CORBA.ValueMember[] {new org.omg.CORBA.ValueMember ("is_system_exception", "IDL:*primitive*:1.0", "ExceptionHolder", "1.0", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null, (short)0), new org.omg.CORBA.ValueMember ("byte_order", "IDL:*primitive*:1.0", "ExceptionHolder", "1.0", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null, (short)0), new org.omg.CORBA.ValueMember ("marshaled_exception", "IDL:marshaled_exception:1.0", "ExceptionHolder", "1.0", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null, (short)0)});
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/Messaging/ExceptionHolder:1.0";
	}
	public static org.omg.Messaging.ExceptionHolder read (org.omg.CORBA.portable.InputStream is)
	{
		return (org.omg.Messaging.ExceptionHolder)((org.omg.CORBA_2_3.portable.InputStream)is).read_value ("IDL:omg.org/Messaging/ExceptionHolder:1.0");
	}
	public static void write (org.omg.CORBA.portable.OutputStream os, org.omg.Messaging.ExceptionHolder val)
	{
		((org.omg.CORBA_2_3.portable.OutputStream)os).write_value (val, "IDL:omg.org/Messaging/ExceptionHolder:1.0");
	}
}
