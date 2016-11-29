package org.omg.CosTypedNotifyChannelAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class TypedEventChannelFactoryIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("create_typed_channel", "(in:initial_QoS org.omg.CosNotification.QoSProperties,in:initial_admin org.omg.CosNotification.AdminProperties,out:id org.omg.CosNotifyChannelAdmin.ChannelID)");
		irInfo.put("get_all_typed_channels", "org.omg.CosNotifyChannelAdmin.ChannelIDSeq()");
		irInfo.put("get_typed_event_channel", "(in:id org.omg.CosNotifyChannelAdmin.ChannelID)");
	}
}
