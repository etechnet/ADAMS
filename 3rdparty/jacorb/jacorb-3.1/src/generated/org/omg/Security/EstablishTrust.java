package org.omg.Security;

/**
 * Generated from IDL struct "EstablishTrust".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class EstablishTrust
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public EstablishTrust(){}
	public boolean trust_in_client;
	public boolean trust_in_target;
	public EstablishTrust(boolean trust_in_client, boolean trust_in_target)
	{
		this.trust_in_client = trust_in_client;
		this.trust_in_target = trust_in_target;
	}
}
