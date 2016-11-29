package org.omg.IOP;

/**
 * Generated from IDL struct "ServiceContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ServiceContext
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ServiceContext(){}
	public int context_id;
	public byte[] context_data;
	public ServiceContext(int context_id, byte[] context_data)
	{
		this.context_id = context_id;
		this.context_data = context_data;
	}
}
