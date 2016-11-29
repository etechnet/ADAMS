package org.omg.CosNotifyCommAck;


/**
 * Generated from IDL interface "StructuredPullSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class StructuredPullSupplierAckHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(StructuredPullSupplierAckHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyCommAck/StructuredPullSupplierAck:1.0", "StructuredPullSupplierAck");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyCommAck.StructuredPullSupplierAck s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyCommAck.StructuredPullSupplierAck extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyCommAck/StructuredPullSupplierAck:1.0";
	}
	public static StructuredPullSupplierAck read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyCommAck._StructuredPullSupplierAckStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyCommAck.StructuredPullSupplierAck s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyCommAck.StructuredPullSupplierAck narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyCommAck.StructuredPullSupplierAck)
		{
			return (org.omg.CosNotifyCommAck.StructuredPullSupplierAck)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyCommAck/StructuredPullSupplierAck:1.0"))
		{
			org.omg.CosNotifyCommAck._StructuredPullSupplierAckStub stub;
			stub = new org.omg.CosNotifyCommAck._StructuredPullSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyCommAck.StructuredPullSupplierAck unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyCommAck.StructuredPullSupplierAck)
		{
			return (org.omg.CosNotifyCommAck.StructuredPullSupplierAck)obj;
		}
		else
		{
			org.omg.CosNotifyCommAck._StructuredPullSupplierAckStub stub;
			stub = new org.omg.CosNotifyCommAck._StructuredPullSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
