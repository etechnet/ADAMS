package org.omg.CosCollection;

/**
 * Generated from IDL interface "CSequence".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CSequenceHolder	implements org.omg.CORBA.portable.Streamable{
	 public CSequence value;
	public CSequenceHolder()
	{
	}
	public CSequenceHolder (final CSequence initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CSequenceHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CSequenceHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CSequenceHelper.write (_out,value);
	}
}
