package org.omg.CORBA;

/**
 * Generated from IDL alias "ExtLocalInterfaceDefSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtLocalInterfaceDefSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ExtLocalInterfaceDef[] value;

	public ExtLocalInterfaceDefSeqHolder ()
	{
	}
	public ExtLocalInterfaceDefSeqHolder (final org.omg.CORBA.ExtLocalInterfaceDef[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ExtLocalInterfaceDefSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExtLocalInterfaceDefSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ExtLocalInterfaceDefSeqHelper.write (out,value);
	}
}
