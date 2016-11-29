package org.omg.CosNotification;


/**
 * Generated from IDL interface "QoSAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface QoSAdminOperations
{
	/* constants */
	/* operations  */
	org.omg.CosNotification.Property[] get_qos();
	void set_qos(org.omg.CosNotification.Property[] qos) throws org.omg.CosNotification.UnsupportedQoS;
	void validate_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS;
}
