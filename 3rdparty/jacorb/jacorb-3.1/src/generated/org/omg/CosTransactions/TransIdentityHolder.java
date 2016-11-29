package org.omg.CosTransactions;

/**
 * Generated from IDL struct "TransIdentity".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TransIdentityHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTransactions.TransIdentity value;

	public TransIdentityHolder ()
	{
	}
	public TransIdentityHolder(final org.omg.CosTransactions.TransIdentity initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTransactions.TransIdentityHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTransactions.TransIdentityHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTransactions.TransIdentityHelper.write(_out, value);
	}
}
