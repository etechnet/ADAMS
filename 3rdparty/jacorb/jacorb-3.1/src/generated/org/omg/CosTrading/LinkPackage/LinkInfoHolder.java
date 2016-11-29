package org.omg.CosTrading.LinkPackage;

/**
 * Generated from IDL struct "LinkInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class LinkInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.LinkPackage.LinkInfo value;

	public LinkInfoHolder ()
	{
	}
	public LinkInfoHolder(final org.omg.CosTrading.LinkPackage.LinkInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.LinkPackage.LinkInfoHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.LinkPackage.LinkInfoHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.LinkPackage.LinkInfoHelper.write(_out, value);
	}
}
