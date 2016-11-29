package org.omg.CosTransactions;

/**
 * Generated from IDL struct "PropagationContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PropagationContext
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PropagationContext(){}
	public int timeout;
	public org.omg.CosTransactions.TransIdentity current;
	public org.omg.CosTransactions.TransIdentity[] parents;
	public org.omg.CORBA.Any implementation_specific_data;
	public PropagationContext(int timeout, org.omg.CosTransactions.TransIdentity current, org.omg.CosTransactions.TransIdentity[] parents, org.omg.CORBA.Any implementation_specific_data)
	{
		this.timeout = timeout;
		this.current = current;
		this.parents = parents;
		this.implementation_specific_data = implementation_specific_data;
	}
}
