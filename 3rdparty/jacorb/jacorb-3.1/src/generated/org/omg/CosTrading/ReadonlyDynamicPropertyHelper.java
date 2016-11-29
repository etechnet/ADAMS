package org.omg.CosTrading;


/**
 * Generated from IDL exception "ReadonlyDynamicProperty".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ReadonlyDynamicPropertyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ReadonlyDynamicPropertyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosTrading.ReadonlyDynamicPropertyHelper.id(),"ReadonlyDynamicProperty",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.ServiceTypeNameHelper.id(), "ServiceTypeName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.PropertyNameHelper.id(), "PropertyName",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.IstringHelper.id(), "Istring",org.omg.CORBA.ORB.init().create_string_tc(0))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.ReadonlyDynamicProperty s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.ReadonlyDynamicProperty extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/ReadonlyDynamicProperty:1.0";
	}
	public static org.omg.CosTrading.ReadonlyDynamicProperty read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		java.lang.String x0;
		x0=in.read_string();
		java.lang.String x1;
		x1=in.read_string();
		final org.omg.CosTrading.ReadonlyDynamicProperty result = new org.omg.CosTrading.ReadonlyDynamicProperty(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.ReadonlyDynamicProperty s)
	{
		out.write_string(id());
		java.lang.String tmpResult1048 = s.type;
out.write_string( tmpResult1048 );
		java.lang.String tmpResult1049 = s.name;
out.write_string( tmpResult1049 );
	}
}
