package org.omg.IOP;

/**
 * Generated from IDL alias "ServiceContextList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ServiceContextListHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.IOP.ServiceContext[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.IOP.ServiceContext[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ServiceContextListHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ServiceContextListHelper.id(), "ServiceContextList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.ServiceContextHelper.id(),"ServiceContext",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("context_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ServiceIdHelper.id(), "ServiceId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("context_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/IOP/ServiceContextList:1.0";
	}
	public static org.omg.IOP.ServiceContext[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.IOP.ServiceContext[] _result;
		int _l_result61 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result61 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result61);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.IOP.ServiceContext[_l_result61];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.IOP.ServiceContextHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.IOP.ServiceContext[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.IOP.ServiceContextHelper.write(_out,_s[i]);
		}

	}
}
