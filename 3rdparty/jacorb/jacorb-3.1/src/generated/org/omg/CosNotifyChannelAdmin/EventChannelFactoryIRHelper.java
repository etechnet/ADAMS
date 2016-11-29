package org.omg.CosNotifyChannelAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class EventChannelFactoryIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("create_channel", "(in:initial_qos org.omg.CosNotification.QoSProperties,in:initial_admin org.omg.CosNotification.AdminProperties,out:id org.omg.CosNotifyChannelAdmin.ChannelID)");
		irInfo.put("get_all_channels", "org.omg.CosNotifyChannelAdmin.ChannelIDSeq()");
		irInfo.put("get_event_channel", "(in:id org.omg.CosNotifyChannelAdmin.ChannelID)");
	}
}
