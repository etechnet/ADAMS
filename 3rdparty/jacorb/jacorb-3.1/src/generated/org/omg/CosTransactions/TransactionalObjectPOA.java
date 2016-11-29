package org.omg.CosTransactions;


/**
 * Generated from IDL interface "TransactionalObject".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class TransactionalObjectPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosTransactions.TransactionalObjectOperations
{
	private String[] ids = {"IDL:omg.org/CosTransactions/TransactionalObject:1.0"};
	public org.omg.CosTransactions.TransactionalObject _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTransactions.TransactionalObject __r = org.omg.CosTransactions.TransactionalObjectHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTransactions.TransactionalObject _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTransactions.TransactionalObject __r = org.omg.CosTransactions.TransactionalObjectHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
