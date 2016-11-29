package org.omg.CosNaming;

/**
 * Generated from IDL alias "BindingList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BindingListHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CosNaming.Binding[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CosNaming.Binding[] extract (final org.omg.CORBA.Any any)
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
			synchronized(BindingListHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.BindingListHelper.id(), "BindingList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.BindingHelper.id(),"Binding",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("binding_name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)}))), null),new org.omg.CORBA.StructMember("binding_type", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosNaming.BindingTypeHelper.id(),"BindingType",new String[]{"nobject","ncontext"}), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/CosNaming/BindingList:1.0";
	}
	public static org.omg.CosNaming.Binding[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CosNaming.Binding[] _result;
		int _l_result89 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result89 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result89);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new org.omg.CosNaming.Binding[_l_result89];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=org.omg.CosNaming.BindingHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CosNaming.Binding[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			org.omg.CosNaming.BindingHelper.write(_out,_s[i]);
		}

	}
}
