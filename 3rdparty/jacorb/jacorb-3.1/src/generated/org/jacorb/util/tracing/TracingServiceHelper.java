package org.jacorb.util.tracing;


/**
 * Generated from IDL interface "TracingService".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class TracingServiceHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TracingServiceHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/util/tracing/TracingService:1.0", "TracingService");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.util.tracing.TracingService s)
	{
			any.insert_Object(s);
	}
	public static org.jacorb.util.tracing.TracingService extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:org/jacorb/util/tracing/TracingService:1.0";
	}
	public static TracingService read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.jacorb.util.tracing._TracingServiceStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jacorb.util.tracing.TracingService s)
	{
		_out.write_Object(s);
	}
	public static org.jacorb.util.tracing.TracingService narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.util.tracing.TracingService)
		{
			return (org.jacorb.util.tracing.TracingService)obj;
		}
		else if (obj._is_a("IDL:org/jacorb/util/tracing/TracingService:1.0"))
		{
			org.jacorb.util.tracing._TracingServiceStub stub;
			stub = new org.jacorb.util.tracing._TracingServiceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jacorb.util.tracing.TracingService unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.util.tracing.TracingService)
		{
			return (org.jacorb.util.tracing.TracingService)obj;
		}
		else
		{
			org.jacorb.util.tracing._TracingServiceStub stub;
			stub = new org.jacorb.util.tracing._TracingServiceStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
