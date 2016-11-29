package org.omg.PortableGroup;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "PropertyManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class PropertyManagerPOATie
	extends PropertyManagerPOA
{
	private PropertyManagerOperations _delegate;

	private POA _poa;
	public PropertyManagerPOATie(PropertyManagerOperations delegate)
	{
		_delegate = delegate;
	}
	public PropertyManagerPOATie(PropertyManagerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.PortableGroup.PropertyManager _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.PortableGroup.PropertyManager __r = org.omg.PortableGroup.PropertyManagerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.PortableGroup.PropertyManager _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.PortableGroup.PropertyManager __r = org.omg.PortableGroup.PropertyManagerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public PropertyManagerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PropertyManagerOperations delegate)
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
	public void remove_default_properties(org.omg.PortableGroup.Property[] props) throws org.omg.PortableGroup.InvalidProperty,org.omg.PortableGroup.UnsupportedProperty
	{
_delegate.remove_default_properties(props);
	}

	public void set_properties_dynamically(org.omg.CORBA.Object object_group, org.omg.PortableGroup.Property[] overrides) throws org.omg.PortableGroup.InvalidProperty,org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.UnsupportedProperty
	{
_delegate.set_properties_dynamically(object_group,overrides);
	}

	public org.omg.PortableGroup.Property[] get_default_properties()
	{
		return _delegate.get_default_properties();
	}

	public org.omg.PortableGroup.Property[] get_type_properties(java.lang.String type_id)
	{
		return _delegate.get_type_properties(type_id);
	}

	public void set_type_properties(java.lang.String type_id, org.omg.PortableGroup.Property[] overrides) throws org.omg.PortableGroup.InvalidProperty,org.omg.PortableGroup.UnsupportedProperty
	{
_delegate.set_type_properties(type_id,overrides);
	}

	public org.omg.PortableGroup.Property[] get_properties(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound
	{
		return _delegate.get_properties(object_group);
	}

	public void set_default_properties(org.omg.PortableGroup.Property[] props) throws org.omg.PortableGroup.InvalidProperty,org.omg.PortableGroup.UnsupportedProperty
	{
_delegate.set_default_properties(props);
	}

	public void remove_type_properties(java.lang.String type_id, org.omg.PortableGroup.Property[] props) throws org.omg.PortableGroup.InvalidProperty,org.omg.PortableGroup.UnsupportedProperty
	{
_delegate.remove_type_properties(type_id,props);
	}

}
