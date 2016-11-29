package org.omg.CosBridgeAdmin;
/**
 * Generated from IDL enum "FlowStyle".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class FlowStyleHolder
	implements org.omg.CORBA.portable.Streamable
{
	public FlowStyle value;

	public FlowStyleHolder ()
	{
	}
	public FlowStyleHolder (final FlowStyle initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return FlowStyleHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = FlowStyleHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		FlowStyleHelper.write (out,value);
	}
}
