package org.jacorb.imr;

/**
 * Generated from IDL interface "ImplementationRepository".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class ImplementationRepositoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public ImplementationRepository value;
	public ImplementationRepositoryHolder()
	{
	}
	public ImplementationRepositoryHolder (final ImplementationRepository initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ImplementationRepositoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ImplementationRepositoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ImplementationRepositoryHelper.write (_out,value);
	}
}
