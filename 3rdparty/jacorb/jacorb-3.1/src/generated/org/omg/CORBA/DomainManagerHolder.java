package org.omg.CORBA;

/**
 * Generated from IDL interface "DomainManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class DomainManagerHolder	implements org.omg.CORBA.portable.Streamable{
	 public DomainManager value;
	public DomainManagerHolder()
	{
	}
	public DomainManagerHolder (final DomainManager initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DomainManagerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DomainManagerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DomainManagerHelper.write (_out,value);
	}
}
