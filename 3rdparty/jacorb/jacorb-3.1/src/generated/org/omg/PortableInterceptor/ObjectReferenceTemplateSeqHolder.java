package org.omg.PortableInterceptor;

/**
 * Generated from IDL alias "ObjectReferenceTemplateSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ObjectReferenceTemplateSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableInterceptor.ObjectReferenceTemplate[] value;

	public ObjectReferenceTemplateSeqHolder ()
	{
	}
	public ObjectReferenceTemplateSeqHolder (final org.omg.PortableInterceptor.ObjectReferenceTemplate[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ObjectReferenceTemplateSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ObjectReferenceTemplateSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ObjectReferenceTemplateSeqHelper.write (out,value);
	}
}
