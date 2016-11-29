package org.omg.Messaging;

/**
 * Generated from IDL valuetype "Poller".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public abstract class Poller
	implements org.omg.CORBA.portable.StreamableValue, org.omg.CORBA.Pollable
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _truncatable_ids = {"IDL:omg.org/Messaging/Poller:1.0"};
	protected org.omg.CORBA.Object target;
	protected java.lang.String op_name = "";
	public abstract org.omg.CORBA.Object operation_target();

	public abstract java.lang.String operation_name();

	public abstract org.omg.Messaging.ReplyHandler associated_handler();

	public abstract void associated_handler(org.omg.Messaging.ReplyHandler arg);

	public abstract boolean is_from_poller();

	public void _write (org.omg.CORBA.portable.OutputStream os)
	{
		os.write_Object(target);
		java.lang.String tmpResult980 = op_name;
os.write_string( tmpResult980 );
	}

	public void _read (final org.omg.CORBA.portable.InputStream os)
	{
		target=os.read_Object();
		op_name=os.read_string();
	}

	public String[] _truncatable_ids()
	{
		return _truncatable_ids;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return org.omg.Messaging.PollerHelper.type();
	}
}
