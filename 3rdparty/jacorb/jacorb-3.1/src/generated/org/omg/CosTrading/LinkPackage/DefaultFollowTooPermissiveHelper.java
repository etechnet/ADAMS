package org.omg.CosTrading.LinkPackage;


/**
 * Generated from IDL exception "DefaultFollowTooPermissive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class DefaultFollowTooPermissiveHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DefaultFollowTooPermissiveHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosTrading.LinkPackage.DefaultFollowTooPermissiveHelper.id(),"DefaultFollowTooPermissive",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("def_pass_on_follow_rule", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosTrading.FollowOptionHelper.id(),"FollowOption",new String[]{"local_only","if_no_local","always"}), null),new org.omg.CORBA.StructMember("limiting_follow_rule", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosTrading.FollowOptionHelper.id(),"FollowOption",new String[]{"local_only","if_no_local","always"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.LinkPackage.DefaultFollowTooPermissive s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.LinkPackage.DefaultFollowTooPermissive extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/Link/DefaultFollowTooPermissive:1.0";
	}
	public static org.omg.CosTrading.LinkPackage.DefaultFollowTooPermissive read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosTrading.FollowOption x0;
		x0=org.omg.CosTrading.FollowOptionHelper.read(in);
		org.omg.CosTrading.FollowOption x1;
		x1=org.omg.CosTrading.FollowOptionHelper.read(in);
		final org.omg.CosTrading.LinkPackage.DefaultFollowTooPermissive result = new org.omg.CosTrading.LinkPackage.DefaultFollowTooPermissive(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.LinkPackage.DefaultFollowTooPermissive s)
	{
		out.write_string(id());
		org.omg.CosTrading.FollowOptionHelper.write(out,s.def_pass_on_follow_rule);
		org.omg.CosTrading.FollowOptionHelper.write(out,s.limiting_follow_rule);
	}
}
