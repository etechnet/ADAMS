package org.omg.ATLAS;


/**
 * Generated from IDL struct "ATLASProfile".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ATLASProfileHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ATLASProfileHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.ATLAS.ATLASProfileHelper.id(),"ATLASProfile",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_locator", org.omg.ATLAS.ATLASLocatorHelper.type(), null),new org.omg.CORBA.StructMember("the_cache_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.ATLAS.ATLASCacheIdHelper.id(), "ATLASCacheId",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.ATLAS.ATLASProfile s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.ATLAS.ATLASProfile extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/ATLAS/ATLASProfile:1.0";
	}
	public static org.omg.ATLAS.ATLASProfile read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.ATLAS.ATLASProfile result = new org.omg.ATLAS.ATLASProfile();
		result.the_locator=org.omg.ATLAS.ATLASLocatorHelper.read(in);
		result.the_cache_id = org.omg.ATLAS.ATLASCacheIdHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.ATLAS.ATLASProfile s)
	{
		org.omg.ATLAS.ATLASLocatorHelper.write(out,s.the_locator);
		org.omg.ATLAS.ATLASCacheIdHelper.write(out,s.the_cache_id);
	}
}
