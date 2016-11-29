package org.omg.PortableServer;

/**
 * Generated from IDL interface "IdAssignmentPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class IdAssignmentPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public IdAssignmentPolicy value;
	public IdAssignmentPolicyHolder()
	{
	}
	public IdAssignmentPolicyHolder (final IdAssignmentPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return IdAssignmentPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IdAssignmentPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		IdAssignmentPolicyHelper.write (_out,value);
	}
}
