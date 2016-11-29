package org.omg.CosTrading;

/**
 * Generated from IDL alias "OfferSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OfferSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CosTrading.Offer[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CosTrading.Offer[] extract (final org.omg.CORBA.Any any)
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
			synchronized(OfferSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.OfferSeqHelper.id(), "OfferSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.OfferHelper.id(),"Offer",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("reference", org.omg.CORBA.ORB.init().get_primitive_tc( org.omg.CORBA.TCKind.tk_objref), null),new org.omg.CORBA.StructMember("properties", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertySeqHelper.id(), "PropertySeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosTrading.PropertyHelper.id(),"Property",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}))), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CosTrading/OfferSeq:1.0";
	}
	public static org.omg.CosTrading.Offer[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CosTrading.Offer[] _result;
		int _l_result92 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result92 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result92);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CosTrading.Offer[_l_result92];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CosTrading.OfferHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CosTrading.Offer[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CosTrading.OfferHelper.write(_out,_s[i]);
		}

	}
}
