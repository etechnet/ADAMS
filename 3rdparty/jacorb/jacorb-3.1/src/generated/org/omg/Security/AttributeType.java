package org.omg.Security;

/**
 * Generated from IDL struct "AttributeType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AttributeType
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public AttributeType(){}
	public org.omg.Security.ExtensibleFamily attribute_family;
	public int attribute_type;
	public AttributeType(org.omg.Security.ExtensibleFamily attribute_family, int attribute_type)
	{
		this.attribute_family = attribute_family;
		this.attribute_type = attribute_type;
	}
}
