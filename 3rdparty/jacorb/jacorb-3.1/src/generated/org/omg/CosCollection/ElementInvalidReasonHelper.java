package org.omg.CosCollection;
/**
 * Generated from IDL enum "ElementInvalidReason".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ElementInvalidReasonHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ElementInvalidReasonHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosCollection.ElementInvalidReasonHelper.id(),"ElementInvalidReason",new String[]{"element_type_invalid","positioning_property_invalid","element_exists"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosCollection.ElementInvalidReason s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosCollection.ElementInvalidReason extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosCollection/ElementInvalidReason:1.0";
	}
	public static ElementInvalidReason read (final org.omg.CORBA.portable.InputStream in)
	{
		return ElementInvalidReason.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final ElementInvalidReason s)
	{
		out.write_long(s.value());
	}
}
