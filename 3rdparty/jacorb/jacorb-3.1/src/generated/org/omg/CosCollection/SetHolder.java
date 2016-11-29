package org.omg.CosCollection;

/**
 * Generated from IDL interface "Set".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SetHolder	implements org.omg.CORBA.portable.Streamable{
	 public Set value;
	public SetHolder()
	{
	}
	public SetHolder (final Set initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SetHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SetHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SetHelper.write (_out,value);
	}
}
