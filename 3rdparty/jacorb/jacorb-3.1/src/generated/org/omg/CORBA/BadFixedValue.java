package org.omg.CORBA;

/**
 * Generated from IDL exception "BadFixedValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class BadFixedValue
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public BadFixedValue()
	{
		super(org.omg.CORBA.BadFixedValueHelper.id());
	}

	public int offset;
	public BadFixedValue(java.lang.String _reason,int offset)
	{
		super(_reason);
		this.offset = offset;
	}
	public BadFixedValue(int offset)
	{
		super(org.omg.CORBA.BadFixedValueHelper.id());
		this.offset = offset;
	}
}
