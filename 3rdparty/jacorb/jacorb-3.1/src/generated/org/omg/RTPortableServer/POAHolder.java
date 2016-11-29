package org.omg.RTPortableServer;

/**
 * Generated from IDL interface "POA".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class POAHolder	implements org.omg.CORBA.portable.Streamable{
	 public POA value;
	public POAHolder()
	{
	}
	public POAHolder (final POA initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return POAHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = POAHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		POAHelper.write (_out,value);
	}
}
