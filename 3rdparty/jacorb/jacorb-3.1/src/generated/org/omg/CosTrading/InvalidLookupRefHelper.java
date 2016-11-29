package org.omg.CosTrading;


/**
 * Generated from IDL exception "InvalidLookupRef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InvalidLookupRefHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidLookupRefHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosTrading.InvalidLookupRefHelper.id(),"InvalidLookupRef",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("target", org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTrading/Lookup:1.0", "Lookup"), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.InvalidLookupRef s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.InvalidLookupRef extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/InvalidLookupRef:1.0";
	}
	public static org.omg.CosTrading.InvalidLookupRef read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosTrading.Lookup x0;
		x0=org.omg.CosTrading.LookupHelper.read(in);
		final org.omg.CosTrading.InvalidLookupRef result = new org.omg.CosTrading.InvalidLookupRef(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.InvalidLookupRef s)
	{
		out.write_string(id());
		org.omg.CosTrading.LookupHelper.write(out,s.target);
	}
}
