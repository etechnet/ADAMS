package org.omg.GIOP;

/**
 * Generated from IDL struct "IORAddressingInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class IORAddressingInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IORAddressingInfo(){}
	public int selected_profile_index;
	public org.omg.IOP.IOR ior;
	public IORAddressingInfo(int selected_profile_index, org.omg.IOP.IOR ior)
	{
		this.selected_profile_index = selected_profile_index;
		this.ior = ior;
	}
}
