package org.omg.PortableGroup;

/**
 * Generated from IDL alias "Name".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NameHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.CosNaming.NameComponent[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.CosNaming.NameComponent[] extract (final org.omg.CORBA.Any any)
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
			synchronized(NameHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.PortableGroup.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.NameHelper.id(), "Name",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNaming.NameComponentHelper.id(),"NameComponent",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null),new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNaming.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0)), null)}))));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/PortableGroup/Name:1.0";
	}
	public static org.omg.CosNaming.NameComponent[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.CosNaming.NameComponent[] _result;
		_result = org.omg.CosNaming.NameHelper.read(_in);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.CosNaming.NameComponent[] _s)
	{
		org.omg.CosNaming.NameHelper.write(_out,_s);
	}
}
