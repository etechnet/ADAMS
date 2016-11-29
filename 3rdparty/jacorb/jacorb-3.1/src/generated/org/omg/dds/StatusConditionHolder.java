package org.omg.dds;

/**
 * Generated from IDL interface "StatusCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class StatusConditionHolder	implements org.omg.CORBA.portable.Streamable{
	 public StatusCondition value;
	public StatusConditionHolder()
	{
	}
	public StatusConditionHolder (final StatusCondition initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StatusConditionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StatusConditionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StatusConditionHelper.write (_out,value);
	}
}
