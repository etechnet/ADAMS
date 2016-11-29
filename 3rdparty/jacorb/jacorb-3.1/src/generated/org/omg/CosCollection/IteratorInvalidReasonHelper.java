package org.omg.CosCollection;
/**
 * Generated from IDL enum "IteratorInvalidReason".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IteratorInvalidReasonHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IteratorInvalidReasonHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosCollection.IteratorInvalidReasonHelper.id(),"IteratorInvalidReason",new String[]{"is_invalid","is_not_for_collection","is_const"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosCollection.IteratorInvalidReason s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosCollection.IteratorInvalidReason extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosCollection/IteratorInvalidReason:1.0";
	}
	public static IteratorInvalidReason read (final org.omg.CORBA.portable.InputStream in)
	{
		return IteratorInvalidReason.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final IteratorInvalidReason s)
	{
		out.write_long(s.value());
	}
}
