package org.omg.Messaging;

/**
 * Generated from IDL interface "SyncScopePolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class SyncScopePolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public SyncScopePolicy value;
	public SyncScopePolicyHolder()
	{
	}
	public SyncScopePolicyHolder (final SyncScopePolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SyncScopePolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SyncScopePolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SyncScopePolicyHelper.write (_out,value);
	}
}
