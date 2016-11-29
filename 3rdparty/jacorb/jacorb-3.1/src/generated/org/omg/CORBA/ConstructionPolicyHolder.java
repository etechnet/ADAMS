package org.omg.CORBA;

/**
 * Generated from IDL interface "ConstructionPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ConstructionPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public ConstructionPolicy value;
	public ConstructionPolicyHolder()
	{
	}
	public ConstructionPolicyHolder (final ConstructionPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ConstructionPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ConstructionPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ConstructionPolicyHelper.write (_out,value);
	}
}
