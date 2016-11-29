package org.omg.CosTransactions;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "SubtransactionAwareResource".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class SubtransactionAwareResourcePOATie
	extends SubtransactionAwareResourcePOA
{
	private SubtransactionAwareResourceOperations _delegate;

	private POA _poa;
	public SubtransactionAwareResourcePOATie(SubtransactionAwareResourceOperations delegate)
	{
		_delegate = delegate;
	}
	public SubtransactionAwareResourcePOATie(SubtransactionAwareResourceOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTransactions.SubtransactionAwareResource _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTransactions.SubtransactionAwareResource __r = org.omg.CosTransactions.SubtransactionAwareResourceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTransactions.SubtransactionAwareResource _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTransactions.SubtransactionAwareResource __r = org.omg.CosTransactions.SubtransactionAwareResourceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public SubtransactionAwareResourceOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(SubtransactionAwareResourceOperations delegate)
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
	public void rollback_subtransaction()
	{
_delegate.rollback_subtransaction();
	}

	public void rollback() throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed,org.omg.CosTransactions.HeuristicCommit
	{
_delegate.rollback();
	}

	public void commit_subtransaction(org.omg.CosTransactions.Coordinator parent)
	{
_delegate.commit_subtransaction(parent);
	}

	public void commit() throws org.omg.CosTransactions.NotPrepared,org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed,org.omg.CosTransactions.HeuristicRollback
	{
_delegate.commit();
	}

	public org.omg.CosTransactions.Vote prepare() throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed
	{
		return _delegate.prepare();
	}

	public void commit_one_phase() throws org.omg.CosTransactions.HeuristicHazard
	{
_delegate.commit_one_phase();
	}

	public void forget()
	{
_delegate.forget();
	}

}
