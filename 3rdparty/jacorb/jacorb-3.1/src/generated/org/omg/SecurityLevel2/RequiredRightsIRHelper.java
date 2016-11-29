package org.omg.SecurityLevel2;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class RequiredRightsIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_required_rights", "(in:obj ,in:operation_name org.omg.CORBA.Identifier,in:interface_name org.omg.CORBA.RepositoryId,out:rights org.omg.Security.RightsList,out:rights_combinator )");
		irInfo.put("set_required_rights", "(in:operation_name org.omg.CORBA.Identifier,in:interface_name org.omg.CORBA.RepositoryId,in:rights org.omg.Security.RightsList,in:rights_combinator )");
	}
}
