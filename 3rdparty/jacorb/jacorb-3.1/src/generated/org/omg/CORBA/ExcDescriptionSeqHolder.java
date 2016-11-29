package org.omg.CORBA;

/**
 * Generated from IDL alias "ExcDescriptionSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExcDescriptionSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ExceptionDescription[] value;

	public ExcDescriptionSeqHolder ()
	{
	}
	public ExcDescriptionSeqHolder (final org.omg.CORBA.ExceptionDescription[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ExcDescriptionSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExcDescriptionSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ExcDescriptionSeqHelper.write (out,value);
	}
}
