package org.omg.GIOP;

/**
 * Generated from IDL union "TargetAddress".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class TargetAddress
	implements org.omg.CORBA.portable.IDLEntity
{
	private short discriminator;
	private byte[] object_key;
	private org.omg.IOP.TaggedProfile profile;
	private org.omg.GIOP.IORAddressingInfo ior;

	public TargetAddress ()
	{
	}

	public short discriminator ()
	{
		return discriminator;
	}

	public byte[] object_key ()
	{
		if (discriminator != 0)
			throw new org.omg.CORBA.BAD_OPERATION();
		return object_key;
	}

	public void object_key (byte[] _x)
	{
		discriminator = 0;
		object_key = _x;
	}

	public org.omg.IOP.TaggedProfile profile ()
	{
		if (discriminator != 1)
			throw new org.omg.CORBA.BAD_OPERATION();
		return profile;
	}

	public void profile (org.omg.IOP.TaggedProfile _x)
	{
		discriminator = 1;
		profile = _x;
	}

	public org.omg.GIOP.IORAddressingInfo ior ()
	{
		if (discriminator != 2)
			throw new org.omg.CORBA.BAD_OPERATION();
		return ior;
	}

	public void ior (org.omg.GIOP.IORAddressingInfo _x)
	{
		discriminator = 2;
		ior = _x;
	}

	public void __default ()
	{
		discriminator = 3;
	}
	public void __default (short _discriminator)
	{
		if(  _discriminator == 0 || _discriminator == 1 || _discriminator == 2 )
			throw new org.omg.CORBA.BAD_PARAM( "Illegal value is used in __default method", 34, org.omg.CORBA.CompletionStatus.COMPLETED_NO );

		discriminator = _discriminator;
	}
}
