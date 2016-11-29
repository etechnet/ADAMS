package org.omg.CORBA;

/**
 * Generated from IDL alias "ExtInterfaceDefSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtInterfaceDefSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CORBA.ExtInterfaceDef[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CORBA.ExtInterfaceDef[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ExtInterfaceDefSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CORBA.ExtInterfaceDefSeqHelper.id(), "ExtInterfaceDefSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/ExtInterfaceDef:1.0", "ExtInterfaceDef")));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CORBA/ExtInterfaceDefSeq:1.0";
	}
	public static org.omg.CORBA.ExtInterfaceDef[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CORBA.ExtInterfaceDef[] _result;
		int _l_result8 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result8 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result8);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CORBA.ExtInterfaceDef[_l_result8];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CORBA.ExtInterfaceDefHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CORBA.ExtInterfaceDef[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CORBA.ExtInterfaceDefHelper.write(_out,_s[i]);
		}

	}
}
