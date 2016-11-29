package org.omg.dds;

/**
 * Generated from IDL interface "PublisherListener".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PublisherListenerHolder	implements org.omg.CORBA.portable.Streamable{
	 public PublisherListener value;
	public PublisherListenerHolder()
	{
	}
	public PublisherListenerHolder (final PublisherListener initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PublisherListenerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PublisherListenerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PublisherListenerHelper.write (_out,value);
	}
}
