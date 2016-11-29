package org.omg.PortableGroup;


/**
 * Generated from IDL interface "GenericFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class GenericFactoryHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(GenericFactoryHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/PortableGroup/GenericFactory:1.0", "GenericFactory");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.PortableGroup.GenericFactory s)
	{
			any.insert_Object(s);
	}
	public static org.omg.PortableGroup.GenericFactory extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/PortableGroup/GenericFactory:1.0";
	}
	public static GenericFactory read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.PortableGroup._GenericFactoryStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.PortableGroup.GenericFactory s)
	{
		_out.write_Object(s);
	}
	public static org.omg.PortableGroup.GenericFactory narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableGroup.GenericFactory)
		{
			return (org.omg.PortableGroup.GenericFactory)obj;
		}
		else if (obj._is_a("IDL:omg.org/PortableGroup/GenericFactory:1.0"))
		{
			org.omg.PortableGroup._GenericFactoryStub stub;
			stub = new org.omg.PortableGroup._GenericFactoryStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.PortableGroup.GenericFactory unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.PortableGroup.GenericFactory)
		{
			return (org.omg.PortableGroup.GenericFactory)obj;
		}
		else
		{
			org.omg.PortableGroup._GenericFactoryStub stub;
			stub = new org.omg.PortableGroup._GenericFactoryStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
