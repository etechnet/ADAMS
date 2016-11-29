package org.omg.CosTransactions;

/**
 * Generated from IDL interface "SubtransactionAwareResource".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SubtransactionAwareResourceHolder	implements org.omg.CORBA.portable.Streamable{
	 public SubtransactionAwareResource value;
	public SubtransactionAwareResourceHolder()
	{
	}
	public SubtransactionAwareResourceHolder (final SubtransactionAwareResource initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SubtransactionAwareResourceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SubtransactionAwareResourceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SubtransactionAwareResourceHelper.write (_out,value);
	}
}
