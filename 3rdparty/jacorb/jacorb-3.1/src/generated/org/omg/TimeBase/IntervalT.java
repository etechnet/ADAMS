package org.omg.TimeBase;

/**
 * Generated from IDL struct "IntervalT".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class IntervalT
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IntervalT(){}
	public long lower_bound;
	public long upper_bound;
	public IntervalT(long lower_bound, long upper_bound)
	{
		this.lower_bound = lower_bound;
		this.upper_bound = upper_bound;
	}
}
