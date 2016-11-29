package org.omg.PortableInterceptor.ORBInitInfoPackage;

/**
 * Generated from IDL exception "DuplicateName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DuplicateName
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DuplicateName()
	{
		super(org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateNameHelper.id());
	}

	public java.lang.String name = "";
	public DuplicateName(java.lang.String _reason,java.lang.String name)
	{
		super(_reason);
		this.name = name;
	}
	public DuplicateName(java.lang.String name)
	{
		super(org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateNameHelper.id());
		this.name = name;
	}
}
