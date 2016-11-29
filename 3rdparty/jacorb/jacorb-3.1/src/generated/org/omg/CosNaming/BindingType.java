package org.omg.CosNaming;
/**
 * Generated from IDL enum "BindingType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BindingType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _nobject = 0;
	public static final BindingType nobject = new BindingType(_nobject);
	public static final int _ncontext = 1;
	public static final BindingType ncontext = new BindingType(_ncontext);
	public int value()
	{
		return value;
	}
	public static BindingType from_int(int value)
	{
		switch (value) {
			case _nobject: return nobject;
			case _ncontext: return ncontext;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _nobject: return "nobject";
			case _ncontext: return "ncontext";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected BindingType(int i)
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
