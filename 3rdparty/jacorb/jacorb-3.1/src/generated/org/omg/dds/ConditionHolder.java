package org.omg.dds;

/**
 * Generated from IDL interface "Condition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConditionHolder	implements org.omg.CORBA.portable.Streamable{
	 public Condition value;
	public ConditionHolder()
	{
	}
	public ConditionHolder (final Condition initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ConditionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ConditionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ConditionHelper.write (_out,value);
	}
}
