package org.omg.CosNotifyFilter;

/**
 * Generated from IDL struct "MappingConstraintInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class MappingConstraintInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyFilter.MappingConstraintInfo value;

	public MappingConstraintInfoHolder ()
	{
	}
	public MappingConstraintInfoHolder(final org.omg.CosNotifyFilter.MappingConstraintInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyFilter.MappingConstraintInfoHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyFilter.MappingConstraintInfoHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyFilter.MappingConstraintInfoHelper.write(_out, value);
	}
}
