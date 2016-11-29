package org.omg.PortableInterceptor;

/**
 * Generated from IDL exception "ForwardRequest".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ForwardRequest
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ForwardRequest()
	{
		super(org.omg.PortableInterceptor.ForwardRequestHelper.id());
	}

	public org.omg.CORBA.Object forward;
	public ForwardRequest(java.lang.String _reason,org.omg.CORBA.Object forward)
	{
		super(_reason);
		this.forward = forward;
	}
	public ForwardRequest(org.omg.CORBA.Object forward)
	{
		super(org.omg.PortableInterceptor.ForwardRequestHelper.id());
		this.forward = forward;
	}
}
