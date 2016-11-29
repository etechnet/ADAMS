package org.omg.Messaging;

/**
 * Generated from IDL struct "RoutingTypeRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RoutingTypeRange
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public RoutingTypeRange(){}
	public short min;
	public short max;
	public RoutingTypeRange(short min, short max)
	{
		this.min = min;
		this.max = max;
	}
}
