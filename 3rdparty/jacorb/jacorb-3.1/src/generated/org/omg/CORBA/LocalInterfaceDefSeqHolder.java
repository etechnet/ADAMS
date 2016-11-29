package org.omg.CORBA;

/**
 * Generated from IDL alias "LocalInterfaceDefSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class LocalInterfaceDefSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.LocalInterfaceDef[] value;

	public LocalInterfaceDefSeqHolder ()
	{
	}
	public LocalInterfaceDefSeqHolder (final org.omg.CORBA.LocalInterfaceDef[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return LocalInterfaceDefSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LocalInterfaceDefSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		LocalInterfaceDefSeqHelper.write (out,value);
	}
}
