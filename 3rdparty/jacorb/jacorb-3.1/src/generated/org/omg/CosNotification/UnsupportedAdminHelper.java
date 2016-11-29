package org.omg.CosNotification;


/**
 * Generated from IDL exception "UnsupportedAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class UnsupportedAdminHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(UnsupportedAdminHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosNotification.UnsupportedAdminHelper.id(),"UnsupportedAdmin",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("admin_err", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyErrorSeqHelper.id(), "PropertyErrorSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.PropertyErrorHelper.id(),"PropertyError",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("code", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosNotification.QoSError_codeHelper.id(),"QoSError_code",new String[]{"UNSUPPORTED_PROPERTY","UNAVAILABLE_PROPERTY","UNSUPPORTED_VALUE","UNAVAILABLE_VALUE","BAD_PROPERTY","BAD_TYPE","BAD_VALUE"}), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("available_range", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.PropertyRangeHelper.id(),"PropertyRange",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("low_val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null),new org.omg.CORBA.StructMember("high_val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotification.UnsupportedAdmin s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotification.UnsupportedAdmin extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/CosNotification/UnsupportedAdmin:1.0";
	}
	public static org.omg.CosNotification.UnsupportedAdmin read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosNotification.PropertyError[] x0;
		x0 = org.omg.CosNotification.PropertyErrorSeqHelper.read(in);
		final org.omg.CosNotification.UnsupportedAdmin result = new org.omg.CosNotification.UnsupportedAdmin(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNotification.UnsupportedAdmin s)
	{
		out.write_string(id());
		org.omg.CosNotification.PropertyErrorSeqHelper.write(out,s.admin_err);
	}
}
