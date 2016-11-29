package org.omg.CORBA;

/**
 * Generated from IDL interface "InterfaceAttrExtension".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InterfaceAttrExtensionHolder	implements org.omg.CORBA.portable.Streamable{
	 public InterfaceAttrExtension value;
	public InterfaceAttrExtensionHolder()
	{
	}
	public InterfaceAttrExtensionHolder (final InterfaceAttrExtension initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return InterfaceAttrExtensionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = InterfaceAttrExtensionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		InterfaceAttrExtensionHelper.write (_out,value);
	}
}
