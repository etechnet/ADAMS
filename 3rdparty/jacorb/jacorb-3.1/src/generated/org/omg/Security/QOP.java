package org.omg.Security;
/**
 * Generated from IDL enum "QOP".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class QOP
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private int value = -1;
	public static final int _SecQOPNoProtection = 0;
	public static final QOP SecQOPNoProtection = new QOP(_SecQOPNoProtection);
	public static final int _SecQOPIntegrity = 1;
	public static final QOP SecQOPIntegrity = new QOP(_SecQOPIntegrity);
	public static final int _SecQOPConfidentiality = 2;
	public static final QOP SecQOPConfidentiality = new QOP(_SecQOPConfidentiality);
	public static final int _SecQOPIntegrityAndConfidentiality = 3;
	public static final QOP SecQOPIntegrityAndConfidentiality = new QOP(_SecQOPIntegrityAndConfidentiality);
	public int value()
	{
		return value;
	}
	public static QOP from_int(int value)
	{
		switch (value) {
			case _SecQOPNoProtection: return SecQOPNoProtection;
			case _SecQOPIntegrity: return SecQOPIntegrity;
			case _SecQOPConfidentiality: return SecQOPConfidentiality;
			case _SecQOPIntegrityAndConfidentiality: return SecQOPIntegrityAndConfidentiality;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	public String toString()
	{
		switch (value) {
			case _SecQOPNoProtection: return "SecQOPNoProtection";
			case _SecQOPIntegrity: return "SecQOPIntegrity";
			case _SecQOPConfidentiality: return "SecQOPConfidentiality";
			case _SecQOPIntegrityAndConfidentiality: return "SecQOPIntegrityAndConfidentiality";
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected QOP(int i)
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
