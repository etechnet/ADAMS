package org.omg.CORBA.ContainerPackage;

/**
 * Generated from IDL alias "DescriptionSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class DescriptionSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.ContainerPackage.Description[] value;

	public DescriptionSeqHolder ()
	{
	}
	public DescriptionSeqHolder (final org.omg.CORBA.ContainerPackage.Description[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return DescriptionSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DescriptionSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DescriptionSeqHelper.write (out,value);
	}
}
