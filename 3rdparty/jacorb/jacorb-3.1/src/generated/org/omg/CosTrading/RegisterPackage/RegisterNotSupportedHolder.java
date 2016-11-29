package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "RegisterNotSupported".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class RegisterNotSupportedHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.RegisterPackage.RegisterNotSupported value;

	public RegisterNotSupportedHolder ()
	{
	}
	public RegisterNotSupportedHolder(final org.omg.CosTrading.RegisterPackage.RegisterNotSupported initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.RegisterPackage.RegisterNotSupportedHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.RegisterPackage.RegisterNotSupportedHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.RegisterPackage.RegisterNotSupportedHelper.write(_out, value);
	}
}
