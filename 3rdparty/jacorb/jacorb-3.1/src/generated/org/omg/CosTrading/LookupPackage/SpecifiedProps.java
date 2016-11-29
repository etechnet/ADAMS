package org.omg.CosTrading.LookupPackage;

/**
 * Generated from IDL union "SpecifiedProps".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SpecifiedProps
	implements org.omg.CORBA.portable.IDLEntity
{
	private org.omg.CosTrading.LookupPackage.HowManyProps discriminator;
	private java.lang.String[] prop_names;

	public SpecifiedProps ()
	{
	}

	public org.omg.CosTrading.LookupPackage.HowManyProps discriminator ()
	{
		return discriminator;
	}

	public java.lang.String[] prop_names ()
	{
		if (discriminator != org.omg.CosTrading.LookupPackage.HowManyProps.some)
			throw new org.omg.CORBA.BAD_OPERATION();
		return prop_names;
	}

	public void prop_names (java.lang.String[] _x)
	{
		discriminator = org.omg.CosTrading.LookupPackage.HowManyProps.some;
		prop_names = _x;
	}

	public void __default ()
	{
		discriminator = org.omg.CosTrading.LookupPackage.HowManyProps.none;
	}
	public void __default (org.omg.CosTrading.LookupPackage.HowManyProps _discriminator)
	{
		if(  _discriminator == org.omg.CosTrading.LookupPackage.HowManyProps.some )
			throw new org.omg.CORBA.BAD_PARAM( "Illegal value is used in __default method", 34, org.omg.CORBA.CompletionStatus.COMPLETED_NO );

		discriminator = _discriminator;
	}
}
