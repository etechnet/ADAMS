package org.omg.CORBA;


/**
 * Generated from IDL interface "OperationDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public abstract class OperationDefPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CORBA.OperationDefOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_get_containing_repository", Integer.valueOf(0));
		m_opsHash.put ( "_get_def_kind", Integer.valueOf(1));
		m_opsHash.put ( "_get_absolute_name", Integer.valueOf(2));
		m_opsHash.put ( "_get_name", Integer.valueOf(3));
		m_opsHash.put ( "_set_name", Integer.valueOf(4));
		m_opsHash.put ( "_set_params", Integer.valueOf(5));
		m_opsHash.put ( "_get_result_def", Integer.valueOf(6));
		m_opsHash.put ( "_get_contexts", Integer.valueOf(7));
		m_opsHash.put ( "_get_defined_in", Integer.valueOf(8));
		m_opsHash.put ( "_get_mode", Integer.valueOf(9));
		m_opsHash.put ( "_get_version", Integer.valueOf(10));
		m_opsHash.put ( "_set_result_def", Integer.valueOf(11));
		m_opsHash.put ( "_set_version", Integer.valueOf(12));
		m_opsHash.put ( "describe", Integer.valueOf(13));
		m_opsHash.put ( "_get_params", Integer.valueOf(14));
		m_opsHash.put ( "_get_id", Integer.valueOf(15));
		m_opsHash.put ( "move", Integer.valueOf(16));
		m_opsHash.put ( "_get_result", Integer.valueOf(17));
		m_opsHash.put ( "_set_contexts", Integer.valueOf(18));
		m_opsHash.put ( "_set_mode", Integer.valueOf(19));
		m_opsHash.put ( "destroy", Integer.valueOf(20));
		m_opsHash.put ( "_get_exceptions", Integer.valueOf(21));
		m_opsHash.put ( "_set_exceptions", Integer.valueOf(22));
		m_opsHash.put ( "_set_id", Integer.valueOf(23));
	}
	private String[] ids = {"IDL:omg.org/CORBA/OperationDef:1.0","IDL:omg.org/CORBA/Contained:1.0","IDL:omg.org/CORBA/IRObject:1.0"};
	public org.omg.CORBA.OperationDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.OperationDef __r = org.omg.CORBA.OperationDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.OperationDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.OperationDef __r = org.omg.CORBA.OperationDefHelper.narrow(__o);
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
			case 0: // _get_containing_repository
			{
			_out = handler.createReply();
			org.omg.CORBA.RepositoryHelper.write(_out,containing_repository());
				break;
			}
			case 1: // _get_def_kind
			{
			_out = handler.createReply();
			org.omg.CORBA.DefinitionKindHelper.write(_out,def_kind());
				break;
			}
			case 2: // _get_absolute_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult414 = absolute_name();
_out.write_string( tmpResult414 );
				break;
			}
			case 3: // _get_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult415 = name();
_out.write_string( tmpResult415 );
				break;
			}
			case 4: // _set_name
			{
			_out = handler.createReply();
			name(_input.read_string());
				break;
			}
			case 5: // _set_params
			{
			_out = handler.createReply();
			params(org.omg.CORBA.ParDescriptionSeqHelper.read(_input));
				break;
			}
			case 6: // _get_result_def
			{
			_out = handler.createReply();
			org.omg.CORBA.IDLTypeHelper.write(_out,result_def());
				break;
			}
			case 7: // _get_contexts
			{
			_out = handler.createReply();
			org.omg.CORBA.ContextIdSeqHelper.write(_out,contexts());
				break;
			}
			case 8: // _get_defined_in
			{
			_out = handler.createReply();
			org.omg.CORBA.ContainerHelper.write(_out,defined_in());
				break;
			}
			case 9: // _get_mode
			{
			_out = handler.createReply();
			org.omg.CORBA.OperationModeHelper.write(_out,mode());
				break;
			}
			case 10: // _get_version
			{
			_out = handler.createReply();
			java.lang.String tmpResult416 = version();
_out.write_string( tmpResult416 );
				break;
			}
			case 11: // _set_result_def
			{
			_out = handler.createReply();
			result_def(org.omg.CORBA.IDLTypeHelper.read(_input));
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
			case 14: // _get_params
			{
			_out = handler.createReply();
			org.omg.CORBA.ParDescriptionSeqHelper.write(_out,params());
				break;
			}
			case 15: // _get_id
			{
			_out = handler.createReply();
			java.lang.String tmpResult417 = id();
_out.write_string( tmpResult417 );
				break;
			}
			case 16: // move
			{
				org.omg.CORBA.Container _arg0=org.omg.CORBA.ContainerHelper.read(_input);
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				move(_arg0,_arg1,_arg2);
				break;
			}
			case 17: // _get_result
			{
			_out = handler.createReply();
			_out.write_TypeCode(result());
				break;
			}
			case 18: // _set_contexts
			{
			_out = handler.createReply();
			contexts(org.omg.CORBA.ContextIdSeqHelper.read(_input));
				break;
			}
			case 19: // _set_mode
			{
			_out = handler.createReply();
			mode(org.omg.CORBA.OperationModeHelper.read(_input));
				break;
			}
			case 20: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 21: // _get_exceptions
			{
			_out = handler.createReply();
			org.omg.CORBA.ExceptionDefSeqHelper.write(_out,exceptions());
				break;
			}
			case 22: // _set_exceptions
			{
			_out = handler.createReply();
			exceptions(org.omg.CORBA.ExceptionDefSeqHelper.read(_input));
				break;
			}
			case 23: // _set_id
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
