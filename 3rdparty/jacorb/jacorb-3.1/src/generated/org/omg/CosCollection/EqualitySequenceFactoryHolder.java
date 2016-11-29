package org.omg.CosCollection;

/**
 * Generated from IDL interface "EqualitySequenceFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EqualitySequenceFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public EqualitySequenceFactory value;
	public EqualitySequenceFactoryHolder()
	{
	}
	public EqualitySequenceFactoryHolder (final EqualitySequenceFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EqualitySequenceFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EqualitySequenceFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EqualitySequenceFactoryHelper.write (_out,value);
	}
}
