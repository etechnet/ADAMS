package org.omg.CosNotification;

/**
 * Generated from IDL alias "PropertyErrorSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PropertyErrorSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotification.PropertyError[] value;

	public PropertyErrorSeqHolder ()
	{
	}
	public PropertyErrorSeqHolder (final org.omg.CosNotification.PropertyError[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PropertyErrorSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PropertyErrorSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PropertyErrorSeqHelper.write (out,value);
	}
}
