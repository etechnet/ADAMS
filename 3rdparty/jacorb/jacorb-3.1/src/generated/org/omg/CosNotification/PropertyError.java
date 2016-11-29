package org.omg.CosNotification;

/**
 * Generated from IDL struct "PropertyError".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PropertyError
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PropertyError(){}
	public org.omg.CosNotification.QoSError_code code;
	public java.lang.String name;
	public org.omg.CosNotification.PropertyRange available_range;
	public PropertyError(org.omg.CosNotification.QoSError_code code, java.lang.String name, org.omg.CosNotification.PropertyRange available_range)
	{
		this.code = code;
		this.name = name;
		this.available_range = available_range;
	}
}
