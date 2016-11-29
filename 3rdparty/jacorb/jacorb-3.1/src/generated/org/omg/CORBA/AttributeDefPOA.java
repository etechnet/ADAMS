package org.omg.CORBA;


/**
 * Generated from IDL interface "AttributeDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public abstract class AttributeDefPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CORBA.AttributeDefOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_get_type_def", Integer.valueOf(0));
		m_opsHash.put ( "_get_containing_repository", Integer.valueOf(1));
		m_opsHash.put ( "_get_def_kind", Integer.valueOf(2));
		m_opsHash.put ( "_set_type_def", Integer.valueOf(3));
		m_opsHash.put ( "_get_absolute_name", Integer.valueOf(4));
		m_opsHash.put ( "_get_name", Integer.valueOf(5));
		m_opsHash.put ( "_get_type", Integer.valueOf(6));
		m_opsHash.put ( "_set_name", Integer.valueOf(7));
		m_opsHash.put ( "_set_mode", Integer.valueOf(8));
		m_opsHash.put ( "_get_mode", Integer.valueOf(9));
		m_opsHash.put ( "_get_defined_in", Integer.valueOf(10));
		m_opsHash.put ( "_get_version", Integer.valueOf(11));
		m_opsHash.put ( "_set_version", Integer.valueOf(12));
		m_opsHash.put ( "describe", Integer.valueOf(13));
		m_opsHash.put ( "_get_id", Integer.valueOf(14));
		m_opsHash.put ( "move", Integer.valueOf(15));
		m_opsHash.put ( "destroy", Integer.valueOf(16));
		m_opsHash.put ( "_set_id", Integer.valueOf(17));
	}
	private String[] ids = {"IDL:omg.org/CORBA/AttributeDef:1.0","IDL:omg.org/CORBA/Contained:1.0","IDL:omg.org/CORBA/IRObject:1.0"};
	public org.omg.CORBA.AttributeDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.AttributeDef __r = org.omg.CORBA.AttributeDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.AttributeDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.AttributeDef __r = org.omg.CORBA.AttributeDefHelper.narrow(__o);
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
			case 1: // _get_containing_repository
			{
			_out = handler.createReply();
			org.omg.CORBA.RepositoryHelper.write(_out,containing_repository());
				break;
			}
			case 2: // _get_def_kind
			{
			_out = handler.createReply();
			org.omg.CORBA.DefinitionKindHelper.write(_out,def_kind());
				break;
			}
			case 3: // _set_type_def
			{
			_out = handler.createReply();
			type_def(org.omg.CORBA.IDLTypeHelper.read(_input));
				break;
			}
			case 4: // _get_absolute_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult385 = absolute_name();
_out.write_string( tmpResult385 );
				break;
			}
			case 5: // _get_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult386 = name();
_out.write_string( tmpResult386 );
				break;
			}
			case 6: // _get_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(type());
				break;
			}
			case 7: // _set_name
			{
			_out = handler.createReply();
			name(_input.read_string());
				break;
			}
			case 8: // _set_mode
			{
			_out = handler.createReply();
			mode(org.omg.CORBA.AttributeModeHelper.read(_input));
				break;
			}
			case 9: // _get_mode
			{
			_out = handler.createReply();
			org.omg.CORBA.AttributeModeHelper.write(_out,mode());
				break;
			}
			case 10: // _get_defined_in
			{
			_out = handler.createReply();
			org.omg.CORBA.ContainerHelper.write(_out,defined_in());
				break;
			}
			case 11: // _get_version
			{
			_out = handler.createReply();
			java.lang.String tmpResult387 = version();
_out.write_string( tmpResult387 );
				break;
			}
			case 12: // _set_version
			{
			_out = handler.createReply();
			version(_input.read_string());
				break;
			}
			case 13: // describe
			{
				_out = handler.createReply();
				org.omg.CORBA.ContainedPackage.DescriptionHelper.write(_out,describe());
				break;
			}
			case 14: // _get_id
			{
			_out = handler.createReply();
			java.lang.String tmpResult388 = id();
_out.write_string( tmpResult388 );
				break;
			}
			case 15: // move
			{
				org.omg.CORBA.Container _arg0=org.omg.CORBA.ContainerHelper.read(_input);
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				move(_arg0,_arg1,_arg2);
				break;
			}
			case 16: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 17: // _set_id
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
