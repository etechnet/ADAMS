package org.omg.CORBA;

/**
 * Generated from IDL interface "AliasDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class AliasDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public AliasDef value;
	public AliasDefHolder()
	{
	}
	public AliasDefHolder (final AliasDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return AliasDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AliasDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AliasDefHelper.write (_out,value);
	}
}
