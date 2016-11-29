package org.omg.GSSUP;

/**
 * Generated from IDL struct "ErrorToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ErrorToken
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ErrorToken(){}
	public int error_code;
	public ErrorToken(int error_code)
	{
		this.error_code = error_code;
	}
}
