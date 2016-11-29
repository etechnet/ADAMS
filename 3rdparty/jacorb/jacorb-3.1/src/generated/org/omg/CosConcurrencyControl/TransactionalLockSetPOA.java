package org.omg.CosConcurrencyControl;


/**
 * Generated from IDL interface "TransactionalLockSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class TransactionalLockSetPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosConcurrencyControl.TransactionalLockSetOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "try_lock", Integer.valueOf(0));
		m_opsHash.put ( "get_coordinator", Integer.valueOf(1));
		m_opsHash.put ( "change_mode", Integer.valueOf(2));
		m_opsHash.put ( "lock", Integer.valueOf(3));
		m_opsHash.put ( "unlock", Integer.valueOf(4));
	}
	private String[] ids = {"IDL:omg.org/CosConcurrencyControl/TransactionalLockSet:1.0"};
	public org.omg.CosConcurrencyControl.TransactionalLockSet _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosConcurrencyControl.TransactionalLockSet __r = org.omg.CosConcurrencyControl.TransactionalLockSetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosConcurrencyControl.TransactionalLockSet _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosConcurrencyControl.TransactionalLockSet __r = org.omg.CosConcurrencyControl.TransactionalLockSetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // try_lock
			{
				org.omg.CosTransactions.Coordinator _arg0=org.omg.CosTransactions.CoordinatorHelper.read(_input);
				org.omg.CosConcurrencyControl.lock_mode _arg1=org.omg.CosConcurrencyControl.lock_modeHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(try_lock(_arg0,_arg1));
				break;
			}
			case 1: // get_coordinator
			{
				org.omg.CosTransactions.Coordinator _arg0=org.omg.CosTransactions.CoordinatorHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosConcurrencyControl.LockCoordinatorHelper.write(_out,get_coordinator(_arg0));
				break;
			}
			case 2: // change_mode
			{
			try
			{
				org.omg.CosTransactions.Coordinator _arg0=org.omg.CosTransactions.CoordinatorHelper.read(_input);
				org.omg.CosConcurrencyControl.lock_mode _arg1=org.omg.CosConcurrencyControl.lock_modeHelper.read(_input);
				org.omg.CosConcurrencyControl.lock_mode _arg2=org.omg.CosConcurrencyControl.lock_modeHelper.read(_input);
				_out = handler.createReply();
				change_mode(_arg0,_arg1,_arg2);
			}
			catch(org.omg.CosConcurrencyControl.LockNotHeld _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosConcurrencyControl.LockNotHeldHelper.write(_out, _ex0);
			}
				break;
			}
			case 3: // lock
			{
				org.omg.CosTransactions.Coordinator _arg0=org.omg.CosTransactions.CoordinatorHelper.read(_input);
				org.omg.CosConcurrencyControl.lock_mode _arg1=org.omg.CosConcurrencyControl.lock_modeHelper.read(_input);
				_out = handler.createReply();
				lock(_arg0,_arg1);
				break;
			}
			case 4: // unlock
			{
			try
			{
				org.omg.CosTransactions.Coordinator _arg0=org.omg.CosTransactions.CoordinatorHelper.read(_input);
				org.omg.CosConcurrencyControl.lock_mode _arg1=org.omg.CosConcurrencyControl.lock_modeHelper.read(_input);
				_out = handler.createReply();
				unlock(_arg0,_arg1);
			}
			catch(org.omg.CosConcurrencyControl.LockNotHeld _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosConcurrencyControl.LockNotHeldHelper.write(_out, _ex0);
			}
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
