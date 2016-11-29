package org.omg.RTCORBA;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class MutexIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("lock", "()");
		irInfo.put("try_lock", "(in:max_wait org.omg.TimeBase.TimeT)");
		irInfo.put("unlock", "()");
	}
}
