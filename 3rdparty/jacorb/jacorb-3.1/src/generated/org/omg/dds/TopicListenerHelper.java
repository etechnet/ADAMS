package org.omg.dds;


/**
 * Generated from IDL interface "TopicListener".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TopicListenerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TopicListenerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/dds/TopicListener:1.0", "TopicListener");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.TopicListener s)
	{
			any.insert_Object(s);
	}
	public static org.omg.dds.TopicListener extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/dds/TopicListener:1.0";
	}
	public static TopicListener read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.dds._TopicListenerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.dds.TopicListener s)
	{
		_out.write_Object(s);
	}
	public static org.omg.dds.TopicListener narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.TopicListener)
		{
			return (org.omg.dds.TopicListener)obj;
		}
		else if (obj._is_a("IDL:omg.org/dds/TopicListener:1.0"))
		{
			org.omg.dds._TopicListenerStub stub;
			stub = new org.omg.dds._TopicListenerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.dds.TopicListener unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.TopicListener)
		{
			return (org.omg.dds.TopicListener)obj;
		}
		else
		{
			org.omg.dds._TopicListenerStub stub;
			stub = new org.omg.dds._TopicListenerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
