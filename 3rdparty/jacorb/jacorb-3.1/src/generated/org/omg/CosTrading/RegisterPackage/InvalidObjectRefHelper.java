package org.omg.CosTrading.RegisterPackage;


/**
 * Generated from IDL exception "InvalidObjectRef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InvalidObjectRefHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidObjectRefHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosTrading.RegisterPackage.InvalidObjectRefHelper.id(),"InvalidObjectRef",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("ref", org.omg.CORBA.ORB.init().get_primitive_tc( org.omg.CORBA.TCKind.tk_objref), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.RegisterPackage.InvalidObjectRef s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.RegisterPackage.InvalidObjectRef extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/Register/InvalidObjectRef:1.0";
	}
	public static org.omg.CosTrading.RegisterPackage.InvalidObjectRef read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CORBA.Object x0;
		x0=in.read_Object();
		final org.omg.CosTrading.RegisterPackage.InvalidObjectRef result = new org.omg.CosTrading.RegisterPackage.InvalidObjectRef(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.RegisterPackage.InvalidObjectRef s)
	{
		out.write_string(id());
		out.write_Object(s.ref);
	}
}
