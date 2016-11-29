package org.omg.PortableGroup;


/**
 * Generated from IDL interface "PropertyManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public abstract class PropertyManagerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.PortableGroup.PropertyManagerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "remove_default_properties", Integer.valueOf(0));
		m_opsHash.put ( "set_properties_dynamically", Integer.valueOf(1));
		m_opsHash.put ( "get_default_properties", Integer.valueOf(2));
		m_opsHash.put ( "get_type_properties", Integer.valueOf(3));
		m_opsHash.put ( "set_type_properties", Integer.valueOf(4));
		m_opsHash.put ( "get_properties", Integer.valueOf(5));
		m_opsHash.put ( "set_default_properties", Integer.valueOf(6));
		m_opsHash.put ( "remove_type_properties", Integer.valueOf(7));
	}
	private String[] ids = {"IDL:omg.org/PortableGroup/PropertyManager:1.0"};
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
			case 0: // remove_default_properties
			{
			try
			{
				org.omg.PortableGroup.Property[] _arg0=org.omg.PortableGroup.PropertiesHelper.read(_input);
				_out = handler.createReply();
				remove_default_properties(_arg0);
			}
			catch(org.omg.PortableGroup.InvalidProperty _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidPropertyHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.UnsupportedProperty _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.UnsupportedPropertyHelper.write(_out, _ex1);
			}
				break;
			}
			case 1: // set_properties_dynamically
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				org.omg.PortableGroup.Property[] _arg1=org.omg.PortableGroup.PropertiesHelper.read(_input);
				_out = handler.createReply();
				set_properties_dynamically(_arg0,_arg1);
			}
			catch(org.omg.PortableGroup.InvalidProperty _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidPropertyHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex1);
			}
			catch(org.omg.PortableGroup.UnsupportedProperty _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.UnsupportedPropertyHelper.write(_out, _ex2);
			}
				break;
			}
			case 2: // get_default_properties
			{
				_out = handler.createReply();
				org.omg.PortableGroup.PropertiesHelper.write(_out,get_default_properties());
				break;
			}
			case 3: // get_type_properties
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.PortableGroup.PropertiesHelper.write(_out,get_type_properties(_arg0));
				break;
			}
			case 4: // set_type_properties
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.PortableGroup.Property[] _arg1=org.omg.PortableGroup.PropertiesHelper.read(_input);
				_out = handler.createReply();
				set_type_properties(_arg0,_arg1);
			}
			catch(org.omg.PortableGroup.InvalidProperty _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidPropertyHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.UnsupportedProperty _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.UnsupportedPropertyHelper.write(_out, _ex1);
			}
				break;
			}
			case 5: // get_properties
			{
			try
			{
				org.omg.CORBA.Object _arg0=_input.read_Object();
				_out = handler.createReply();
				org.omg.PortableGroup.PropertiesHelper.write(_out,get_properties(_arg0));
			}
			catch(org.omg.PortableGroup.ObjectGroupNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.ObjectGroupNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 6: // set_default_properties
			{
			try
			{
				org.omg.PortableGroup.Property[] _arg0=org.omg.PortableGroup.PropertiesHelper.read(_input);
				_out = handler.createReply();
				set_default_properties(_arg0);
			}
			catch(org.omg.PortableGroup.InvalidProperty _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidPropertyHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.UnsupportedProperty _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.UnsupportedPropertyHelper.write(_out, _ex1);
			}
				break;
			}
			case 7: // remove_type_properties
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				org.omg.PortableGroup.Property[] _arg1=org.omg.PortableGroup.PropertiesHelper.read(_input);
				_out = handler.createReply();
				remove_type_properties(_arg0,_arg1);
			}
			catch(org.omg.PortableGroup.InvalidProperty _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.InvalidPropertyHelper.write(_out, _ex0);
			}
			catch(org.omg.PortableGroup.UnsupportedProperty _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.PortableGroup.UnsupportedPropertyHelper.write(_out, _ex1);
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
