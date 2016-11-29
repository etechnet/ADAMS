package org.omg.CosTransactions;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Resource".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class ResourcePOATie
	extends ResourcePOA
{
	private ResourceOperations _delegate;

	private POA _poa;
	public ResourcePOATie(ResourceOperations delegate)
	{
		_delegate = delegate;
	}
	public ResourcePOATie(ResourceOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTransactions.Resource _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTransactions.Resource __r = org.omg.CosTransactions.ResourceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTransactions.Resource _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTransactions.Resource __r = org.omg.CosTransactions.ResourceHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ResourceOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ResourceOperations delegate)
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
	public void rollback() throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed,org.omg.CosTransactions.HeuristicCommit
	{
_delegate.rollback();
	}

	public void commit() throws org.omg.CosTransactions.NotPrepared,org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed,org.omg.CosTransactions.HeuristicRollback
	{
_delegate.commit();
	}

	public org.omg.CosTransactions.Vote prepare() throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed
	{
		return _delegate.prepare();
	}

	public void forget()
	{
_delegate.forget();
	}

	public void commit_one_phase() throws org.omg.CosTransactions.HeuristicHazard
	{
_delegate.commit_one_phase();
	}

}
