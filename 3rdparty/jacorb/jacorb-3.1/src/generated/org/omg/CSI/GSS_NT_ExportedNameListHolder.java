package org.omg.CSI;

/**
 * Generated from IDL alias "GSS_NT_ExportedNameList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class GSS_NT_ExportedNameListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[][] value;

	public GSS_NT_ExportedNameListHolder ()
	{
	}
	public GSS_NT_ExportedNameListHolder (final byte[][] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return GSS_NT_ExportedNameListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = GSS_NT_ExportedNameListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		GSS_NT_ExportedNameListHelper.write (out,value);
	}
}
