package org.omg.CosTrading;

/**
 * Generated from IDL alias "PolicySeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PolicySeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CosTrading.Policy[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CosTrading.Policy[] extract (final org.omg.CORBA.Any any)
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
			synchronized(PolicySeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicySeqHelper.id(), "PolicySeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.PolicyHelper.id(),"Policy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicyNameHelper.id(), "PolicyName",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PolicyValueHelper.id(), "PolicyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CosTrading/PolicySeq:1.0";
	}
	public static org.omg.CosTrading.Policy[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CosTrading.Policy[] _result;
		int _l_result96 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result96 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result96);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CosTrading.Policy[_l_result96];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CosTrading.PolicyHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CosTrading.Policy[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CosTrading.PolicyHelper.write(_out,_s[i]);
		}

	}
}
