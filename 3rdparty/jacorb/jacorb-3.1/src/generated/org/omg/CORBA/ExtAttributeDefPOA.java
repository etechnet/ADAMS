package org.omg.CORBA;


/**
 * Generated from IDL interface "ExtAttributeDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public abstract class ExtAttributeDefPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CORBA.ExtAttributeDefOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_get_type_def", Integer.valueOf(0));
		m_opsHash.put ( "_get_set_exceptions", Integer.valueOf(1));
		m_opsHash.put ( "_get_containing_repository", Integer.valueOf(2));
		m_opsHash.put ( "_get_def_kind", Integer.valueOf(3));
		m_opsHash.put ( "_set_type_def", Integer.valueOf(4));
		m_opsHash.put ( "_get_absolute_name", Integer.valueOf(5));
		m_opsHash.put ( "_get_name", Integer.valueOf(6));
		m_opsHash.put ( "_get_type", Integer.valueOf(7));
		m_opsHash.put ( "_set_name", Integer.valueOf(8));
		m_opsHash.put ( "_set_mode", Integer.valueOf(9));
		m_opsHash.put ( "_set_get_exceptions", Integer.valueOf(10));
		m_opsHash.put ( "_get_mode", Integer.valueOf(11));
		m_opsHash.put ( "_get_defined_in", Integer.valueOf(12));
		m_opsHash.put ( "_get_version", Integer.valueOf(13));
		m_opsHash.put ( "_set_version", Integer.valueOf(14));
		m_opsHash.put ( "describe", Integer.valueOf(15));
		m_opsHash.put ( "describe_attribute", Integer.valueOf(16));
		m_opsHash.put ( "_get_id", Integer.valueOf(17));
		m_opsHash.put ( "move", Integer.valueOf(18));
		m_opsHash.put ( "_get_get_exceptions", Integer.valueOf(19));
		m_opsHash.put ( "_set_set_exceptions", Integer.valueOf(20));
		m_opsHash.put ( "destroy", Integer.valueOf(21));
		m_opsHash.put ( "_set_id", Integer.valueOf(22));
	}
	private String[] ids = {"IDL:omg.org/CORBA/ExtAttributeDef:1.0","IDL:omg.org/CORBA/AttributeDef:1.0","IDL:omg.org/CORBA/Contained:1.0","IDL:omg.org/CORBA/IRObject:1.0"};
	public org.omg.CORBA.ExtAttributeDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.ExtAttributeDef __r = org.omg.CORBA.ExtAttributeDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.ExtAttributeDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.ExtAttributeDef __r = org.omg.CORBA.ExtAttributeDefHelper.narrow(__o);
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
			case 0: // _get_type_def
			{
			_out = handler.createReply();
			org.omg.CORBA.IDLTypeHelper.write(_out,type_def());
				break;
			}
			case 1: // _get_set_exceptions
			{
			_out = handler.createReply();
			org.omg.CORBA.ExcDescriptionSeqHelper.write(_out,set_exceptions());
				break;
			}
			case 2: // _get_containing_repository
			{
			_out = handler.createReply();
			org.omg.CORBA.RepositoryHelper.write(_out,containing_repository());
				break;
			}
			case 3: // _get_def_kind
			{
			_out = handler.createReply();
			org.omg.CORBA.DefinitionKindHelper.write(_out,def_kind());
				break;
			}
			case 4: // _set_type_def
			{
			_out = handler.createReply();
			type_def(org.omg.CORBA.IDLTypeHelper.read(_input));
				break;
			}
			case 5: // _get_absolute_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult402 = absolute_name();
_out.write_string( tmpResult402 );
				break;
			}
			case 6: // _get_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult403 = name();
_out.write_string( tmpResult403 );
				break;
			}
			case 7: // _get_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(type());
				break;
			}
			case 8: // _set_name
			{
			_out = handler.createReply();
			name(_input.read_string());
				break;
			}
			case 9: // _set_mode
			{
			_out = handler.createReply();
			mode(org.omg.CORBA.AttributeModeHelper.read(_input));
				break;
			}
			case 10: // _set_get_exceptions
			{
			_out = handler.createReply();
			get_exceptions(org.omg.CORBA.ExcDescriptionSeqHelper.read(_input));
				break;
			}
			case 11: // _get_mode
			{
			_out = handler.createReply();
			org.omg.CORBA.AttributeModeHelper.write(_out,mode());
				break;
			}
			case 12: // _get_defined_in
			{
			_out = handler.createReply();
			org.omg.CORBA.ContainerHelper.write(_out,defined_in());
				break;
			}
			case 13: // _get_version
			{
			_out = handler.createReply();
			java.lang.String tmpResult404 = version();
_out.write_string( tmpResult404 );
				break;
			}
			case 14: // _set_version
			{
			_out = handler.createReply();
			version(_input.read_string());
				break;
			}
			case 15: // describe
			{
				_out = handler.createReply();
				org.omg.CORBA.ContainedPackage.DescriptionHelper.write(_out,describe());
				break;
			}
			case 16: // describe_attribute
			{
				_out = handler.createReply();
				org.omg.CORBA.ExtAttributeDescriptionHelper.write(_out,describe_attribute());
				break;
			}
			case 17: // _get_id
			{
			_out = handler.createReply();
			java.lang.String tmpResult405 = id();
_out.write_string( tmpResult405 );
				break;
			}
			case 18: // move
			{
				org.omg.CORBA.Container _arg0=org.omg.CORBA.ContainerHelper.read(_input);
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				move(_arg0,_arg1,_arg2);
				break;
			}
			case 19: // _get_get_exceptions
			{
			_out = handler.createReply();
			org.omg.CORBA.ExcDescriptionSeqHelper.write(_out,get_exceptions());
				break;
			}
			case 20: // _set_set_exceptions
			{
			_out = handler.createReply();
			set_exceptions(org.omg.CORBA.ExcDescriptionSeqHelper.read(_input));
				break;
			}
			case 21: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 22: // _set_id
			{
			_out = handler.createReply();
			id(_input.read_string());
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
