package org.omg.GSSUP;

/**
 * Generated from IDL struct "InitialContextToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class InitialContextToken
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InitialContextToken(){}
	public byte[] username;
	public byte[] password;
	public byte[] target_name;
	public InitialContextToken(byte[] username, byte[] password, byte[] target_name)
	{
		this.username = username;
		this.password = password;
		this.target_name = target_name;
	}
}
