package org.omg.Security;

/**
 * Generated from IDL struct "SecAttribute".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SecAttribute
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public SecAttribute(){}
	public org.omg.Security.AttributeType attribute_type;
	public byte[] defining_authority;
	public byte[] value;
	public SecAttribute(org.omg.Security.AttributeType attribute_type, byte[] defining_authority, byte[] value)
	{
		this.attribute_type = attribute_type;
		this.defining_authority = defining_authority;
		this.value = value;
	}
}
