package org.omg.CosConcurrencyControl;


/**
 * Generated from IDL interface "LockSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface LockSetOperations
{
	/* constants */
	/* operations  */
	void lock(org.omg.CosConcurrencyControl.lock_mode mode);
	boolean try_lock(org.omg.CosConcurrencyControl.lock_mode mode);
	void unlock(org.omg.CosConcurrencyControl.lock_mode mode) throws org.omg.CosConcurrencyControl.LockNotHeld;
	void change_mode(org.omg.CosConcurrencyControl.lock_mode held_mode, org.omg.CosConcurrencyControl.lock_mode new_mode) throws org.omg.CosConcurrencyControl.LockNotHeld;
	org.omg.CosConcurrencyControl.LockCoordinator get_coordinator(org.omg.CosTransactions.Coordinator which);
}
