package org.jacorb.sasPolicy;

/**
 * Generated from IDL struct "ATLASPolicyValues".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ATLASPolicyValues
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ATLASPolicyValues(){}
	public java.lang.String atlasURL = "";
	public java.lang.String atlasCache = "";
	public ATLASPolicyValues(java.lang.String atlasURL, java.lang.String atlasCache)
	{
		this.atlasURL = atlasURL;
		this.atlasCache = atlasCache;
	}
}
