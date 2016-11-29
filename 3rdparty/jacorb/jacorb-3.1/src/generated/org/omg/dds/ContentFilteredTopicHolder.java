package org.omg.dds;

/**
 * Generated from IDL interface "ContentFilteredTopic".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ContentFilteredTopicHolder	implements org.omg.CORBA.portable.Streamable{
	 public ContentFilteredTopic value;
	public ContentFilteredTopicHolder()
	{
	}
	public ContentFilteredTopicHolder (final ContentFilteredTopic initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ContentFilteredTopicHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ContentFilteredTopicHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ContentFilteredTopicHelper.write (_out,value);
	}
}
