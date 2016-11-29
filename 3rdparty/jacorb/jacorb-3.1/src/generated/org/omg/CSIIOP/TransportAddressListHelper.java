package org.omg.CSIIOP;

/**
 * Generated from IDL alias "TransportAddressList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransportAddressListHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CSIIOP.TransportAddress[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CSIIOP.TransportAddress[] extract (final org.omg.CORBA.Any any)
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
			synchronized(TransportAddressListHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.TransportAddressListHelper.id(), "TransportAddressList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSIIOP.TransportAddressHelper.id(),"TransportAddress",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("host_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CSIIOP/TransportAddressList:1.0";
	}
	public static org.omg.CSIIOP.TransportAddress[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CSIIOP.TransportAddress[] _result;
		int _l_result148 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result148 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result148);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CSIIOP.TransportAddress[_l_result148];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CSIIOP.TransportAddressHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CSIIOP.TransportAddress[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CSIIOP.TransportAddressHelper.write(_out,_s[i]);
		}

	}
}
