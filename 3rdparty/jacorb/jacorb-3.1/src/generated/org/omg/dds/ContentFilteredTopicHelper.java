package org.omg.dds;


/**
 * Generated from IDL interface "ContentFilteredTopic".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ContentFilteredTopicHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ContentFilteredTopicHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/dds/ContentFilteredTopic:1.0", "ContentFilteredTopic");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.ContentFilteredTopic s)
	{
			any.insert_Object(s);
	}
	public static org.omg.dds.ContentFilteredTopic extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/dds/ContentFilteredTopic:1.0";
	}
	public static ContentFilteredTopic read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.dds._ContentFilteredTopicStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.dds.ContentFilteredTopic s)
	{
		_out.write_Object(s);
	}
	public static org.omg.dds.ContentFilteredTopic narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.ContentFilteredTopic)
		{
			return (org.omg.dds.ContentFilteredTopic)obj;
		}
		else if (obj._is_a("IDL:omg.org/dds/ContentFilteredTopic:1.0"))
		{
			org.omg.dds._ContentFilteredTopicStub stub;
			stub = new org.omg.dds._ContentFilteredTopicStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.dds.ContentFilteredTopic unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.ContentFilteredTopic)
		{
			return (org.omg.dds.ContentFilteredTopic)obj;
		}
		else
		{
			org.omg.dds._ContentFilteredTopicStub stub;
			stub = new org.omg.dds._ContentFilteredTopicStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
