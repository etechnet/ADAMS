package org.omg.PortableServer;
/**
 * Generated from IDL enum "RequestProcessingPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class RequestProcessingPolicyValue
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _USE_ACTIVE_OBJECT_MAP_ONLY = 0;
	public static final RequestProcessingPolicyValue USE_ACTIVE_OBJECT_MAP_ONLY = new RequestProcessingPolicyValue(_USE_ACTIVE_OBJECT_MAP_ONLY);
	public static final int _USE_DEFAULT_SERVANT = 1;
	public static final RequestProcessingPolicyValue USE_DEFAULT_SERVANT = new RequestProcessingPolicyValue(_USE_DEFAULT_SERVANT);
	public static final int _USE_SERVANT_MANAGER = 2;
	public static final RequestProcessingPolicyValue USE_SERVANT_MANAGER = new RequestProcessingPolicyValue(_USE_SERVANT_MANAGER);
	public int value()
	{
		return value;
	}
	public static RequestProcessingPolicyValue from_int(int value)
	{
		switch (value) {
			case _USE_ACTIVE_OBJECT_MAP_ONLY: return USE_ACTIVE_OBJECT_MAP_ONLY;
			case _USE_DEFAULT_SERVANT: return USE_DEFAULT_SERVANT;
			case _USE_SERVANT_MANAGER: return USE_SERVANT_MANAGER;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _USE_ACTIVE_OBJECT_MAP_ONLY: return "USE_ACTIVE_OBJECT_MAP_ONLY";
			case _USE_DEFAULT_SERVANT: return "USE_DEFAULT_SERVANT";
			case _USE_SERVANT_MANAGER: return "USE_SERVANT_MANAGER";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected RequestProcessingPolicyValue(int i)
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
