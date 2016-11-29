package org.omg.RTCORBA;

/**
 * Generated from IDL struct "PriorityBand".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PriorityBand
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PriorityBand(){}
	public short low;
	public short high;
	public PriorityBand(short low, short high)
	{
		this.low = low;
		this.high = high;
	}
}
