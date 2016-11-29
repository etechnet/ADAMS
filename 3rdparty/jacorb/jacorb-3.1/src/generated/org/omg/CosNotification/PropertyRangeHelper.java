package org.omg.CosNotification;


/**
 * Generated from IDL struct "PropertyRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PropertyRangeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PropertyRangeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CosNotification.PropertyRangeHelper.id(),"PropertyRange",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("low_val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null),new org.omg.CORBA.StructMember("high_val", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosNotification.PropertyValueHelper.id(), "PropertyValue",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(11))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotification.PropertyRange s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosNotification.PropertyRange extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosNotification/PropertyRange:1.0";
	}
	public static org.omg.CosNotification.PropertyRange read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CosNotification.PropertyRange result = new org.omg.CosNotification.PropertyRange();
		result.low_val=in.read_any();
		result.high_val=in.read_any();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosNotification.PropertyRange s)
	{
		out.write_any(s.low_val);
		out.write_any(s.high_val);
	}
}
