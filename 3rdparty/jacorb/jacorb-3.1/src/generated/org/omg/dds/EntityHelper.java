package org.omg.dds;


/**
 * Generated from IDL interface "Entity".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class EntityHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(EntityHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/dds/Entity:1.0", "Entity");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.Entity s)
	{
			any.insert_Object(s);
	}
	public static org.omg.dds.Entity extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/dds/Entity:1.0";
	}
	public static Entity read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.dds._EntityStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.dds.Entity s)
	{
		_out.write_Object(s);
	}
	public static org.omg.dds.Entity narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.Entity)
		{
			return (org.omg.dds.Entity)obj;
		}
		else if (obj._is_a("IDL:omg.org/dds/Entity:1.0"))
		{
			org.omg.dds._EntityStub stub;
			stub = new org.omg.dds._EntityStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.dds.Entity unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.dds.Entity)
		{
			return (org.omg.dds.Entity)obj;
		}
		else
		{
			org.omg.dds._EntityStub stub;
			stub = new org.omg.dds._EntityStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
