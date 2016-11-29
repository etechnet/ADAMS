package org.omg.CORBA;

/**
 * Generated from IDL interface "SequenceDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class SequenceDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public SequenceDef value;
	public SequenceDefHolder()
	{
	}
	public SequenceDefHolder (final SequenceDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SequenceDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SequenceDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SequenceDefHelper.write (_out,value);
	}
}
