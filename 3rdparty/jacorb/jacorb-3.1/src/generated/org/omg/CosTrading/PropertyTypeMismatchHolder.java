package org.omg.CosTrading;

/**
 * Generated from IDL exception "PropertyTypeMismatch".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PropertyTypeMismatchHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.PropertyTypeMismatch value;

	public PropertyTypeMismatchHolder ()
	{
	}
	public PropertyTypeMismatchHolder(final org.omg.CosTrading.PropertyTypeMismatch initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.PropertyTypeMismatchHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.PropertyTypeMismatchHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.PropertyTypeMismatchHelper.write(_out, value);
	}
}
