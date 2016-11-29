package org.omg.CosTime;
/**
 * Generated from IDL enum "OverlapType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OverlapType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _OTContainer = 0;
	public static final OverlapType OTContainer = new OverlapType(_OTContainer);
	public static final int _OTContained = 1;
	public static final OverlapType OTContained = new OverlapType(_OTContained);
	public static final int _OTOverlap = 2;
	public static final OverlapType OTOverlap = new OverlapType(_OTOverlap);
	public static final int _OTNoOverlap = 3;
	public static final OverlapType OTNoOverlap = new OverlapType(_OTNoOverlap);
	public int value()
	{
		return value;
	}
	public static OverlapType from_int(int value)
	{
		switch (value) {
			case _OTContainer: return OTContainer;
			case _OTContained: return OTContained;
			case _OTOverlap: return OTOverlap;
			case _OTNoOverlap: return OTNoOverlap;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _OTContainer: return "OTContainer";
			case _OTContained: return "OTContained";
			case _OTOverlap: return "OTOverlap";
			case _OTNoOverlap: return "OTNoOverlap";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected OverlapType(int i)
	{
		value = i;
	}
	/**
	 * Designate replacement object when deserialized from stream. See
	 * http://www.omg.org/docs/ptc/02-01-03.htm#Issue4271
	 *
	 * @throws java.io.ObjectStreamException
	 */
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
