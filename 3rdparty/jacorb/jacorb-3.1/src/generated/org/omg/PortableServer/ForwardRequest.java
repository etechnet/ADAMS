package org.omg.PortableServer;

/**
 * Generated from IDL exception "ForwardRequest".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ForwardRequest
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ForwardRequest()
	{
		super(org.omg.PortableServer.ForwardRequestHelper.id());
	}

	public org.omg.CORBA.Object forward_reference;
	public ForwardRequest(java.lang.String _reason,org.omg.CORBA.Object forward_reference)
	{
		super(_reason);
		this.forward_reference = forward_reference;
	}
	public ForwardRequest(org.omg.CORBA.Object forward_reference)
	{
		super(org.omg.PortableServer.ForwardRequestHelper.id());
		this.forward_reference = forward_reference;
	}
}
