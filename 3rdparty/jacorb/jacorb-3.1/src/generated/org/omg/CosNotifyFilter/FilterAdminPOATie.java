package org.omg.CosNotifyFilter;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "FilterAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class FilterAdminPOATie
	extends FilterAdminPOA
{
	private FilterAdminOperations _delegate;

	private POA _poa;
	public FilterAdminPOATie(FilterAdminOperations delegate)
	{
		_delegate = delegate;
	}
	public FilterAdminPOATie(FilterAdminOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyFilter.FilterAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyFilter.FilterAdmin __r = org.omg.CosNotifyFilter.FilterAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyFilter.FilterAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyFilter.FilterAdmin __r = org.omg.CosNotifyFilter.FilterAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public FilterAdminOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(FilterAdminOperations delegate)
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
	public int add_filter(org.omg.CosNotifyFilter.Filter new_filter)
	{
		return _delegate.add_filter(new_filter);
	}

	public void remove_all_filters()
	{
_delegate.remove_all_filters();
	}

	public org.omg.CosNotifyFilter.Filter get_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
		return _delegate.get_filter(filter);
	}

	public int[] get_all_filters()
	{
		return _delegate.get_all_filters();
	}

	public void remove_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
_delegate.remove_filter(filter);
	}

}
