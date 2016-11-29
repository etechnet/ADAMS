package org.omg.CosNotifyFilter;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Filter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class FilterPOATie
	extends FilterPOA
{
	private FilterOperations _delegate;

	private POA _poa;
	public FilterPOATie(FilterOperations delegate)
	{
		_delegate = delegate;
	}
	public FilterPOATie(FilterOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyFilter.Filter _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyFilter.Filter __r = org.omg.CosNotifyFilter.FilterHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyFilter.Filter _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyFilter.Filter __r = org.omg.CosNotifyFilter.FilterHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public FilterOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(FilterOperations delegate)
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
	public boolean match(org.omg.CORBA.Any filterable_data) throws org.omg.CosNotifyFilter.UnsupportedFilterableData
	{
		return _delegate.match(filterable_data);
	}

	public int[] get_callbacks()
	{
		return _delegate.get_callbacks();
	}

	public void detach_callback(int callback) throws org.omg.CosNotifyFilter.CallbackNotFound
	{
_delegate.detach_callback(callback);
	}

	public org.omg.CosNotifyFilter.ConstraintInfo[] get_all_constraints()
	{
		return _delegate.get_all_constraints();
	}

	public int attach_callback(org.omg.CosNotifyComm.NotifySubscribe callback)
	{
		return _delegate.attach_callback(callback);
	}

	public boolean match_structured(org.omg.CosNotification.StructuredEvent filterable_data) throws org.omg.CosNotifyFilter.UnsupportedFilterableData
	{
		return _delegate.match_structured(filterable_data);
	}

	public org.omg.CosNotifyFilter.ConstraintInfo[] add_constraints(org.omg.CosNotifyFilter.ConstraintExp[] constraint_list) throws org.omg.CosNotifyFilter.InvalidConstraint
	{
		return _delegate.add_constraints(constraint_list);
	}

	public java.lang.String constraint_grammar()
	{
		return _delegate.constraint_grammar();
	}

	public void modify_constraints(int[] del_list, org.omg.CosNotifyFilter.ConstraintInfo[] modify_list) throws org.omg.CosNotifyFilter.ConstraintNotFound,org.omg.CosNotifyFilter.InvalidConstraint
	{
_delegate.modify_constraints(del_list,modify_list);
	}

	public void remove_all_constraints()
	{
_delegate.remove_all_constraints();
	}

	public boolean match_typed(org.omg.CosNotification.Property[] filterable_data) throws org.omg.CosNotifyFilter.UnsupportedFilterableData
	{
		return _delegate.match_typed(filterable_data);
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public org.omg.CosNotifyFilter.ConstraintInfo[] get_constraints(int[] id_list) throws org.omg.CosNotifyFilter.ConstraintNotFound
	{
		return _delegate.get_constraints(id_list);
	}

}
