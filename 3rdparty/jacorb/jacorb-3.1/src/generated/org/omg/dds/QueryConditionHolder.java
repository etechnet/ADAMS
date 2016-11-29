package org.omg.dds;

/**
 * Generated from IDL interface "QueryCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class QueryConditionHolder	implements org.omg.CORBA.portable.Streamable{
	 public QueryCondition value;
	public QueryConditionHolder()
	{
	}
	public QueryConditionHolder (final QueryCondition initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return QueryConditionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = QueryConditionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		QueryConditionHelper.write (_out,value);
	}
}
