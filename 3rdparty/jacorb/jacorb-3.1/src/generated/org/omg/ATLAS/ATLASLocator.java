package org.omg.ATLAS;

/**
 * Generated from IDL union "ATLASLocator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ATLASLocator
	implements org.omg.CORBA.portable.IDLEntity
{
	private int discriminator;
	private org.omg.ATLAS.CosNamingLocator naming_locator;
	private java.lang.String the_url;
	private org.omg.ATLAS.AuthTokenDispenser the_dispenser;

	public ATLASLocator ()
	{
	}

	public int discriminator ()
	{
		return discriminator;
	}

	public org.omg.ATLAS.CosNamingLocator naming_locator ()
	{
		if (discriminator != 1)
			throw new org.omg.CORBA.BAD_OPERATION();
		return naming_locator;
	}

	public void naming_locator (org.omg.ATLAS.CosNamingLocator _x)
	{
		discriminator = 1;
		naming_locator = _x;
	}

	public java.lang.String the_url ()
	{
		if (discriminator != 2)
			throw new org.omg.CORBA.BAD_OPERATION();
		return the_url;
	}

	public void the_url (java.lang.String _x)
	{
		discriminator = 2;
		the_url = _x;
	}

	public org.omg.ATLAS.AuthTokenDispenser the_dispenser ()
	{
		if (discriminator != 3)
			throw new org.omg.CORBA.BAD_OPERATION();
		return the_dispenser;
	}

	public void the_dispenser (org.omg.ATLAS.AuthTokenDispenser _x)
	{
		discriminator = 3;
		the_dispenser = _x;
	}

	public void __default ()
	{
		discriminator = 0;
	}
	public void __default (int _discriminator)
	{
		if(  _discriminator == 1 || _discriminator == 2 || _discriminator == 3 )
			throw new org.omg.CORBA.BAD_PARAM( "Illegal value is used in __default method", 34, org.omg.CORBA.CompletionStatus.COMPLETED_NO );

		discriminator = _discriminator;
	}
}
