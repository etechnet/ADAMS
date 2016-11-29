package org.omg.DynamicAny;

/**
 * Generated from IDL struct "NameValuePair".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class NameValuePair
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NameValuePair(){}
	public java.lang.String id;
	public org.omg.CORBA.Any value;
	public NameValuePair(java.lang.String id, org.omg.CORBA.Any value)
	{
		this.id = id;
		this.value = value;
	}
}
