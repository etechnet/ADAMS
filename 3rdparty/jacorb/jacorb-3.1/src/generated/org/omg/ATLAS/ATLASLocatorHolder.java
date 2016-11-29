package org.omg.ATLAS;
/**
 * Generated from IDL union "ATLASLocator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ATLASLocatorHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ATLASLocator value;

	public ATLASLocatorHolder ()
	{
	}
	public ATLASLocatorHolder (final ATLASLocator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ATLASLocatorHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ATLASLocatorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ATLASLocatorHelper.write (out, value);
	}
}
