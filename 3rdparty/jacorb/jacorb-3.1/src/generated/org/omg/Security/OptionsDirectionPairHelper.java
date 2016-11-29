package org.omg.Security;


/**
 * Generated from IDL struct "OptionsDirectionPair".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class OptionsDirectionPairHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(OptionsDirectionPairHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.OptionsDirectionPairHelper.id(),"OptionsDirectionPair",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("options", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.AssociationOptionsHelper.id(), "AssociationOptions",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4))), null),new org.omg.CORBA.StructMember("direction", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.Security.CommunicationDirectionHelper.id(),"CommunicationDirection",new String[]{"SecDirectionBoth","SecDirectionRequest","SecDirectionReply"}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.OptionsDirectionPair s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.OptionsDirectionPair extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/OptionsDirectionPair:1.0";
	}
	public static org.omg.Security.OptionsDirectionPair read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.OptionsDirectionPair result = new org.omg.Security.OptionsDirectionPair();
		result.options=in.read_ushort();
		result.direction=org.omg.Security.CommunicationDirectionHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.OptionsDirectionPair s)
	{
		out.write_ushort(s.options);
		org.omg.Security.CommunicationDirectionHelper.write(out,s.direction);
	}
}
