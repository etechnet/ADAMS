package org.omg.GIOP;

/**
 * Generated from IDL union "TargetAddress".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class TargetAddressHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TargetAddressHelper.class)
			{
				if (_type == null)
				{
			org.omg.CORBA.UnionMember[] members = new org.omg.CORBA.UnionMember[3];
			org.omg.CORBA.Any label_any;
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_short ((short)(0));
			members[0] = new org.omg.CORBA.UnionMember ("object_key", label_any, org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_short ((short)(1));
			members[1] = new org.omg.CORBA.UnionMember ("profile", label_any, org.omg.IOP.TaggedProfileHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_short ((short)(2));
			members[2] = new org.omg.CORBA.UnionMember ("ior", label_any, org.omg.GIOP.IORAddressingInfoHelper.type(),null);
			 _type = org.omg.CORBA.ORB.init().create_union_tc(id(),"TargetAddress",org.omg.CORBA.ORB.init().create_alias_tc(org.omg.GIOP.AddressingDispositionHelper.id(), "AddressingDisposition",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), members);
				}
			}
		}
			return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GIOP.TargetAddress s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GIOP.TargetAddress extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GIOP/TargetAddress:1.0";
	}
	public static TargetAddress read (org.omg.CORBA.portable.InputStream in)
	{
		TargetAddress result = new TargetAddress();
		short disc=in.read_short();
		switch (disc)
		{
			case 0:
			{
				byte[] _var;
				int _l_var53 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _l_var53 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_var53);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_var = new byte[_l_var53];
		in.read_octet_array(_var,0,_l_var53);
				result.object_key (_var);
				break;
			}
			case 1:
			{
				org.omg.IOP.TaggedProfile _var;
				_var=org.omg.IOP.TaggedProfileHelper.read(in);
				result.profile (_var);
				break;
			}
			case 2:
			{
				org.omg.GIOP.IORAddressingInfo _var;
				_var=org.omg.GIOP.IORAddressingInfoHelper.read(in);
				result.ior (_var);
				break;
			}
		}
		return result;
	}
	public static void write (org.omg.CORBA.portable.OutputStream out, TargetAddress s)
	{
		out.write_short(s.discriminator ());
		switch (s.discriminator ())
		{
			case 0:
			{
				
		out.write_long(s.object_key ().length);
		out.write_octet_array(s.object_key (),0,s.object_key ().length);
				break;
			}
			case 1:
			{
				org.omg.IOP.TaggedProfileHelper.write(out,s.profile ());
				break;
			}
			case 2:
			{
				org.omg.GIOP.IORAddressingInfoHelper.write(out,s.ior ());
				break;
			}
		}
	}
}
