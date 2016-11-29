package org.omg.Security;

/**
 * Generated from IDL struct "AttributeType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AttributeTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Security.AttributeType value;

	public AttributeTypeHolder ()
	{
	}
	public AttributeTypeHolder(final org.omg.Security.AttributeType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.Security.AttributeTypeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.Security.AttributeTypeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.Security.AttributeTypeHelper.write(_out, value);
	}
}
