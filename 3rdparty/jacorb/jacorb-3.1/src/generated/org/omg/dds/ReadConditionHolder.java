package org.omg.dds;

/**
 * Generated from IDL interface "ReadCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ReadConditionHolder	implements org.omg.CORBA.portable.Streamable{
	 public ReadCondition value;
	public ReadConditionHolder()
	{
	}
	public ReadConditionHolder (final ReadCondition initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ReadConditionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ReadConditionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ReadConditionHelper.write (_out,value);
	}
}
