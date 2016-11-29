package org.jacorb.events;

/**
 * Generated from IDL interface "JacORBEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.03.02
 */

public final class JacORBEventChannelHolder	implements org.omg.CORBA.portable.Streamable{
	 public JacORBEventChannel value;
	public JacORBEventChannelHolder()
	{
	}
	public JacORBEventChannelHolder (final JacORBEventChannel initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return JacORBEventChannelHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = JacORBEventChannelHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		JacORBEventChannelHelper.write (_out,value);
	}
}
