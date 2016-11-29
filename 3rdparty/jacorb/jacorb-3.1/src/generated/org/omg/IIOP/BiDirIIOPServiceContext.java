package org.omg.IIOP;

/**
 * Generated from IDL struct "BiDirIIOPServiceContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BiDirIIOPServiceContext
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public BiDirIIOPServiceContext(){}
	public org.omg.IIOP.ListenPoint[] listen_points;
	public BiDirIIOPServiceContext(org.omg.IIOP.ListenPoint[] listen_points)
	{
		this.listen_points = listen_points;
	}
}
