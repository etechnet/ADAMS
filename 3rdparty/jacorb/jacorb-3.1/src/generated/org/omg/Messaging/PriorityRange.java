package org.omg.Messaging;

/**
 * Generated from IDL struct "PriorityRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class PriorityRange
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PriorityRange(){}
	public short min;
	public short max;
	public PriorityRange(short min, short max)
	{
		this.min = min;
		this.max = max;
	}
}
