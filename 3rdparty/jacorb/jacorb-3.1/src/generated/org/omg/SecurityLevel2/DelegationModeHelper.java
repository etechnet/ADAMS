package org.omg.SecurityLevel2;
/**
 * Generated from IDL enum "DelegationMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class DelegationModeHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DelegationModeHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.SecurityLevel2.DelegationModeHelper.id(),"DelegationMode",new String[]{"Delegate","NoDelegate"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.SecurityLevel2.DelegationMode s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.SecurityLevel2.DelegationMode extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/SecurityLevel2/DelegationMode:1.0";
	}
	public static DelegationMode read (final org.omg.CORBA.portable.InputStream in)
	{
		return DelegationMode.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final DelegationMode s)
	{
		out.write_long(s.value());
	}
}
