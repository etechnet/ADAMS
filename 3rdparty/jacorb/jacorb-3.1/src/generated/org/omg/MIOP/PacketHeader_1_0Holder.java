package org.omg.MIOP;

/**
 * Generated from IDL struct "PacketHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PacketHeader_1_0Holder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.MIOP.PacketHeader_1_0 value;

	public PacketHeader_1_0Holder ()
	{
	}
	public PacketHeader_1_0Holder(final org.omg.MIOP.PacketHeader_1_0 initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.MIOP.PacketHeader_1_0Helper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.MIOP.PacketHeader_1_0Helper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.MIOP.PacketHeader_1_0Helper.write(_out, value);
	}
}
