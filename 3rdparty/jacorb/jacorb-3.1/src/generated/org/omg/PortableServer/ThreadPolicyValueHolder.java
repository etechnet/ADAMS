package org.omg.PortableServer;
/**
 * Generated from IDL enum "ThreadPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ThreadPolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ThreadPolicyValue value;

	public ThreadPolicyValueHolder ()
	{
	}
	public ThreadPolicyValueHolder (final ThreadPolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ThreadPolicyValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ThreadPolicyValueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ThreadPolicyValueHelper.write (out,value);
	}
}
