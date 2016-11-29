package org.omg.CosConcurrencyControl;


/**
 * Generated from IDL interface "LockSetFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class LockSetFactoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosConcurrencyControl.LockSetFactoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "create_transactional", Integer.valueOf(0));
		m_opsHash.put ( "create_transactional_related", Integer.valueOf(1));
		m_opsHash.put ( "create_related", Integer.valueOf(2));
		m_opsHash.put ( "create", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:omg.org/CosConcurrencyControl/LockSetFactory:1.0"};
	public org.omg.CosConcurrencyControl.LockSetFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosConcurrencyControl.LockSetFactory __r = org.omg.CosConcurrencyControl.LockSetFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosConcurrencyControl.LockSetFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosConcurrencyControl.LockSetFactory __r = org.omg.CosConcurrencyControl.LockSetFactoryHelper.narrow(__o);
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
			case 0: // create_transactional
			{
				_out = handler.createReply();
				org.omg.CosConcurrencyControl.TransactionalLockSetHelper.write(_out,create_transactional());
				break;
			}
			case 1: // create_transactional_related
			{
				org.omg.CosConcurrencyControl.TransactionalLockSet _arg0=org.omg.CosConcurrencyControl.TransactionalLockSetHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosConcurrencyControl.TransactionalLockSetHelper.write(_out,create_transactional_related(_arg0));
				break;
			}
			case 2: // create_related
			{
				org.omg.CosConcurrencyControl.LockSet _arg0=org.omg.CosConcurrencyControl.LockSetHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosConcurrencyControl.LockSetHelper.write(_out,create_related(_arg0));
				break;
			}
			case 3: // create
			{
				_out = handler.createReply();
				org.omg.CosConcurrencyControl.LockSetHelper.write(_out,create());
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
