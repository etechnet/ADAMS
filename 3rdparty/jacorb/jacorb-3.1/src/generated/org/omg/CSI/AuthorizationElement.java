package org.omg.CSI;

/**
 * Generated from IDL struct "AuthorizationElement".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthorizationElement
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public AuthorizationElement(){}
	public int the_type;
	public byte[] the_element;
	public AuthorizationElement(int the_type, byte[] the_element)
	{
		this.the_type = the_type;
		this.the_element = the_element;
	}
}
