package org.omg.BiDirPolicy;


/**
 * Generated from IDL interface "BidirectionalPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class BidirectionalPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(BidirectionalPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/BiDirPolicy/BidirectionalPolicy:1.0", "BidirectionalPolicy");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.BiDirPolicy.BidirectionalPolicy s)
	{
			any.insert_Object(s);
	}
	public static org.omg.BiDirPolicy.BidirectionalPolicy extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/BiDirPolicy/BidirectionalPolicy:1.0";
	}
	public static BidirectionalPolicy read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.BiDirPolicy._BidirectionalPolicyStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.BiDirPolicy.BidirectionalPolicy s)
	{
		_out.write_Object(s);
	}
	public static org.omg.BiDirPolicy.BidirectionalPolicy narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.BiDirPolicy.BidirectionalPolicy)
		{
			return (org.omg.BiDirPolicy.BidirectionalPolicy)obj;
		}
		else if (obj._is_a("IDL:omg.org/BiDirPolicy/BidirectionalPolicy:1.0"))
		{
			org.omg.BiDirPolicy._BidirectionalPolicyStub stub;
			stub = new org.omg.BiDirPolicy._BidirectionalPolicyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.BiDirPolicy.BidirectionalPolicy unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.BiDirPolicy.BidirectionalPolicy)
		{
			return (org.omg.BiDirPolicy.BidirectionalPolicy)obj;
		}
		else
		{
			org.omg.BiDirPolicy._BidirectionalPolicyStub stub;
			stub = new org.omg.BiDirPolicy._BidirectionalPolicyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
