package org.omg.Security;
/**
 * Generated from IDL enum "SecurityFeature".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SecurityFeature
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecNoDelegation = 0;
	public static final SecurityFeature SecNoDelegation = new SecurityFeature(_SecNoDelegation);
	public static final int _SecSimpleDelegation = 1;
	public static final SecurityFeature SecSimpleDelegation = new SecurityFeature(_SecSimpleDelegation);
	public static final int _SecCompositeDelegation = 2;
	public static final SecurityFeature SecCompositeDelegation = new SecurityFeature(_SecCompositeDelegation);
	public static final int _SecNoProtection = 3;
	public static final SecurityFeature SecNoProtection = new SecurityFeature(_SecNoProtection);
	public static final int _SecIntegrity = 4;
	public static final SecurityFeature SecIntegrity = new SecurityFeature(_SecIntegrity);
	public static final int _SecConfidentiality = 5;
	public static final SecurityFeature SecConfidentiality = new SecurityFeature(_SecConfidentiality);
	public static final int _SecIntegrityAndConfidentiality = 6;
	public static final SecurityFeature SecIntegrityAndConfidentiality = new SecurityFeature(_SecIntegrityAndConfidentiality);
	public static final int _SecDetectReplay = 7;
	public static final SecurityFeature SecDetectReplay = new SecurityFeature(_SecDetectReplay);
	public static final int _SecDetectMisordering = 8;
	public static final SecurityFeature SecDetectMisordering = new SecurityFeature(_SecDetectMisordering);
	public static final int _SecEstablishTrustInTarget = 9;
	public static final SecurityFeature SecEstablishTrustInTarget = new SecurityFeature(_SecEstablishTrustInTarget);
	public static final int _SecEstablishTrustInClient = 10;
	public static final SecurityFeature SecEstablishTrustInClient = new SecurityFeature(_SecEstablishTrustInClient);
	public int value()
	{
		return value;
	}
	public static SecurityFeature from_int(int value)
	{
		switch (value) {
			case _SecNoDelegation: return SecNoDelegation;
			case _SecSimpleDelegation: return SecSimpleDelegation;
			case _SecCompositeDelegation: return SecCompositeDelegation;
			case _SecNoProtection: return SecNoProtection;
			case _SecIntegrity: return SecIntegrity;
			case _SecConfidentiality: return SecConfidentiality;
			case _SecIntegrityAndConfidentiality: return SecIntegrityAndConfidentiality;
			case _SecDetectReplay: return SecDetectReplay;
			case _SecDetectMisordering: return SecDetectMisordering;
			case _SecEstablishTrustInTarget: return SecEstablishTrustInTarget;
			case _SecEstablishTrustInClient: return SecEstablishTrustInClient;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecNoDelegation: return "SecNoDelegation";
			case _SecSimpleDelegation: return "SecSimpleDelegation";
			case _SecCompositeDelegation: return "SecCompositeDelegation";
			case _SecNoProtection: return "SecNoProtection";
			case _SecIntegrity: return "SecIntegrity";
			case _SecConfidentiality: return "SecConfidentiality";
			case _SecIntegrityAndConfidentiality: return "SecIntegrityAndConfidentiality";
			case _SecDetectReplay: return "SecDetectReplay";
			case _SecDetectMisordering: return "SecDetectMisordering";
			case _SecEstablishTrustInTarget: return "SecEstablishTrustInTarget";
			case _SecEstablishTrustInClient: return "SecEstablishTrustInClient";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected SecurityFeature(int i)
	{
		value = i;
	}
	/**
	 * Designate replacement object when deserialized from stream. See
	 * http://www.omg.org/docs/ptc/02-01-03.htm#Issue4271
	 *
	 * @throws java.io.ObjectStreamException
	 */
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
