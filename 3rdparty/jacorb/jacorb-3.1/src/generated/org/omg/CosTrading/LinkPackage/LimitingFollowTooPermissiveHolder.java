package org.omg.CosTrading.LinkPackage;

/**
 * Generated from IDL exception "LimitingFollowTooPermissive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class LimitingFollowTooPermissiveHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive value;

	public LimitingFollowTooPermissiveHolder ()
	{
	}
	public LimitingFollowTooPermissiveHolder(final org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissive initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissiveHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissiveHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.LinkPackage.LimitingFollowTooPermissiveHelper.write(_out, value);
	}
}
