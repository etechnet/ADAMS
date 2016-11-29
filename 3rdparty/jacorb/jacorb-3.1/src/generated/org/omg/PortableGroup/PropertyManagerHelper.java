package org.omg.PortableGroup;


/**
 * Generated from IDL interface "PropertyManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PropertyManagerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PropertyManagerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/PortableGroup/PropertyManager:1.0", "PropertyManager");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableGroup.PropertyManager s)
	{
			any.insert_Object(s);
	}
	public static org.omg.PortableGroup.PropertyManager extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/PortableGroup/PropertyManager:1.0";
	}
	public static PropertyManager read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.PortableGroup._PropertyManagerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.PortableGroup.PropertyManager s)
	{
		_out.write_Object(s);
	}
	public static org.omg.PortableGroup.PropertyManager narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableGroup.PropertyManager)
		{
			return (org.omg.PortableGroup.PropertyManager)obj;
		}
		else if (obj._is_a("IDL:omg.org/PortableGroup/PropertyManager:1.0"))
		{
			org.omg.PortableGroup._PropertyManagerStub stub;
			stub = new org.omg.PortableGroup._PropertyManagerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.PortableGroup.PropertyManager unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableGroup.PropertyManager)
		{
			return (org.omg.PortableGroup.PropertyManager)obj;
		}
		else
		{
			org.omg.PortableGroup._PropertyManagerStub stub;
			stub = new org.omg.PortableGroup._PropertyManagerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
