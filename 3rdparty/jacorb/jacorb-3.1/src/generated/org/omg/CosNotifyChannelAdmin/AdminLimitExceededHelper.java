package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL exception "AdminLimitExceeded".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class AdminLimitExceededHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AdminLimitExceededHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.id(),"AdminLimitExceeded",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("admin_property_err", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotifyChannelAdmin.AdminLimitHelper.id(),"AdminLimit",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyChannelAdmin.AdminLimitExceeded s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotifyChannelAdmin.AdminLimitExceeded extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotifyChannelAdmin/AdminLimitExceeded:1.0";
	}
	public static org.omg.CosNotifyChannelAdmin.AdminLimitExceeded read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosNotifyChannelAdmin.AdminLimit x0;
		x0=org.omg.CosNotifyChannelAdmin.AdminLimitHelper.read(in);
		final org.omg.CosNotifyChannelAdmin.AdminLimitExceeded result = new org.omg.CosNotifyChannelAdmin.AdminLimitExceeded(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNotifyChannelAdmin.AdminLimitExceeded s)
	{
		out.write_string(id());
		org.omg.CosNotifyChannelAdmin.AdminLimitHelper.write(out,s.admin_property_err);
	}
}
