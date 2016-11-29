package org.omg.IIOP;

/**
 * Generated from IDL alias "ListenPointList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ListenPointListHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.IIOP.ListenPoint[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.IIOP.ListenPoint[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ListenPointListHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IIOP.ListenPointListHelper.id(), "ListenPointList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IIOP.ListenPointHelper.id(),"ListenPoint",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("host", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("port", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/IIOP/ListenPointList:1.0";
	}
	public static org.omg.IIOP.ListenPoint[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.IIOP.ListenPoint[] _result;
		int _l_result84 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result84 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result84);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.IIOP.ListenPoint[_l_result84];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.IIOP.ListenPointHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.IIOP.ListenPoint[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.IIOP.ListenPointHelper.write(_out,_s[i]);
		}

	}
}
