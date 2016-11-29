package org.omg.CosTime;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class TimeServiceIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("uto_from_utc", "(in:utc )");
		irInfo.put("new_universal_time", "(in:time org.omg.TimeBase.TimeT,in:inaccuracy org.omg.TimeBase.InaccuracyT,in:tdf org.omg.TimeBase.TdfT)");
		irInfo.put("secure_universal_time", "()");
		irInfo.put("universal_time", "()");
		irInfo.put("new_interval", "(in:lower org.omg.TimeBase.TimeT,in:upper org.omg.TimeBase.TimeT)");
	}
}
