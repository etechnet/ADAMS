package org.jacorb.imr;

/**
 * Generated from IDL alias "ServerInfoSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ServerInfoSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.jacorb.imr.ServerInfo[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.jacorb.imr.ServerInfo[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ServerInfoSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.ServerInfoSeqHelper.id(), "ServerInfoSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.imr.ServerInfoHelper.id(),"ServerInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.LogicalServerNameHelper.id(), "LogicalServerName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("command", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.StartupCommandHelper.id(), "StartupCommand",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("poas", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.POAInfoSeqHelper.id(), "POAInfoSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.imr.POAInfoHelper.id(),"POAInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.POANameHelper.id(), "POAName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.HostNameHelper.id(), "HostName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.PortNumberHelper.id(), "PortNumber",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("server", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.LogicalServerNameHelper.id(), "LogicalServerName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("active", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)}))), null),new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.HostNameHelper.id(), "HostName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("active", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("holding", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:org/jacorb/imr/ServerInfoSeq:1.0";
	}
	public static org.jacorb.imr.ServerInfo[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.jacorb.imr.ServerInfo[] _result;
		int _l_result168 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result168 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result168);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.jacorb.imr.ServerInfo[_l_result168];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.jacorb.imr.ServerInfoHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.jacorb.imr.ServerInfo[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.jacorb.imr.ServerInfoHelper.write(_out,_s[i]);
		}

	}
}
