package org.omg.RTCORBA;

/**
 * Generated from IDL alias "ProtocolList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ProtocolListHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.RTCORBA.Protocol[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.RTCORBA.Protocol[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ProtocolListHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.RTCORBA.ProtocolListHelper.id(), "ProtocolList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.RTCORBA.ProtocolHelper.id(),"Protocol",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("protocol_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ProfileIdHelper.id(), "ProfileId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("orb_protocol_properties", org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:omg.org/RTCORBA/ProtocolProperties:1.0", "ProtocolProperties"), null),new org.omg.CORBA.StructMember("transport_protocol_properties", org.omg.CORBA.ORB.init().create_local_interface_tc("IDL:omg.org/RTCORBA/ProtocolProperties:1.0", "ProtocolProperties"), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/RTCORBA/ProtocolList:1.0";
	}
	public static org.omg.RTCORBA.Protocol[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.RTCORBA.Protocol[] _result;
		int _l_result63 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result63 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result63);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.RTCORBA.Protocol[_l_result63];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.RTCORBA.ProtocolHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.RTCORBA.Protocol[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.RTCORBA.ProtocolHelper.write(_out,_s[i]);
		}

	}
}
