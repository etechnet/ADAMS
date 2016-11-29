package org.omg.Messaging;

/**
 * Generated from IDL valuetype "Poller".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public abstract class PollerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type = null;
	public static void insert (org.omg.CORBA.Any a, org.omg.Messaging.Poller v)
	{
		a.insert_Value (v, v._type());
	}
	public static org.omg.Messaging.Poller extract (org.omg.CORBA.Any a)
	{
		return (org.omg.Messaging.Poller)a.extract_Value();
	}
	public static org.omg.CORBA.TypeCode type()
	{
		if (_type == null)
		{
			synchronized(PollerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_value_tc ("IDL:omg.org/Messaging/Poller:1.0", "Poller", (short)0, null, new org.omg.CORBA.ValueMember[] {new org.omg.CORBA.ValueMember ("target", "IDL:omg.org/CORBA/Object:1.0", "Poller", "1.0", org.omg.CORBA.ORB.init().get_primitive_tc( org.omg.CORBA.TCKind.tk_objref), null, (short)0), new org.omg.CORBA.ValueMember ("op_name", "IDL:op_name:1.0", "Poller", "1.0", org.omg.CORBA.ORB.init().create_string_tc(0), null, (short)0)});
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/Messaging/Poller:1.0";
	}
	public static org.omg.Messaging.Poller read (org.omg.CORBA.portable.InputStream is)
	{
		return (org.omg.Messaging.Poller)((org.omg.CORBA_2_3.portable.InputStream)is).read_value ("IDL:omg.org/Messaging/Poller:1.0");
	}
	public static void write (org.omg.CORBA.portable.OutputStream os, org.omg.Messaging.Poller val)
	{
		((org.omg.CORBA_2_3.portable.OutputStream)os).write_value (val, "IDL:omg.org/Messaging/Poller:1.0");
	}
}
