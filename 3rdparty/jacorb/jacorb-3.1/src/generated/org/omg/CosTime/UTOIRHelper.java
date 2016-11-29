package org.omg.CosTime;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class UTOIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("interval", "()");
		irInfo.put("time_to_interval", "(in:uto_ )");
		irInfo.put("compare_time", "(in:comparison_type ,in:uto_ )");
		irInfo.put("utc_time", "attribute;org.omg.TimeBase.UtcT");
		irInfo.put("inaccuracy", "attribute;org.omg.TimeBase.InaccuracyT");
		irInfo.put("absolute_time", "()");
		irInfo.put("tdf", "attribute;org.omg.TimeBase.TdfT");
		irInfo.put("time", "attribute;org.omg.TimeBase.TimeT");
	}
}
