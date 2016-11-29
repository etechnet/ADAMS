package org.omg.PortableServer;
/**
 * Generated from IDL enum "LifespanPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class LifespanPolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public LifespanPolicyValue value;

	public LifespanPolicyValueHolder ()
	{
	}
	public LifespanPolicyValueHolder (final LifespanPolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return LifespanPolicyValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LifespanPolicyValueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		LifespanPolicyValueHelper.write (out,value);
	}
}
