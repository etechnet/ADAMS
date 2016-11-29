package org.omg.CosTrading;

/**
 * Generated from IDL alias "TraderName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TraderNameHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, java.lang.String[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static java.lang.String[] extract (final org.omg.CORBA.Any any)
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
			synchronized(TraderNameHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.TraderNameHelper.id(), "TraderName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.LinkNameSeqHelper.id(), "LinkNameSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CosTrading.LinkNameHelper.type())));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CosTrading/TraderName:1.0";
	}
	public static java.lang.String[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		java.lang.String[] _result;
		_result = org.omg.CosTrading.LinkNameSeqHelper.read(_in);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, java.lang.String[] _s)
	{
		org.omg.CosTrading.LinkNameSeqHelper.write(_out,_s);
	}
}
