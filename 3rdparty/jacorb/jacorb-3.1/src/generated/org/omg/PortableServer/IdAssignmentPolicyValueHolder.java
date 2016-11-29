package org.omg.PortableServer;
/**
 * Generated from IDL enum "IdAssignmentPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class IdAssignmentPolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public IdAssignmentPolicyValue value;

	public IdAssignmentPolicyValueHolder ()
	{
	}
	public IdAssignmentPolicyValueHolder (final IdAssignmentPolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return IdAssignmentPolicyValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IdAssignmentPolicyValueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		IdAssignmentPolicyValueHelper.write (out,value);
	}
}
