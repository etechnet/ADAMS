package org.omg.CosTransactions;

/**
 * Generated from IDL exception "Inactive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class InactiveHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTransactions.Inactive value;

	public InactiveHolder ()
	{
	}
	public InactiveHolder(final org.omg.CosTransactions.Inactive initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTransactions.InactiveHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTransactions.InactiveHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTransactions.InactiveHelper.write(_out, value);
	}
}
