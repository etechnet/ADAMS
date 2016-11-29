package org.omg.CORBA;

/**
 * Generated from IDL interface "OperationDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class OperationDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public OperationDef value;
	public OperationDefHolder()
	{
	}
	public OperationDefHolder (final OperationDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return OperationDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = OperationDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		OperationDefHelper.write (_out,value);
	}
}
