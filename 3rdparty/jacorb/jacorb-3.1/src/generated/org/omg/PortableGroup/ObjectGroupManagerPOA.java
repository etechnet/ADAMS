package org.omg.PortableGroup;


/**
 * Generated from IDL interface "ObjectGroupManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public abstract class ObjectGroupManagerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.PortableGroup.ObjectGroupManagerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "create_member", Integer.valueOf(0));
		m_opsHash.put ( "locations_of_members", Integer.valueOf(1));
		m_opsHash.put ( "get_member_ref", Integer.valueOf(2));
		m_opsHash.put ( "get_object_group_id", Integer.valueOf(3));
		m_opsHash.put ( "get_object_group_ref", Integer.valueOf(4));
		m_opsHash.put ( "remove_member", Integer.valueOf(5));
		m_opsHash.put ( "add_member", Integer.valueOf(6));
	}
	private String[] ids = {"IDL:omg.org/PortableGroup/ObjectGroupManager:1.0"};
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
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // create_member
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				org.omg.CosNaming.NameComponent[] _arg1=org.omg.CosNaming.NameHelper.read(_input);
				java.lang.String _arg2=_input.read_string();
				org.omg.PortableGroup.Property[] _arg3=org.omg.PortableGroup.PropertiesHelper.read(_input);
				_out = handler.createReply();
				_out.write_Object(create_member(_arg0,_arg1,_arg2,_arg3));
			}
			catch(org.omg.PortableGroup.ObjectNotCreated _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectNotCreatedHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.MemberAlreadyPresent _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.MemberAlreadyPresentHelper.write(_out, _ex1);
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex2);
			}
			catch(org.omg.PortableGroup.InvalidCriteria _ex3)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidCriteriaHelper.write(_out, _ex3);
			}
			catch(org.omg.PortableGroup.CannotMeetCriteria _ex4)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.CannotMeetCriteriaHelper.write(_out, _ex4);
			}
			catch(org.omg.PortableGroup.NoFactory _ex5)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.NoFactoryHelper.write(_out, _ex5);
			}
				break;
			}
			case 1: // locations_of_members
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				_out = handler.createReply();
				org.omg.PortableGroup.LocationsHelper.write(_out,locations_of_members(_arg0));
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // get_member_ref
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				org.omg.CosNaming.NameComponent[] _arg1=org.omg.CosNaming.NameHelper.read(_input);
				_out = handler.createReply();
				_out.write_Object(get_member_ref(_arg0,_arg1));
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.MemberNotFound _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.MemberNotFoundHelper.write(_out, _ex1);
			}
				break;
			}
			case 3: // get_object_group_id
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				_out = handler.createReply();
				_out.write_ulonglong(get_object_group_id(_arg0));
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 4: // get_object_group_ref
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				_out = handler.createReply();
				_out.write_Object(get_object_group_ref(_arg0));
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 5: // remove_member
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				org.omg.CosNaming.NameComponent[] _arg1=org.omg.CosNaming.NameHelper.read(_input);
				_out = handler.createReply();
				_out.write_Object(remove_member(_arg0,_arg1));
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.MemberNotFound _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.MemberNotFoundHelper.write(_out, _ex1);
			}
				break;
			}
			case 6: // add_member
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				org.omg.CosNaming.NameComponent[] _arg1=org.omg.CosNaming.NameHelper.read(_input);
				org.omg.CORBA.Object _arg2=_input.read_Object();
				_out = handler.createReply();
				_out.write_Object(add_member(_arg0,_arg1,_arg2));
			}
			catch(org.omg.CORBA.INV_OBJREF _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CORBA.INV_OBJREFHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.MemberAlreadyPresent _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.MemberAlreadyPresentHelper.write(_out, _ex1);
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex2);
			}
			catch(org.omg.PortableGroup.ObjectNotAdded _ex3)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectNotAddedHelper.write(_out, _ex3);
			}
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
