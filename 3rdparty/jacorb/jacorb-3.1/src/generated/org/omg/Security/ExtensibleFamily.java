package org.omg.Security;

/**
 * Generated from IDL struct "ExtensibleFamily".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExtensibleFamily
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ExtensibleFamily(){}
	public short family_definer;
	public short family;
	public ExtensibleFamily(short family_definer, short family)
	{
		this.family_definer = family_definer;
		this.family = family;
	}
}
