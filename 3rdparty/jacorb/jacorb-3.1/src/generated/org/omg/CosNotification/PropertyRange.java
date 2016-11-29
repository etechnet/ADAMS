package org.omg.CosNotification;

/**
 * Generated from IDL struct "PropertyRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PropertyRange
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PropertyRange(){}
	public org.omg.CORBA.Any low_val;
	public org.omg.CORBA.Any high_val;
	public PropertyRange(org.omg.CORBA.Any low_val, org.omg.CORBA.Any high_val)
	{
		this.low_val = low_val;
		this.high_val = high_val;
	}
}
