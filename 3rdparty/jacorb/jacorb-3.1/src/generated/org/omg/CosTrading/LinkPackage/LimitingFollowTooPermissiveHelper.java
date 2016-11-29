package org.omg.CosTrading.LinkPackage;


/**
 * Generated from IDL exception "LimitingFollowTooPermissive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class LimitingFollowTooPermissiveHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(LimitingFollowTooPermissiveHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissiveHelper.id(),"LimitingFollowTooPermissive",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("limiting_follow_rule", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosTrading.FollowOptionHelper.id(),"FollowOption",new String[]{"local_only","if_no_local","always"}), null),new org.omg.CORBA.StructMember("max_link_follow_policy", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.CosTrading.FollowOptionHelper.id(),"FollowOption",new String[]{"local_only","if_no_local","always"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CosTrading/Link/LimitingFollowTooPermissive:1.0";
	}
	public static org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		org.omg.CosTrading.FollowOption x0;
		x0=org.omg.CosTrading.FollowOptionHelper.read(in);
		org.omg.CosTrading.FollowOption x1;
		x1=org.omg.CosTrading.FollowOptionHelper.read(in);
		final org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive result = new org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive(id, x0, x1);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive s)
	{
		out.write_string(id());
		org.omg.CosTrading.FollowOptionHelper.write(out,s.limiting_follow_rule);
		org.omg.CosTrading.FollowOptionHelper.write(out,s.max_link_follow_policy);
	}
}
