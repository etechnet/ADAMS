package org.omg.CosNotifyFilter;

/**
 * Generated from IDL alias "ConstraintInfoSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintInfoSeqHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CosNotifyFilter.ConstraintInfo[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CosNotifyFilter.ConstraintInfo[] extract (final org.omg.CORBA.Any any)
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
			synchronized(ConstraintInfoSeqHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotifyFilter.ConstraintInfoSeqHelper.id(), "ConstraintInfoSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotifyFilter.ConstraintInfoHelper.id(),"ConstraintInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("constraint_expression", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotifyFilter.ConstraintExpHelper.id(),"ConstraintExp",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("event_types", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.EventTypeSeqHelper.id(), "EventTypeSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.EventTypeHelper.id(),"EventType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("domain_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type_name", org.omg.CORBA.ORB.init().create_string_tc(0), null)}))), null),new org.omg.CORBA.StructMember("constraint_expr", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null),new org.omg.CORBA.StructMember("constraint_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotifyFilter.ConstraintIDHelper.id(), "ConstraintID",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CosNotifyFilter/ConstraintInfoSeq:1.0";
	}
	public static org.omg.CosNotifyFilter.ConstraintInfo[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CosNotifyFilter.ConstraintInfo[] _result;
		int _l_result125 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result125 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result125);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CosNotifyFilter.ConstraintInfo[_l_result125];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CosNotifyFilter.ConstraintInfoHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CosNotifyFilter.ConstraintInfo[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CosNotifyFilter.ConstraintInfoHelper.write(_out,_s[i]);
		}

	}
}
