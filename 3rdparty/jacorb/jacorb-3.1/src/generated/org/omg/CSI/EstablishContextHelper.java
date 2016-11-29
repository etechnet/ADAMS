package org.omg.CSI;


/**
 * Generated from IDL struct "EstablishContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class EstablishContextHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(EstablishContextHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSI.EstablishContextHelper.id(),"EstablishContext",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("client_context_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.ContextIdHelper.id(), "ContextId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24))), null),new org.omg.CORBA.StructMember("authorization_token", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationTokenHelper.id(), "AuthorizationToken",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSI.AuthorizationElementHelper.id(),"AuthorizationElement",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationElementTypeHelper.id(), "AuthorizationElementType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("the_element", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationElementContentsHelper.id(), "AuthorizationElementContents",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)}))), null),new org.omg.CORBA.StructMember("identity_token", org.omg.CSI.IdentityTokenHelper.type(), null),new org.omg.CORBA.StructMember("client_authentication_token", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.GSSTokenHelper.id(), "GSSToken",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CSI.EstablishContext s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CSI.EstablishContext extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CSI/EstablishContext:1.0";
	}
	public static org.omg.CSI.EstablishContext read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CSI.EstablishContext result = new org.omg.CSI.EstablishContext();
		result.client_context_id=in.read_ulonglong();
		result.authorization_token = org.omg.CSI.AuthorizationTokenHelper.read(in);
		result.identity_token=org.omg.CSI.IdentityTokenHelper.read(in);
		result.client_authentication_token = org.omg.CSI.GSSTokenHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CSI.EstablishContext s)
	{
		out.write_ulonglong(s.client_context_id);
		org.omg.CSI.AuthorizationTokenHelper.write(out,s.authorization_token);
		org.omg.CSI.IdentityTokenHelper.write(out,s.identity_token);
		org.omg.CSI.GSSTokenHelper.write(out,s.client_authentication_token);
	}
}
