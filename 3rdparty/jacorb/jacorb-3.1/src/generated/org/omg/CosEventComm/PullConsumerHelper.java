package org.omg.CosEventComm;


/**
 * Generated from IDL interface "PullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PullConsumerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PullConsumerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosEventComm/PullConsumer:1.0", "PullConsumer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosEventComm.PullConsumer s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosEventComm.PullConsumer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosEventComm/PullConsumer:1.0";
	}
	public static PullConsumer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosEventComm._PullConsumerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosEventComm.PullConsumer s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosEventComm.PullConsumer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosEventComm.PullConsumer)
		{
			return (org.omg.CosEventComm.PullConsumer)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosEventComm/PullConsumer:1.0"))
		{
			org.omg.CosEventComm._PullConsumerStub stub;
			stub = new org.omg.CosEventComm._PullConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosEventComm.PullConsumer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosEventComm.PullConsumer)
		{
			return (org.omg.CosEventComm.PullConsumer)obj;
		}
		else
		{
			org.omg.CosEventComm._PullConsumerStub stub;
			stub = new org.omg.CosEventComm._PullConsumerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
