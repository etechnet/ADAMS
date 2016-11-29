package org.omg.SecurityLevel2;

/**
 * Generated from IDL interface "AuditDecision".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class AuditDecisionHolder	implements org.omg.CORBA.portable.Streamable{
	 public AuditDecision value;
	public AuditDecisionHolder()
	{
	}
	public AuditDecisionHolder (final AuditDecision initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return AuditDecisionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AuditDecisionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AuditDecisionHelper.write (_out,value);
	}
}
