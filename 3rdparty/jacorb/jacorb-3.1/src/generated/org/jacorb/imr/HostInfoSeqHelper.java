package org.jacorb.imr;

/**
 * Generated from IDL alias "HostInfoSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class HostInfoSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.jacorb.imr.HostInfo[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.jacorb.imr.HostInfo[] extract (final org.omg.CORBA.Any any)
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
			synchronized(HostInfoSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.HostInfoSeqHelper.id(), "HostInfoSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.imr.HostInfoHelper.id(),"HostInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.HostNameHelper.id(), "HostName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("ssd_ref", org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/imr/ServerStartupDaemon:1.0", "ServerStartupDaemon"), null),new org.omg.CORBA.StructMember("ior_string", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.imr.IORStringHelper.id(), "IORString",org.omg.CORBA.ORB.init().create_string_tc(0)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:org/jacorb/imr/HostInfoSeq:1.0";
	}
	public static org.jacorb.imr.HostInfo[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.jacorb.imr.HostInfo[] _result;
		int _l_result169 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result169 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result169);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.jacorb.imr.HostInfo[_l_result169];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.jacorb.imr.HostInfoHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.jacorb.imr.HostInfo[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.jacorb.imr.HostInfoHelper.write(_out,_s[i]);
		}

	}
}
