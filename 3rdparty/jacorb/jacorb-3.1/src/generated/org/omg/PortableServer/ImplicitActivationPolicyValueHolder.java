package org.omg.PortableServer;
/**
 * Generated from IDL enum "ImplicitActivationPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ImplicitActivationPolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ImplicitActivationPolicyValue value;

	public ImplicitActivationPolicyValueHolder ()
	{
	}
	public ImplicitActivationPolicyValueHolder (final ImplicitActivationPolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ImplicitActivationPolicyValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ImplicitActivationPolicyValueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ImplicitActivationPolicyValueHelper.write (out,value);
	}
}
