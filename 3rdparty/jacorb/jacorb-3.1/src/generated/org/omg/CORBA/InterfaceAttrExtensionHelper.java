package org.omg.CORBA;


/**
 * Generated from IDL interface "InterfaceAttrExtension".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InterfaceAttrExtensionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InterfaceAttrExtensionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CORBA/InterfaceAttrExtension:1.0", "InterfaceAttrExtension");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CORBA.InterfaceAttrExtension s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CORBA.InterfaceAttrExtension extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CORBA/InterfaceAttrExtension:1.0";
	}
	public static InterfaceAttrExtension read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CORBA._InterfaceAttrExtensionStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CORBA.InterfaceAttrExtension s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CORBA.InterfaceAttrExtension narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.InterfaceAttrExtension)
		{
			return (org.omg.CORBA.InterfaceAttrExtension)obj;
		}
		else if (obj._is_a("IDL:omg.org/CORBA/InterfaceAttrExtension:1.0"))
		{
			org.omg.CORBA._InterfaceAttrExtensionStub stub;
			stub = new org.omg.CORBA._InterfaceAttrExtensionStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CORBA.InterfaceAttrExtension unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CORBA.InterfaceAttrExtension)
		{
			return (org.omg.CORBA.InterfaceAttrExtension)obj;
		}
		else
		{
			org.omg.CORBA._InterfaceAttrExtensionStub stub;
			stub = new org.omg.CORBA._InterfaceAttrExtensionStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
