package org.omg.CosTransactions;


/**
 * Generated from IDL interface "RecoveryCoordinator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class RecoveryCoordinatorPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTransactions.RecoveryCoordinatorOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "replay_completion", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:omg.org/CosTransactions/RecoveryCoordinator:1.0"};
	public org.omg.CosTransactions.RecoveryCoordinator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTransactions.RecoveryCoordinator __r = org.omg.CosTransactions.RecoveryCoordinatorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTransactions.RecoveryCoordinator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTransactions.RecoveryCoordinator __r = org.omg.CosTransactions.RecoveryCoordinatorHelper.narrow(__o);
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
			case 0: // replay_completion
			{
			try
			{
				org.omg.CosTransactions.Resource _arg0=org.omg.CosTransactions.ResourceHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosTransactions.StatusHelper.write(_out,replay_completion(_arg0));
			}
			catch(org.omg.CosTransactions.NotPrepared _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.NotPreparedHelper.write(_out, _ex0);
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
