package org.omg.PortableServer;
/**
 * Generated from IDL enum "ServantRetentionPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ServantRetentionPolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ServantRetentionPolicyValue value;

	public ServantRetentionPolicyValueHolder ()
	{
	}
	public ServantRetentionPolicyValueHolder (final ServantRetentionPolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ServantRetentionPolicyValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServantRetentionPolicyValueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ServantRetentionPolicyValueHelper.write (out,value);
	}
}
