package org.omg.CORBA.ContainedPackage;

/**
 * Generated from IDL struct "Description".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class Description
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Description(){}
	public org.omg.CORBA.DefinitionKind kind;
	public org.omg.CORBA.Any value;
	public Description(org.omg.CORBA.DefinitionKind kind, org.omg.CORBA.Any value)
	{
		this.kind = kind;
		this.value = value;
	}
}
