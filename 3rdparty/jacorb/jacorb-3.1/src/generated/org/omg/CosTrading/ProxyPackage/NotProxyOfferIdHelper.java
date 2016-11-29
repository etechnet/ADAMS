package org.omg.CosTrading.ProxyPackage;


/**
 * Generated from IDL exception "NotProxyOfferId".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class NotProxyOfferIdHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(NotProxyOfferIdHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.CosTrading.ProxyPackage.NotProxyOfferIdHelper.id(),"NotProxyOfferId",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CosTrading.OfferIdHelper.id(), "OfferId",org.omg.CORBA.ORB.init().create_string_tc(0)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CosTrading.ProxyPackage.NotProxyOfferId s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CosTrading.ProxyPackage.NotProxyOfferId extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/CosTrading/Proxy/NotProxyOfferId:1.0";
	}
	public static org.omg.CosTrading.ProxyPackage.NotProxyOfferId read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		java.lang.String x0;
		x0=in.read_string();
		final org.omg.CosTrading.ProxyPackage.NotProxyOfferId result = new org.omg.CosTrading.ProxyPackage.NotProxyOfferId(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CosTrading.ProxyPackage.NotProxyOfferId s)
	{
		out.write_string(id());
		java.lang.String tmpResult1092 = s.id;
out.write_string( tmpResult1092 );
	}
}
