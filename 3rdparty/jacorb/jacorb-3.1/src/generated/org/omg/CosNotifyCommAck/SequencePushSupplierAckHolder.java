package org.omg.CosNotifyCommAck;

/**
 * Generated from IDL interface "SequencePushSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SequencePushSupplierAckHolder	implements org.omg.CORBA.portable.Streamable{
	 public SequencePushSupplierAck value;
	public SequencePushSupplierAckHolder()
	{
	}
	public SequencePushSupplierAckHolder (final SequencePushSupplierAck initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SequencePushSupplierAckHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SequencePushSupplierAckHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SequencePushSupplierAckHelper.write (_out,value);
	}
}
