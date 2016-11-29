package org.omg.CosNaming.NamingContextPackage;

/**
 * Generated from IDL exception "CannotProceed".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CannotProceed
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CannotProceed()
	{
		super(org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.id());
	}

	public org.omg.CosNaming.NamingContext cxt;
	public org.omg.CosNaming.NameComponent[] rest_of_name;
	public CannotProceed(java.lang.String _reason,org.omg.CosNaming.NamingContext cxt, org.omg.CosNaming.NameComponent[] rest_of_name)
	{
		super(_reason);
		this.cxt = cxt;
		this.rest_of_name = rest_of_name;
	}
	public CannotProceed(org.omg.CosNaming.NamingContext cxt, org.omg.CosNaming.NameComponent[] rest_of_name)
	{
		super(org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.id());
		this.cxt = cxt;
		this.rest_of_name = rest_of_name;
	}
}
