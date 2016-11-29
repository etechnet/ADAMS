package org.omg.CosTrading.LookupPackage;

/**
 * Generated from IDL exception "IllegalPreference".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalPreference
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public IllegalPreference()
	{
		super(org.omg.CosTrading.LookupPackage.IllegalPreferenceHelper.id());
	}

	public java.lang.String pref;
	public IllegalPreference(java.lang.String _reason,java.lang.String pref)
	{
		super(_reason);
		this.pref = pref;
	}
	public IllegalPreference(java.lang.String pref)
	{
		super(org.omg.CosTrading.LookupPackage.IllegalPreferenceHelper.id());
		this.pref = pref;
	}
}
