package org.omg.PortableGroup;

/**
 * Generated from IDL struct "TagGroupTaggedComponent".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class TagGroupTaggedComponentHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.TagGroupTaggedComponent value;

	public TagGroupTaggedComponentHolder ()
	{
	}
	public TagGroupTaggedComponentHolder(final org.omg.PortableGroup.TagGroupTaggedComponent initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.TagGroupTaggedComponentHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.TagGroupTaggedComponentHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.TagGroupTaggedComponentHelper.write(_out, value);
	}
}
