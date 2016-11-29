package org.omg.CORBA;

/**
 * Generated from IDL alias "AttrDescriptionSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class AttrDescriptionSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.AttributeDescription[] value;

	public AttrDescriptionSeqHolder ()
	{
	}
	public AttrDescriptionSeqHolder (final org.omg.CORBA.AttributeDescription[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return AttrDescriptionSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AttrDescriptionSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AttrDescriptionSeqHelper.write (out,value);
	}
}
