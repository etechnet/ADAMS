package org.omg.CosNaming;

/**
 * Generated from IDL alias "BindingList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BindingListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNaming.Binding[] value;

	public BindingListHolder ()
	{
	}
	public BindingListHolder (final org.omg.CosNaming.Binding[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return BindingListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = BindingListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		BindingListHelper.write (out,value);
	}
}
