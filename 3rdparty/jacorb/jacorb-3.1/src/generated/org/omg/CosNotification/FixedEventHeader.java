package org.omg.CosNotification;

/**
 * Generated from IDL struct "FixedEventHeader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class FixedEventHeader
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public FixedEventHeader(){}
	public org.omg.CosNotification.EventType event_type;
	public java.lang.String event_name = "";
	public FixedEventHeader(org.omg.CosNotification.EventType event_type, java.lang.String event_name)
	{
		this.event_type = event_type;
		this.event_name = event_name;
	}
}
