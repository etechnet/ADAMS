package org.omg.CORBA;
/**
 * Generated from IDL enum "ParameterMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ParameterMode
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _PARAM_IN = 0;
	public static final ParameterMode PARAM_IN = new ParameterMode(_PARAM_IN);
	public static final int _PARAM_OUT = 1;
	public static final ParameterMode PARAM_OUT = new ParameterMode(_PARAM_OUT);
	public static final int _PARAM_INOUT = 2;
	public static final ParameterMode PARAM_INOUT = new ParameterMode(_PARAM_INOUT);
	public int value()
	{
		return value;
	}
	public static ParameterMode from_int(int value)
	{
		switch (value) {
			case _PARAM_IN: return PARAM_IN;
			case _PARAM_OUT: return PARAM_OUT;
			case _PARAM_INOUT: return PARAM_INOUT;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _PARAM_IN: return "PARAM_IN";
			case _PARAM_OUT: return "PARAM_OUT";
			case _PARAM_INOUT: return "PARAM_INOUT";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ParameterMode(int i)
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
