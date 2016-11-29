package org.omg.CosBridgeAdmin;
/**
 * Generated from IDL enum "ExternalEndpointRole".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointRole
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SOURCE = 0;
	public static final ExternalEndpointRole SOURCE = new ExternalEndpointRole(_SOURCE);
	public static final int _SINK = 1;
	public static final ExternalEndpointRole SINK = new ExternalEndpointRole(_SINK);
	public int value()
	{
		return value;
	}
	public static ExternalEndpointRole from_int(int value)
	{
		switch (value) {
			case _SOURCE: return SOURCE;
			case _SINK: return SINK;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SOURCE: return "SOURCE";
			case _SINK: return "SINK";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected ExternalEndpointRole(int i)
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
