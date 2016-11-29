package org.omg.CosNaming;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class NamingContextIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("bind_new_context", "(in:n org.omg.CosNaming.Name)");
		irInfo.put("new_context", "()");
		irInfo.put("list", "(in:how_many ,out:bl org.omg.CosNaming.BindingList,out:bi )");
		irInfo.put("bind", "(in:n org.omg.CosNaming.Name,in:obj )");
		irInfo.put("rebind_context", "(in:n org.omg.CosNaming.Name,in:nc )");
		irInfo.put("destroy", "()");
		irInfo.put("rebind", "(in:n org.omg.CosNaming.Name,in:obj )");
		irInfo.put("bind_context", "(in:n org.omg.CosNaming.Name,in:nc )");
		irInfo.put("resolve", "(in:n org.omg.CosNaming.Name)");
		irInfo.put("unbind", "(in:n org.omg.CosNaming.Name)");
	}
}
