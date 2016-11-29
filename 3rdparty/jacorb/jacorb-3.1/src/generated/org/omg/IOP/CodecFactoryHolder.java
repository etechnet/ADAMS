package org.omg.IOP;

/**
 * Generated from IDL interface "CodecFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class CodecFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public CodecFactory value;
	public CodecFactoryHolder()
	{
	}
	public CodecFactoryHolder (final CodecFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CodecFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CodecFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CodecFactoryHelper.write (_out,value);
	}
}
