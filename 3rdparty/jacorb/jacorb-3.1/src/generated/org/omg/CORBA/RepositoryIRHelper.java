package org.omg.CORBA;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class RepositoryIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("create_fixed", "(in:digits ,in:scale )");
		irInfo.put("get_primitive", "(in:kind )");
		irInfo.put("create_string", "(in:bound )");
		irInfo.put("lookup_id", "(in:search_id org.omg.CORBA.RepositoryId)");
		irInfo.put("create_array", "(in:length ,in:element_type )");
		irInfo.put("create_sequence", "(in:bound ,in:element_type )");
		irInfo.put("create_wstring", "(in:bound )");
		irInfo.put("get_canonical_typecode", "(in:tc )");
	}
}
