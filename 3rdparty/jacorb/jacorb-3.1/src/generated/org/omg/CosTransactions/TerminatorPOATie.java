package org.omg.CosTransactions;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Terminator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class TerminatorPOATie
	extends TerminatorPOA
{
	private TerminatorOperations _delegate;

	private POA _poa;
	public TerminatorPOATie(TerminatorOperations delegate)
	{
		_delegate = delegate;
	}
	public TerminatorPOATie(TerminatorOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTransactions.Terminator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTransactions.Terminator __r = org.omg.CosTransactions.TerminatorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTransactions.Terminator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTransactions.Terminator __r = org.omg.CosTransactions.TerminatorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TerminatorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TerminatorOperations delegate)
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
	public void rollback()
	{
_delegate.rollback();
	}

	public void commit(boolean report_heuristics) throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed
	{
_delegate.commit(report_heuristics);
	}

}
