package org.omg.CORBA;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ContainedIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("move", "(in:new_container ,in:new_name org.omg.CORBA.Identifier,in:new_version org.omg.CORBA.VersionSpec)");
		irInfo.put("defined_in", "attribute;org.omg.CORBA.Container");
		irInfo.put("version", "attribute-w;org.omg.CORBA.VersionSpec");
		irInfo.put("containing_repository", "attribute;org.omg.CORBA.Repository");
		irInfo.put("absolute_name", "attribute;org.omg.CORBA.ScopedName");
		irInfo.put("name", "attribute-w;org.omg.CORBA.Identifier");
		irInfo.put("describe", "()");
		irInfo.put("id", "attribute-w;org.omg.CORBA.RepositoryId");
	}
}
