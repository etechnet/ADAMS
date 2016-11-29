package org.omg.CosConcurrencyControl;


/**
 * Generated from IDL interface "LockSetFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface LockSetFactoryOperations
{
	/* constants */
	/* operations  */
	org.omg.CosConcurrencyControl.LockSet create();
	org.omg.CosConcurrencyControl.LockSet create_related(org.omg.CosConcurrencyControl.LockSet which);
	org.omg.CosConcurrencyControl.TransactionalLockSet create_transactional();
	org.omg.CosConcurrencyControl.TransactionalLockSet create_transactional_related(org.omg.CosConcurrencyControl.TransactionalLockSet which);
}
