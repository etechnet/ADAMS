package org.omg.ETF;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class BufferListIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("add_buffer", "(in:size ,inout:buf org.omg.ETF.Buffer)");
		irInfo.put("release_buffers", "()");
		irInfo.put("get_buffer", "(in:index ,inout:buf org.omg.ETF.Buffer)");
		irInfo.put("num_buffers", "attribute;int");
	}
}
