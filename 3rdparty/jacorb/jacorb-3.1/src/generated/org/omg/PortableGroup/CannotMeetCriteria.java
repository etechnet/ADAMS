package org.omg.PortableGroup;

/**
 * Generated from IDL exception "CannotMeetCriteria".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class CannotMeetCriteria
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CannotMeetCriteria()
	{
		super(org.omg.PortableGroup.CannotMeetCriteriaHelper.id());
	}

	public org.omg.PortableGroup.Property[] unmet_criteria;
	public CannotMeetCriteria(java.lang.String _reason,org.omg.PortableGroup.Property[] unmet_criteria)
	{
		super(_reason);
		this.unmet_criteria = unmet_criteria;
	}
	public CannotMeetCriteria(org.omg.PortableGroup.Property[] unmet_criteria)
	{
		super(org.omg.PortableGroup.CannotMeetCriteriaHelper.id());
		this.unmet_criteria = unmet_criteria;
	}
}
