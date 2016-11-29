package org.omg.CSIIOP;


/**
 * Generated from IDL struct "AS_ContextSec".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AS_ContextSecHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AS_ContextSecHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSIIOP.AS_ContextSecHelper.id(),"AS_ContextSec",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("target_supports", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("target_requires", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSIIOP.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("client_authentication_mech", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.OIDHelper.id(), "OID",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null),new org.omg.CORBA.StructMember("target_name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.GSS_NT_ExportedNameHelper.id(), "GSS_NT_ExportedName",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CSIIOP.AS_ContextSec s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CSIIOP.AS_ContextSec extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CSIIOP/AS_ContextSec:1.0";
	}
	public static org.omg.CSIIOP.AS_ContextSec read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CSIIOP.AS_ContextSec result = new org.omg.CSIIOP.AS_ContextSec();
		result.target_supports=in.read_ushort();
		result.target_requires=in.read_ushort();
		result.client_authentication_mech = org.omg.CSI.OIDHelper.read(in);
		result.target_name = org.omg.CSI.GSS_NT_ExportedNameHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CSIIOP.AS_ContextSec s)
	{
		out.write_ushort(s.target_supports);
		out.write_ushort(s.target_requires);
		org.omg.CSI.OIDHelper.write(out,s.client_authentication_mech);
		org.omg.CSI.GSS_NT_ExportedNameHelper.write(out,s.target_name);
	}
}
