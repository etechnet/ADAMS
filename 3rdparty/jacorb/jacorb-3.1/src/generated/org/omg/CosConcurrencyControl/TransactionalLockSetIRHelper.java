package org.omg.CosConcurrencyControl;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class TransactionalLockSetIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("change_mode", "(in:current ,in:held_mode ,in:new_mode )");
		irInfo.put("get_coordinator", "(in:which )");
		irInfo.put("lock", "(in:current ,in:mode )");
		irInfo.put("try_lock", "(in:current ,in:mode )");
		irInfo.put("unlock", "(in:current ,in:mode )");
	}
}
