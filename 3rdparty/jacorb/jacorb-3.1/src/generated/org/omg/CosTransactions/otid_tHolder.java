package org.omg.CosTransactions;

/**
 * Generated from IDL struct "otid_t".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class otid_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTransactions.otid_t value;

	public otid_tHolder ()
	{
	}
	public otid_tHolder(final org.omg.CosTransactions.otid_t initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTransactions.otid_tHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTransactions.otid_tHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTransactions.otid_tHelper.write(_out, value);
	}
}
