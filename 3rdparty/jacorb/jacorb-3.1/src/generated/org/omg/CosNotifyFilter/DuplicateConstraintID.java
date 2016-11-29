package org.omg.CosNotifyFilter;

/**
 * Generated from IDL exception "DuplicateConstraintID".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DuplicateConstraintID
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DuplicateConstraintID()
	{
		super(org.omg.CosNotifyFilter.DuplicateConstraintIDHelper.id());
	}

	public int id;
	public DuplicateConstraintID(java.lang.String _reason,int id)
	{
		super(_reason);
		this.id = id;
	}
	public DuplicateConstraintID(int id)
	{
		super(org.omg.CosNotifyFilter.DuplicateConstraintIDHelper.id());
		this.id = id;
	}
}
