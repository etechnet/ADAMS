package org.omg.CosNotifyChannelAdmin;
/**
 * Generated from IDL enum "ObtainInfoMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ObtainInfoModeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ObtainInfoMode value;

	public ObtainInfoModeHolder ()
	{
	}
	public ObtainInfoModeHolder (final ObtainInfoMode initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ObtainInfoModeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ObtainInfoModeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ObtainInfoModeHelper.write (out,value);
	}
}
