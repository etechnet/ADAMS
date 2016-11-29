package org.omg.CORBA;

/**
 * Generated from IDL alias "ExtInitializerSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtInitializerSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ExtInitializer[] value;

	public ExtInitializerSeqHolder ()
	{
	}
	public ExtInitializerSeqHolder (final org.omg.CORBA.ExtInitializer[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ExtInitializerSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExtInitializerSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ExtInitializerSeqHelper.write (out,value);
	}
}
