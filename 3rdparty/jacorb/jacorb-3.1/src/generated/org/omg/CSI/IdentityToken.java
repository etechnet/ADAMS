package org.omg.CSI;

/**
 * Generated from IDL union "IdentityToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class IdentityToken
	implements org.omg.CORBA.portable.IDLEntity
{
	private int discriminator;
	private boolean absent;
	private boolean anonymous;
	private byte[] principal_name;
	private byte[] certificate_chain;
	private byte[] dn;
	private byte[] id;

	public IdentityToken ()
	{
	}

	public int discriminator ()
	{
		return discriminator;
	}

	public boolean absent ()
	{
		if (discriminator != 0)
			throw new org.omg.CORBA.BAD_OPERATION();
		return absent;
	}

	public void absent (boolean _x)
	{
		discriminator = 0;
		absent = _x;
	}

	public boolean anonymous ()
	{
		if (discriminator != 1)
			throw new org.omg.CORBA.BAD_OPERATION();
		return anonymous;
	}

	public void anonymous (boolean _x)
	{
		discriminator = 1;
		anonymous = _x;
	}

	public byte[] principal_name ()
	{
		if (discriminator != 2)
			throw new org.omg.CORBA.BAD_OPERATION();
		return principal_name;
	}

	public void principal_name (byte[] _x)
	{
		discriminator = 2;
		principal_name = _x;
	}

	public byte[] certificate_chain ()
	{
		if (discriminator != 4)
			throw new org.omg.CORBA.BAD_OPERATION();
		return certificate_chain;
	}

	public void certificate_chain (byte[] _x)
	{
		discriminator = 4;
		certificate_chain = _x;
	}

	public byte[] dn ()
	{
		if (discriminator != 8)
			throw new org.omg.CORBA.BAD_OPERATION();
		return dn;
	}

	public void dn (byte[] _x)
	{
		discriminator = 8;
		dn = _x;
	}

	public byte[] id ()
	{
		if (discriminator != 3)
			throw new org.omg.CORBA.BAD_OPERATION();
		return id;
	}

	public void id (byte[] _x)
	{
		discriminator = 3;
		id = _x;
	}

	public void id (int _discriminator, byte[] _x)
	{
		if (_discriminator != 3)
			throw new org.omg.CORBA.BAD_OPERATION();
		discriminator = _discriminator;
		id = _x;
	}

}
