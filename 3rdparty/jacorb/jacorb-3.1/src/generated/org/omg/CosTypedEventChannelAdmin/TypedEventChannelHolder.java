package org.omg.CosTypedEventChannelAdmin;

/**
 * Generated from IDL interface "TypedEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class TypedEventChannelHolder	implements org.omg.CORBA.portable.Streamable{
	 public TypedEventChannel value;
	public TypedEventChannelHolder()
	{
	}
	public TypedEventChannelHolder (final TypedEventChannel initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TypedEventChannelHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TypedEventChannelHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TypedEventChannelHelper.write (_out,value);
	}
}
