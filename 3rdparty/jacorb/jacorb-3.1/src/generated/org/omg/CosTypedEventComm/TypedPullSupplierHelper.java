package org.omg.CosTypedEventComm;


/**
 * Generated from IDL interface "TypedPullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class TypedPullSupplierHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TypedPullSupplierHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTypedEventComm/TypedPullSupplier:1.0", "TypedPullSupplier");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTypedEventComm.TypedPullSupplier s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTypedEventComm.TypedPullSupplier extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTypedEventComm/TypedPullSupplier:1.0";
	}
	public static TypedPullSupplier read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTypedEventComm._TypedPullSupplierStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTypedEventComm.TypedPullSupplier s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTypedEventComm.TypedPullSupplier narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedEventComm.TypedPullSupplier)
		{
			return (org.omg.CosTypedEventComm.TypedPullSupplier)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTypedEventComm/TypedPullSupplier:1.0"))
		{
			org.omg.CosTypedEventComm._TypedPullSupplierStub stub;
			stub = new org.omg.CosTypedEventComm._TypedPullSupplierStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTypedEventComm.TypedPullSupplier unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedEventComm.TypedPullSupplier)
		{
			return (org.omg.CosTypedEventComm.TypedPullSupplier)obj;
		}
		else
		{
			org.omg.CosTypedEventComm._TypedPullSupplierStub stub;
			stub = new org.omg.CosTypedEventComm._TypedPullSupplierStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
