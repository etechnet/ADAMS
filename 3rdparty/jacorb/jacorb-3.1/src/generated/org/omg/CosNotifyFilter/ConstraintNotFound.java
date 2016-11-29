package org.omg.CosNotifyFilter;

/**
 * Generated from IDL exception "ConstraintNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintNotFound
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ConstraintNotFound()
	{
		super(org.omg.CosNotifyFilter.ConstraintNotFoundHelper.id());
	}

	public int id;
	public ConstraintNotFound(java.lang.String _reason,int id)
	{
		super(_reason);
		this.id = id;
	}
	public ConstraintNotFound(int id)
	{
		super(org.omg.CosNotifyFilter.ConstraintNotFoundHelper.id());
		this.id = id;
	}
}
