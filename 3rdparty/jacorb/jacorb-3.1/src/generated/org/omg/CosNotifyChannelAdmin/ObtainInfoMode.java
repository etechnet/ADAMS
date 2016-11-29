package org.omg.CosNotifyChannelAdmin;
/**
 * Generated from IDL enum "ObtainInfoMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ObtainInfoMode
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _ALL_NOW_UPDATES_OFF = 0;
	public static final ObtainInfoMode ALL_NOW_UPDATES_OFF = new ObtainInfoMode(_ALL_NOW_UPDATES_OFF);
	public static final int _ALL_NOW_UPDATES_ON = 1;
	public static final ObtainInfoMode ALL_NOW_UPDATES_ON = new ObtainInfoMode(_ALL_NOW_UPDATES_ON);
	public static final int _NONE_NOW_UPDATES_OFF = 2;
	public static final ObtainInfoMode NONE_NOW_UPDATES_OFF = new ObtainInfoMode(_NONE_NOW_UPDATES_OFF);
	public static final int _NONE_NOW_UPDATES_ON = 3;
	public static final ObtainInfoMode NONE_NOW_UPDATES_ON = new ObtainInfoMode(_NONE_NOW_UPDATES_ON);
	public int value()
	{
		return value;
	}
	public static ObtainInfoMode from_int(int value)
	{
		switch (value) {
			case _ALL_NOW_UPDATES_OFF: return ALL_NOW_UPDATES_OFF;
			case _ALL_NOW_UPDATES_ON: return ALL_NOW_UPDATES_ON;
			case _NONE_NOW_UPDATES_OFF: return NONE_NOW_UPDATES_OFF;
			case _NONE_NOW_UPDATES_ON: return NONE_NOW_UPDATES_ON;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _ALL_NOW_UPDATES_OFF: return "ALL_NOW_UPDATES_OFF";
			case _ALL_NOW_UPDATES_ON: return "ALL_NOW_UPDATES_ON";
			case _NONE_NOW_UPDATES_OFF: return "NONE_NOW_UPDATES_OFF";
			case _NONE_NOW_UPDATES_ON: return "NONE_NOW_UPDATES_ON";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ObtainInfoMode(int i)
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
