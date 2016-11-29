package org.omg.CosTrading;


/**
 * Generated from IDL interface "ImportAttributes".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class ImportAttributesHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ImportAttributesHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:omg.org/CosTrading/ImportAttributes:1.0", "ImportAttributes");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.ImportAttributes s)
	{
			any.insert_Object(s);
	}
	public static org.omg.CosTrading.ImportAttributes extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:omg.org/CosTrading/ImportAttributes:1.0";
	}
	public static ImportAttributes read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.omg.CosTrading._ImportAttributesStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.omg.CosTrading.ImportAttributes s)
	{
		_out.write_Object(s);
	}
	public static org.omg.CosTrading.ImportAttributes narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.ImportAttributes)
		{
			return (org.omg.CosTrading.ImportAttributes)obj;
		}
		else if (obj._is_a("IDL:omg.org/CosTrading/ImportAttributes:1.0"))
		{
			org.omg.CosTrading._ImportAttributesStub stub;
			stub = new org.omg.CosTrading._ImportAttributesStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.omg.CosTrading.ImportAttributes unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.omg.CosTrading.ImportAttributes)
		{
			return (org.omg.CosTrading.ImportAttributes)obj;
		}
		else
		{
			org.omg.CosTrading._ImportAttributesStub stub;
			stub = new org.omg.CosTrading._ImportAttributesStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
