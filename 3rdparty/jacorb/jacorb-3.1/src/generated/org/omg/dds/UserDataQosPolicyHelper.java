package org.omg.dds;


/**
 * Generated from IDL struct "UserDataQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class UserDataQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(UserDataQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.UserDataQosPolicyHelper.id(),"UserDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.UserDataQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.UserDataQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/UserDataQosPolicy:1.0";
	}
	public static org.omg.dds.UserDataQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.UserDataQosPolicy result = new org.omg.dds.UserDataQosPolicy();
		int _lresult_value111 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_value111 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_value111);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.value = new byte[_lresult_value111];
		in.read_octet_array(result.value,0,_lresult_value111);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.UserDataQosPolicy s)
	{
		
		out.write_long(s.value.length);
		out.write_octet_array(s.value,0,s.value.length);
	}
}
