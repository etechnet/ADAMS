package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL alias "ExternalEndpointErrorSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointErrorSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosBridgeAdmin.ExternalEndpointError[] value;

	public ExternalEndpointErrorSeqHolder ()
	{
	}
	public ExternalEndpointErrorSeqHolder (final org.omg.CosBridgeAdmin.ExternalEndpointError[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ExternalEndpointErrorSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExternalEndpointErrorSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ExternalEndpointErrorSeqHelper.write (out,value);
	}
}
