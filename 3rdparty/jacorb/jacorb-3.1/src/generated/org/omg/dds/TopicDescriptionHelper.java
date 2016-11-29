package org.omg.dds;


/**
 * Generated from IDL interface "TopicDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TopicDescriptionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TopicDescriptionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/dds/TopicDescription:1.0", "TopicDescription");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.TopicDescription s)
	{
			any.insert_Object(s);
	}
	public static org.omg.dds.TopicDescription extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/dds/TopicDescription:1.0";
	}
	public static TopicDescription read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.dds._TopicDescriptionStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.dds.TopicDescription s)
	{
		_out.write_Object(s);
	}
	public static org.omg.dds.TopicDescription narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.TopicDescription)
		{
			return (org.omg.dds.TopicDescription)obj;
		}
		else if (obj._is_a("IDL:omg.org/dds/TopicDescription:1.0"))
		{
			org.omg.dds._TopicDescriptionStub stub;
			stub = new org.omg.dds._TopicDescriptionStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.dds.TopicDescription unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.TopicDescription)
		{
			return (org.omg.dds.TopicDescription)obj;
		}
		else
		{
			org.omg.dds._TopicDescriptionStub stub;
			stub = new org.omg.dds._TopicDescriptionStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
