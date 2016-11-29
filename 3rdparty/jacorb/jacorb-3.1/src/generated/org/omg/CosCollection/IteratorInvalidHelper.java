package org.omg.CosCollection;


/**
 * Generated from IDL exception "IteratorInvalid".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IteratorInvalidHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IteratorInvalidHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosCollection.IteratorInvalidHelper.id(),"IteratorInvalid",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("why", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosCollection.IteratorInvalidReasonHelper.id(),"IteratorInvalidReason",new String[]{"is_invalid","is_not_for_collection","is_const"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosCollection.IteratorInvalid s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosCollection.IteratorInvalid extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosCollection/IteratorInvalid:1.0";
	}
	public static org.omg.CosCollection.IteratorInvalid read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosCollection.IteratorInvalidReason x0;
		x0=org.omg.CosCollection.IteratorInvalidReasonHelper.read(in);
		final org.omg.CosCollection.IteratorInvalid result = new org.omg.CosCollection.IteratorInvalid(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosCollection.IteratorInvalid s)
	{
		out.write_string(id());
		org.omg.CosCollection.IteratorInvalidReasonHelper.write(out,s.why);
	}
}
