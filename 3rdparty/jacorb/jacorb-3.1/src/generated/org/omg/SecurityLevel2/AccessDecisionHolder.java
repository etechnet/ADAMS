package org.omg.SecurityLevel2;

/**
 * Generated from IDL interface "AccessDecision".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class AccessDecisionHolder	implements org.omg.CORBA.portable.Streamable{
	 public AccessDecision value;
	public AccessDecisionHolder()
	{
	}
	public AccessDecisionHolder (final AccessDecision initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return AccessDecisionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AccessDecisionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AccessDecisionHelper.write (_out,value);
	}
}
