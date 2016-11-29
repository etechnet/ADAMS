package org.omg.DynamicAny;

/**
 * Generated from IDL interface "DynSequence".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DynSequenceHolder	implements org.omg.CORBA.portable.Streamable{
	 public DynSequence value;
	public DynSequenceHolder()
	{
	}
	public DynSequenceHolder (final DynSequence initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DynSequenceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DynSequenceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DynSequenceHelper.write (_out,value);
	}
}
