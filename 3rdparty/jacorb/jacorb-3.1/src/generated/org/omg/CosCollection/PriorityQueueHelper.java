package org.omg.CosCollection;


/**
 * Generated from IDL interface "PriorityQueue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PriorityQueueHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PriorityQueueHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosCollection/PriorityQueue:1.0", "PriorityQueue");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosCollection.PriorityQueue s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosCollection.PriorityQueue extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosCollection/PriorityQueue:1.0";
	}
	public static PriorityQueue read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosCollection._PriorityQueueStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosCollection.PriorityQueue s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosCollection.PriorityQueue narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosCollection.PriorityQueue)
		{
			return (org.omg.CosCollection.PriorityQueue)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosCollection/PriorityQueue:1.0"))
		{
			org.omg.CosCollection._PriorityQueueStub stub;
			stub = new org.omg.CosCollection._PriorityQueueStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosCollection.PriorityQueue unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosCollection.PriorityQueue)
		{
			return (org.omg.CosCollection.PriorityQueue)obj;
		}
		else
		{
			org.omg.CosCollection._PriorityQueueStub stub;
			stub = new org.omg.CosCollection._PriorityQueueStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
