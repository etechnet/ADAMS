package org.jacorb.imr.AdminPackage;


/**
 * Generated from IDL exception "FileOpFailed".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class FileOpFailedHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(FileOpFailedHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.jacorb.imr.AdminPackage.FileOpFailedHelper.id(),"FileOpFailed",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.AdminPackage.FileOpFailed s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.imr.AdminPackage.FileOpFailed extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/imr/Admin/FileOpFailed:1.0";
	}
	public static org.jacorb.imr.AdminPackage.FileOpFailed read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final org.jacorb.imr.AdminPackage.FileOpFailed result = new org.jacorb.imr.AdminPackage.FileOpFailed(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.imr.AdminPackage.FileOpFailed s)
	{
		out.write_string(id());
	}
}
