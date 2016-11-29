package org.omg.CORBA;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class InterfaceDefIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("create_attribute", "(in:id org.omg.CORBA.RepositoryId,in:name org.omg.CORBA.Identifier,in:version org.omg.CORBA.VersionSpec,in:type ,in:mode )");
		irInfo.put("describe_interface", "()");
		irInfo.put("base_interfaces", "attribute-w;org.omg.CORBA.InterfaceDefSeq");
		irInfo.put("create_operation", "(in:id org.omg.CORBA.RepositoryId,in:name org.omg.CORBA.Identifier,in:version org.omg.CORBA.VersionSpec,in:result ,in:mode ,in:params org.omg.CORBA.ParDescriptionSeq,in:exceptions org.omg.CORBA.ExceptionDefSeq,in:contexts org.omg.CORBA.ContextIdSeq)");
		irInfo.put("is_a", "(in:interface_id org.omg.CORBA.RepositoryId)");
	}
}
