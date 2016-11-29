package org.omg.PortableGroup;


/**
 * Generated from IDL interface "GenericFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public abstract class GenericFactoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.PortableGroup.GenericFactoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "create_object", Integer.valueOf(0));
		m_opsHash.put ( "delete_object", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:omg.org/PortableGroup/GenericFactory:1.0"};
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
			case 0: // create_object
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.PortableGroup.Property[] _arg1=org.omg.PortableGroup.PropertiesHelper.read(_input);
				org.omg.CORBA.AnyHolder _arg2= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_Object(create_object(_arg0,_arg1,_arg2));
				_out.write_any(_arg2.value);
			}
			catch(org.omg.PortableGroup.InvalidProperty _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidPropertyHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.ObjectNotCreated _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectNotCreatedHelper.write(_out, _ex1);
			}
			catch(org.omg.PortableGroup.InvalidCriteria _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidCriteriaHelper.write(_out, _ex2);
			}
			catch(org.omg.PortableGroup.CannotMeetCriteria _ex3)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.CannotMeetCriteriaHelper.write(_out, _ex3);
			}
			catch(org.omg.PortableGroup.NoFactory _ex4)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.NoFactoryHelper.write(_out, _ex4);
			}
				break;
			}
			case 1: // delete_object
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				delete_object(_arg0);
			}
			catch(org.omg.PortableGroup.ObjectNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectNotFoundHelper.write(_out, _ex0);
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
