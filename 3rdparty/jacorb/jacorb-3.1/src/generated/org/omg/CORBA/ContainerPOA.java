package org.omg.CORBA;


/**
 * Generated from IDL interface "Container".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public abstract class ContainerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CORBA.ContainerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "create_struct", Integer.valueOf(0));
		m_opsHash.put ( "create_constant", Integer.valueOf(1));
		m_opsHash.put ( "_get_def_kind", Integer.valueOf(2));
		m_opsHash.put ( "create_alias", Integer.valueOf(3));
		m_opsHash.put ( "create_union", Integer.valueOf(4));
		m_opsHash.put ( "lookup", Integer.valueOf(5));
		m_opsHash.put ( "create_native", Integer.valueOf(6));
		m_opsHash.put ( "create_ext_value", Integer.valueOf(7));
		m_opsHash.put ( "create_value_box", Integer.valueOf(8));
		m_opsHash.put ( "lookup_name", Integer.valueOf(9));
		m_opsHash.put ( "create_abstract_interface", Integer.valueOf(10));
		m_opsHash.put ( "describe_contents", Integer.valueOf(11));
		m_opsHash.put ( "create_module", Integer.valueOf(12));
		m_opsHash.put ( "create_value", Integer.valueOf(13));
		m_opsHash.put ( "contents", Integer.valueOf(14));
		m_opsHash.put ( "create_enum", Integer.valueOf(15));
		m_opsHash.put ( "create_interface", Integer.valueOf(16));
		m_opsHash.put ( "create_local_interface", Integer.valueOf(17));
		m_opsHash.put ( "destroy", Integer.valueOf(18));
		m_opsHash.put ( "create_exception", Integer.valueOf(19));
	}
	private String[] ids = {"IDL:omg.org/CORBA/Container:1.0","IDL:omg.org/CORBA/IRObject:1.0"};
	public org.omg.CORBA.Container _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.Container __r = org.omg.CORBA.ContainerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.Container _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.Container __r = org.omg.CORBA.ContainerHelper.narrow(__o);
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
			case 0: // create_struct
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.StructMember[] _arg3=org.omg.CORBA.StructMemberSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.StructDefHelper.write(_out,create_struct(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 1: // create_constant
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				org.omg.CORBA.Any _arg4=_input.read_any();
				_out = handler.createReply();
				org.omg.CORBA.ConstantDefHelper.write(_out,create_constant(_arg0,_arg1,_arg2,_arg3,_arg4));
				break;
			}
			case 2: // _get_def_kind
			{
			_out = handler.createReply();
			org.omg.CORBA.DefinitionKindHelper.write(_out,def_kind());
				break;
			}
			case 3: // create_alias
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.AliasDefHelper.write(_out,create_alias(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 4: // create_union
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				org.omg.CORBA.UnionMember[] _arg4=org.omg.CORBA.UnionMemberSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.UnionDefHelper.write(_out,create_union(_arg0,_arg1,_arg2,_arg3,_arg4));
				break;
			}
			case 5: // lookup
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.ContainedHelper.write(_out,lookup(_arg0));
				break;
			}
			case 6: // create_native
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.NativeDefHelper.write(_out,create_native(_arg0,_arg1,_arg2));
				break;
			}
			case 7: // create_ext_value
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				boolean _arg3=_input.read_boolean();
				boolean _arg4=_input.read_boolean();
				org.omg.CORBA.ValueDef _arg5=org.omg.CORBA.ValueDefHelper.read(_input);
				boolean _arg6=_input.read_boolean();
				org.omg.CORBA.ValueDef[] _arg7=org.omg.CORBA.ValueDefSeqHelper.read(_input);
				org.omg.CORBA.InterfaceDef[] _arg8=org.omg.CORBA.InterfaceDefSeqHelper.read(_input);
				org.omg.CORBA.ExtInitializer[] _arg9=org.omg.CORBA.ExtInitializerSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.ExtValueDefHelper.write(_out,create_ext_value(_arg0,_arg1,_arg2,_arg3,_arg4,_arg5,_arg6,_arg7,_arg8,_arg9));
				break;
			}
			case 8: // create_value_box
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.ValueBoxDefHelper.write(_out,create_value_box(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 9: // lookup_name
			{
				java.lang.String _arg0=_input.read_string();
				int _arg1=_input.read_long();
				org.omg.CORBA.DefinitionKind _arg2=org.omg.CORBA.DefinitionKindHelper.read(_input);
				boolean _arg3=_input.read_boolean();
				_out = handler.createReply();
				org.omg.CORBA.ContainedSeqHelper.write(_out,lookup_name(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 10: // create_abstract_interface
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.AbstractInterfaceDef[] _arg3=org.omg.CORBA.AbstractInterfaceDefSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.AbstractInterfaceDefHelper.write(_out,create_abstract_interface(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 11: // describe_contents
			{
				org.omg.CORBA.DefinitionKind _arg0=org.omg.CORBA.DefinitionKindHelper.read(_input);
				boolean _arg1=_input.read_boolean();
				int _arg2=_input.read_long();
				_out = handler.createReply();
				org.omg.CORBA.ContainerPackage.DescriptionSeqHelper.write(_out,describe_contents(_arg0,_arg1,_arg2));
				break;
			}
			case 12: // create_module
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.ModuleDefHelper.write(_out,create_module(_arg0,_arg1,_arg2));
				break;
			}
			case 13: // create_value
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				boolean _arg3=_input.read_boolean();
				boolean _arg4=_input.read_boolean();
				org.omg.CORBA.ValueDef _arg5=org.omg.CORBA.ValueDefHelper.read(_input);
				boolean _arg6=_input.read_boolean();
				org.omg.CORBA.ValueDef[] _arg7=org.omg.CORBA.ValueDefSeqHelper.read(_input);
				org.omg.CORBA.InterfaceDef[] _arg8=org.omg.CORBA.InterfaceDefSeqHelper.read(_input);
				org.omg.CORBA.Initializer[] _arg9=org.omg.CORBA.InitializerSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.ValueDefHelper.write(_out,create_value(_arg0,_arg1,_arg2,_arg3,_arg4,_arg5,_arg6,_arg7,_arg8,_arg9));
				break;
			}
			case 14: // contents
			{
				org.omg.CORBA.DefinitionKind _arg0=org.omg.CORBA.DefinitionKindHelper.read(_input);
				boolean _arg1=_input.read_boolean();
				_out = handler.createReply();
				org.omg.CORBA.ContainedSeqHelper.write(_out,contents(_arg0,_arg1));
				break;
			}
			case 15: // create_enum
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				java.lang.String[] _arg3=org.omg.CORBA.EnumMemberSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.EnumDefHelper.write(_out,create_enum(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 16: // create_interface
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.InterfaceDef[] _arg3=org.omg.CORBA.InterfaceDefSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.InterfaceDefHelper.write(_out,create_interface(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 17: // create_local_interface
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.InterfaceDef[] _arg3=org.omg.CORBA.InterfaceDefSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.LocalInterfaceDefHelper.write(_out,create_local_interface(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 18: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 19: // create_exception
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.StructMember[] _arg3=org.omg.CORBA.StructMemberSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.ExceptionDefHelper.write(_out,create_exception(_arg0,_arg1,_arg2,_arg3));
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
