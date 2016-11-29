package org.jacorb.imr;


/**
 * Generated from IDL interface "ImplementationRepository".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ImplementationRepositoryHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ImplementationRepositoryHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:org/jacorb/imr/ImplementationRepository:1.0", "ImplementationRepository");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.imr.ImplementationRepository s)
	{
			any.insert_Object(s);
	}
	public static org.jacorb.imr.ImplementationRepository extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:org/jacorb/imr/ImplementationRepository:1.0";
	}
	public static ImplementationRepository read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.jacorb.imr._ImplementationRepositoryStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jacorb.imr.ImplementationRepository s)
	{
		_out.write_Object(s);
	}
	public static org.jacorb.imr.ImplementationRepository narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.ImplementationRepository)
		{
			return (org.jacorb.imr.ImplementationRepository)obj;
		}
		else if (obj._is_a("IDL:org/jacorb/imr/ImplementationRepository:1.0"))
		{
			org.jacorb.imr._ImplementationRepositoryStub stub;
			stub = new org.jacorb.imr._ImplementationRepositoryStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jacorb.imr.ImplementationRepository unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jacorb.imr.ImplementationRepository)
		{
			return (org.jacorb.imr.ImplementationRepository)obj;
		}
		else
		{
			org.jacorb.imr._ImplementationRepositoryStub stub;
			stub = new org.jacorb.imr._ImplementationRepositoryStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
