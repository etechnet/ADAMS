package org.omg.CosNotifyCommAck;


/**
 * Generated from IDL interface "StructuredPushSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class StructuredPushSupplierAckHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(StructuredPushSupplierAckHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosNotifyCommAck/StructuredPushSupplierAck:1.0", "StructuredPushSupplierAck");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosNotifyCommAck.StructuredPushSupplierAck s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosNotifyCommAck.StructuredPushSupplierAck extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosNotifyCommAck/StructuredPushSupplierAck:1.0";
	}
	public static StructuredPushSupplierAck read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosNotifyCommAck._StructuredPushSupplierAckStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosNotifyCommAck.StructuredPushSupplierAck s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosNotifyCommAck.StructuredPushSupplierAck narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyCommAck.StructuredPushSupplierAck)
		{
			return (org.omg.CosNotifyCommAck.StructuredPushSupplierAck)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosNotifyCommAck/StructuredPushSupplierAck:1.0"))
		{
			org.omg.CosNotifyCommAck._StructuredPushSupplierAckStub stub;
			stub = new org.omg.CosNotifyCommAck._StructuredPushSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosNotifyCommAck.StructuredPushSupplierAck unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosNotifyCommAck.StructuredPushSupplierAck)
		{
			return (org.omg.CosNotifyCommAck.StructuredPushSupplierAck)obj;
		}
		else
		{
			org.omg.CosNotifyCommAck._StructuredPushSupplierAckStub stub;
			stub = new org.omg.CosNotifyCommAck._StructuredPushSupplierAckStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
