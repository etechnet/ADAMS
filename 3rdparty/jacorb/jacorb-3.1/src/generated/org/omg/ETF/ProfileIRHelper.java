package org.omg.ETF;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ProfileIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("tag", "attribute;int");
		irInfo.put("version", "attribute;org.omg.GIOP.Version");
		irInfo.put("marshal", "(inout:tagged_profile ,inout:components org.omg.IOP.TaggedComponentSeq)");
		irInfo.put("set_object_key", "(in:key org.omg.ETF.ObjectKey)");
		irInfo.put("hash", "()");
		irInfo.put("get_object_key", "org.omg.ETF.ObjectKey()");
		irInfo.put("is_match", "(in:prof )");
		irInfo.put("copy", "()");
	}
}
