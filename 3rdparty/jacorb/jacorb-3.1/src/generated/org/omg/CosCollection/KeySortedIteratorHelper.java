package org.omg.CosCollection;


/**
 * Generated from IDL interface "KeySortedIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class KeySortedIteratorHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(KeySortedIteratorHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosCollection/KeySortedIterator:1.0", "KeySortedIterator");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosCollection.KeySortedIterator s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosCollection.KeySortedIterator extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosCollection/KeySortedIterator:1.0";
	}
	public static KeySortedIterator read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosCollection._KeySortedIteratorStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosCollection.KeySortedIterator s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosCollection.KeySortedIterator narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosCollection.KeySortedIterator)
		{
			return (org.omg.CosCollection.KeySortedIterator)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosCollection/KeySortedIterator:1.0"))
		{
			org.omg.CosCollection._KeySortedIteratorStub stub;
			stub = new org.omg.CosCollection._KeySortedIteratorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosCollection.KeySortedIterator unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosCollection.KeySortedIterator)
		{
			return (org.omg.CosCollection.KeySortedIterator)obj;
		}
		else
		{
			org.omg.CosCollection._KeySortedIteratorStub stub;
			stub = new org.omg.CosCollection._KeySortedIteratorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
