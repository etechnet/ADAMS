package org.omg.CosTransactions;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class CoordinatorIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("hash_transaction", "()");
		irInfo.put("get_status", "()");
		irInfo.put("is_same_transaction", "(in:tc )");
		irInfo.put("hash_top_level_tran", "()");
		irInfo.put("get_parent_status", "()");
		irInfo.put("rollback_only", "()");
		irInfo.put("is_top_level_transaction", "()");
		irInfo.put("create_subtransaction", "()");
		irInfo.put("get_top_level_status", "()");
		irInfo.put("get_transaction_name", "()");
		irInfo.put("is_ancestor_transaction", "(in:tc )");
		irInfo.put("get_txcontext", "()");
		irInfo.put("is_related_transaction", "(in:tc )");
		irInfo.put("register_subtran_aware", "(in:r )");
		irInfo.put("register_resource", "(in:r )");
		irInfo.put("register_synchronization", "(in:sync )");
		irInfo.put("is_descendant_transaction", "(in:tc )");
	}
}
