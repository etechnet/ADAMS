package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL alias "ExternalEndpointErrorSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointErrorSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CosBridgeAdmin.ExternalEndpointError[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CosBridgeAdmin.ExternalEndpointError[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ExternalEndpointErrorSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorSeqHelper.id(), "ExternalEndpointErrorSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.id(),"ExternalEndpointError",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("role", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.ExternalEndpointRoleHelper.id(),"ExternalEndpointRole",new String[]{"SOURCE","SINK"}), null),new org.omg.CORBA.StructMember("code", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosBridgeAdmin.ExternalEndpointErrorCodeHelper.id(),"ExternalEndpointErrorCode",new String[]{"INVALID_CHANNELID","INVALID_JMSDESTINATION","MISMATCH_ENDPOINTROLE_NOTIFSTYLE"}), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CosBridgeAdmin/ExternalEndpointErrorSeq:1.0";
	}
	public static org.omg.CosBridgeAdmin.ExternalEndpointError[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CosBridgeAdmin.ExternalEndpointError[] _result;
		int _l_result130 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result130 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result130);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CosBridgeAdmin.ExternalEndpointError[_l_result130];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CosBridgeAdmin.ExternalEndpointError[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.write(_out,_s[i]);
		}

	}
}
