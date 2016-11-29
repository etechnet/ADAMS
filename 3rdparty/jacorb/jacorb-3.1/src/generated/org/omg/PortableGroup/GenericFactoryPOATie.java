package org.omg.PortableGroup;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "GenericFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class GenericFactoryPOATie
	extends GenericFactoryPOA
{
	private GenericFactoryOperations _delegate;

	private POA _poa;
	public GenericFactoryPOATie(GenericFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public GenericFactoryPOATie(GenericFactoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.PortableGroup.GenericFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.PortableGroup.GenericFactory __r = org.omg.PortableGroup.GenericFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.PortableGroup.GenericFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.PortableGroup.GenericFactory __r = org.omg.PortableGroup.GenericFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public GenericFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(GenericFactoryOperations delegate)
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
	public org.omg.CORBA.Object create_object(java.lang.String type_id, org.omg.PortableGroup.Property[] the_criteria, org.omg.CORBA.AnyHolder factory_creation_id) throws org.omg.PortableGroup.InvalidProperty,org.omg.PortableGroup.ObjectNotCreated,org.omg.PortableGroup.InvalidCriteria,org.omg.PortableGroup.CannotMeetCriteria,org.omg.PortableGroup.NoFactory
	{
		return _delegate.create_object(type_id,the_criteria,factory_creation_id);
	}

	public void delete_object(org.omg.CORBA.Any factory_creation_id) throws org.omg.PortableGroup.ObjectNotFound
	{
_delegate.delete_object(factory_creation_id);
	}

}
