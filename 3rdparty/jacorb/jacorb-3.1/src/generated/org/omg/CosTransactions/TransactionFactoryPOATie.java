package org.omg.CosTransactions;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TransactionFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class TransactionFactoryPOATie
	extends TransactionFactoryPOA
{
	private TransactionFactoryOperations _delegate;

	private POA _poa;
	public TransactionFactoryPOATie(TransactionFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public TransactionFactoryPOATie(TransactionFactoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTransactions.TransactionFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTransactions.TransactionFactory __r = org.omg.CosTransactions.TransactionFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTransactions.TransactionFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTransactions.TransactionFactory __r = org.omg.CosTransactions.TransactionFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TransactionFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TransactionFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public org.omg.CosTransactions.Control create(int time_out)
	{
		return _delegate.create(time_out);
	}

	public org.omg.CosTransactions.Control recreate(org.omg.CosTransactions.PropagationContext ctx)
	{
		return _delegate.recreate(ctx);
	}

}
