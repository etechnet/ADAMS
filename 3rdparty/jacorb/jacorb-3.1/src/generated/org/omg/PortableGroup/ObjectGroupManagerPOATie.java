package org.omg.PortableGroup;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ObjectGroupManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class ObjectGroupManagerPOATie
	extends ObjectGroupManagerPOA
{
	private ObjectGroupManagerOperations _delegate;

	private POA _poa;
	public ObjectGroupManagerPOATie(ObjectGroupManagerOperations delegate)
	{
		_delegate = delegate;
	}
	public ObjectGroupManagerPOATie(ObjectGroupManagerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.PortableGroup.ObjectGroupManager _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.PortableGroup.ObjectGroupManager __r = org.omg.PortableGroup.ObjectGroupManagerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.PortableGroup.ObjectGroupManager _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.PortableGroup.ObjectGroupManager __r = org.omg.PortableGroup.ObjectGroupManagerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ObjectGroupManagerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ObjectGroupManagerOperations delegate)
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
	public org.omg.CORBA.Object create_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location, java.lang.String type_id, org.omg.PortableGroup.Property[] the_criteria) throws org.omg.PortableGroup.ObjectNotCreated,org.omg.PortableGroup.MemberAlreadyPresent,org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.InvalidCriteria,org.omg.PortableGroup.CannotMeetCriteria,org.omg.PortableGroup.NoFactory
	{
		return _delegate.create_member(object_group,the_location,type_id,the_criteria);
	}

	public org.omg.CosNaming.NameComponent[][] locations_of_members(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound
	{
		return _delegate.locations_of_members(object_group);
	}

	public org.omg.CORBA.Object get_member_ref(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] loc) throws org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.MemberNotFound
	{
		return _delegate.get_member_ref(object_group,loc);
	}

	public long get_object_group_id(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound
	{
		return _delegate.get_object_group_id(object_group);
	}

	public org.omg.CORBA.Object get_object_group_ref(org.omg.CORBA.Object object_group) throws org.omg.PortableGroup.ObjectGroupNotFound
	{
		return _delegate.get_object_group_ref(object_group);
	}

	public org.omg.CORBA.Object remove_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location) throws org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.MemberNotFound
	{
		return _delegate.remove_member(object_group,the_location);
	}

	public org.omg.CORBA.Object add_member(org.omg.CORBA.Object object_group, org.omg.CosNaming.NameComponent[] the_location, org.omg.CORBA.Object member) throws org.omg.CORBA.INV_OBJREF,org.omg.PortableGroup.MemberAlreadyPresent,org.omg.PortableGroup.ObjectGroupNotFound,org.omg.PortableGroup.ObjectNotAdded
	{
		return _delegate.add_member(object_group,the_location,member);
	}

}
