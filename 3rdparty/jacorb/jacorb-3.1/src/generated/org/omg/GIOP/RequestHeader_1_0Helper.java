package org.omg.GIOP;


/**
 * Generated from IDL struct "RequestHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RequestHeader_1_0Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RequestHeader_1_0Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.RequestHeader_1_0Helper.id(),"RequestHeader_1_0",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("service_context", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ServiceContextListHelper.id(), "ServiceContextList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.ServiceContextHelper.id(),"ServiceContext",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("context_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ServiceIdHelper.id(), "ServiceId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("context_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}))), null),new org.omg.CORBA.StructMember("request_id", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("response_expected", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("object_key", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null),new org.omg.CORBA.StructMember("operation", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("requesting_principal", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.GIOP.PrincipalHelper.id(), "Principal",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GIOP.RequestHeader_1_0 s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GIOP.RequestHeader_1_0 extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GIOP/RequestHeader_1_0:1.0";
	}
	public static org.omg.GIOP.RequestHeader_1_0 read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.GIOP.RequestHeader_1_0 result = new org.omg.GIOP.RequestHeader_1_0();
		result.service_context = org.omg.IOP.ServiceContextListHelper.read(in);
		result.request_id=in.read_ulong();
		result.response_expected=in.read_boolean();
		int _lresult_object_key51 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_object_key51 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_object_key51);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.object_key = new byte[_lresult_object_key51];
		in.read_octet_array(result.object_key,0,_lresult_object_key51);
		result.operation=in.read_string();
		result.requesting_principal = org.omg.GIOP.PrincipalHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.GIOP.RequestHeader_1_0 s)
	{
		org.omg.IOP.ServiceContextListHelper.write(out,s.service_context);
		out.write_ulong(s.request_id);
		out.write_boolean(s.response_expected);
		
		out.write_long(s.object_key.length);
		out.write_octet_array(s.object_key,0,s.object_key.length);
		java.lang.String tmpResult981 = s.operation;
out.write_string( tmpResult981 );
		org.omg.GIOP.PrincipalHelper.write(out,s.requesting_principal);
	}
}
