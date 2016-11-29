package org.omg.ATLAS;


/**
 * Generated from IDL struct "AuthTokenData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthTokenDataHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AuthTokenDataHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.ATLAS.AuthTokenDataHelper.id(),"AuthTokenData",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("ident_token", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.ATLAS.IdTokenOptionHelper.id(), "IdTokenOption",org.omg.CORBA.ORB.init().create_sequence_tc(1, org.omg.CSI.IdentityTokenHelper.type())), null),new org.omg.CORBA.StructMember("auth_token", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationTokenHelper.id(), "AuthorizationToken",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CSI.AuthorizationElementHelper.id(),"AuthorizationElement",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("the_type", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationElementTypeHelper.id(), "AuthorizationElementType",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("the_element", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CSI.AuthorizationElementContentsHelper.id(), "AuthorizationElementContents",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)}))), null),new org.omg.CORBA.StructMember("expiry_time", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.ATLAS.ExpiryTimeHelper.id(), "ExpiryTime",org.omg.CORBA.ORB.init().create_sequence_tc(1, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.TimeBase.UtcTHelper.id(),"UtcT",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("time", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TimeTHelper.id(), "TimeT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(24))), null),new org.omg.CORBA.StructMember("inacclo", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("inacchi", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("tdf", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.TimeBase.TdfTHelper.id(), "TdfT",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.ATLAS.AuthTokenData s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.ATLAS.AuthTokenData extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/ATLAS/AuthTokenData:1.0";
	}
	public static org.omg.ATLAS.AuthTokenData read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.ATLAS.AuthTokenData result = new org.omg.ATLAS.AuthTokenData();
		result.ident_token = org.omg.ATLAS.IdTokenOptionHelper.read(in);
		result.auth_token = org.omg.CSI.AuthorizationTokenHelper.read(in);
		result.expiry_time = org.omg.ATLAS.ExpiryTimeHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.ATLAS.AuthTokenData s)
	{
		org.omg.ATLAS.IdTokenOptionHelper.write(out,s.ident_token);
		org.omg.CSI.AuthorizationTokenHelper.write(out,s.auth_token);
		org.omg.ATLAS.ExpiryTimeHelper.write(out,s.expiry_time);
	}
}
