package org.omg.CORBA;

/**
 * Generated from IDL interface "Container".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ContainerHolder	implements org.omg.CORBA.portable.Streamable{
	 public Container value;
	public ContainerHolder()
	{
	}
	public ContainerHolder (final Container initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ContainerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ContainerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ContainerHelper.write (_out,value);
	}
}
