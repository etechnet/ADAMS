package org.omg.CosTrading;


/**
 * Generated from IDL interface "OfferIdIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OfferIdIteratorHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(OfferIdIteratorHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTrading/OfferIdIterator:1.0", "OfferIdIterator");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.OfferIdIterator s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTrading.OfferIdIterator extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTrading/OfferIdIterator:1.0";
	}
	public static OfferIdIterator read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTrading._OfferIdIteratorStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTrading.OfferIdIterator s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTrading.OfferIdIterator narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.OfferIdIterator)
		{
			return (org.omg.CosTrading.OfferIdIterator)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTrading/OfferIdIterator:1.0"))
		{
			org.omg.CosTrading._OfferIdIteratorStub stub;
			stub = new org.omg.CosTrading._OfferIdIteratorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTrading.OfferIdIterator unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.OfferIdIterator)
		{
			return (org.omg.CosTrading.OfferIdIterator)obj;
		}
		else
		{
			org.omg.CosTrading._OfferIdIteratorStub stub;
			stub = new org.omg.CosTrading._OfferIdIteratorStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
