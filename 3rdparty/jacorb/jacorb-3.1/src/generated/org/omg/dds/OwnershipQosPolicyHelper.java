package org.omg.dds;


/**
 * Generated from IDL struct "OwnershipQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OwnershipQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(OwnershipQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.OwnershipQosPolicyHelper.id(),"OwnershipQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.OwnershipQosPolicyKindHelper.id(),"OwnershipQosPolicyKind",new String[]{"SHARED_OWNERSHIP_QOS","EXCLUSIVE_OWNERSHIP_QOS"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.OwnershipQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.OwnershipQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/OwnershipQosPolicy:1.0";
	}
	public static org.omg.dds.OwnershipQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.OwnershipQosPolicy result = new org.omg.dds.OwnershipQosPolicy();
		result.kind=org.omg.dds.OwnershipQosPolicyKindHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.OwnershipQosPolicy s)
	{
		org.omg.dds.OwnershipQosPolicyKindHelper.write(out,s.kind);
	}
}
