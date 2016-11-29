package org.omg.CosTypedEventComm;


/**
 * Generated from IDL interface "TypedPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class TypedPushConsumerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TypedPushConsumerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTypedEventComm/TypedPushConsumer:1.0", "TypedPushConsumer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTypedEventComm.TypedPushConsumer s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTypedEventComm.TypedPushConsumer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTypedEventComm/TypedPushConsumer:1.0";
	}
	public static TypedPushConsumer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTypedEventComm._TypedPushConsumerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTypedEventComm.TypedPushConsumer s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTypedEventComm.TypedPushConsumer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedEventComm.TypedPushConsumer)
		{
			return (org.omg.CosTypedEventComm.TypedPushConsumer)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTypedEventComm/TypedPushConsumer:1.0"))
		{
			org.omg.CosTypedEventComm._TypedPushConsumerStub stub;
			stub = new org.omg.CosTypedEventComm._TypedPushConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTypedEventComm.TypedPushConsumer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTypedEventComm.TypedPushConsumer)
		{
			return (org.omg.CosTypedEventComm.TypedPushConsumer)obj;
		}
		else
		{
			org.omg.CosTypedEventComm._TypedPushConsumerStub stub;
			stub = new org.omg.CosTypedEventComm._TypedPushConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
