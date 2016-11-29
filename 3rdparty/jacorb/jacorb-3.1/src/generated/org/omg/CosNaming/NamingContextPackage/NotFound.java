package org.omg.CosNaming.NamingContextPackage;

/**
 * Generated from IDL exception "NotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class NotFound
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NotFound()
	{
		super(org.omg.CosNaming.NamingContextPackage.NotFoundHelper.id());
	}

	public org.omg.CosNaming.NamingContextPackage.NotFoundReason why;
	public org.omg.CosNaming.NameComponent[] rest_of_name;
	public NotFound(java.lang.String _reason,org.omg.CosNaming.NamingContextPackage.NotFoundReason why, org.omg.CosNaming.NameComponent[] rest_of_name)
	{
		super(_reason);
		this.why = why;
		this.rest_of_name = rest_of_name;
	}
	public NotFound(org.omg.CosNaming.NamingContextPackage.NotFoundReason why, org.omg.CosNaming.NameComponent[] rest_of_name)
	{
		super(org.omg.CosNaming.NamingContextPackage.NotFoundHelper.id());
		this.why = why;
		this.rest_of_name = rest_of_name;
	}
}
