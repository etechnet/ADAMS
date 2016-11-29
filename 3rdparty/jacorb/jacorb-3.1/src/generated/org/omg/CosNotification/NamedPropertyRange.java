package org.omg.CosNotification;

/**
 * Generated from IDL struct "NamedPropertyRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class NamedPropertyRange
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NamedPropertyRange(){}
	public java.lang.String name;
	public org.omg.CosNotification.PropertyRange range;
	public NamedPropertyRange(java.lang.String name, org.omg.CosNotification.PropertyRange range)
	{
		this.name = name;
		this.range = range;
	}
}
