package org.omg.CORBA;


/**
 * Generated from IDL interface "ValueDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public abstract class ValueDefPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CORBA.ValueDefOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "create_value", Integer.valueOf(0));
		m_opsHash.put ( "_set_is_truncatable", Integer.valueOf(1));
		m_opsHash.put ( "_get_id", Integer.valueOf(2));
		m_opsHash.put ( "_get_absolute_name", Integer.valueOf(3));
		m_opsHash.put ( "is_a", Integer.valueOf(4));
		m_opsHash.put ( "_set_abstract_base_values", Integer.valueOf(5));
		m_opsHash.put ( "_get_initializers", Integer.valueOf(6));
		m_opsHash.put ( "create_struct", Integer.valueOf(7));
		m_opsHash.put ( "describe_contents", Integer.valueOf(8));
		m_opsHash.put ( "create_alias", Integer.valueOf(9));
		m_opsHash.put ( "create_enum", Integer.valueOf(10));
		m_opsHash.put ( "lookup", Integer.valueOf(11));
		m_opsHash.put ( "_get_base_value", Integer.valueOf(12));
		m_opsHash.put ( "_set_version", Integer.valueOf(13));
		m_opsHash.put ( "create_ext_value", Integer.valueOf(14));
		m_opsHash.put ( "describe", Integer.valueOf(15));
		m_opsHash.put ( "_set_name", Integer.valueOf(16));
		m_opsHash.put ( "_get_is_abstract", Integer.valueOf(17));
		m_opsHash.put ( "_get_defined_in", Integer.valueOf(18));
		m_opsHash.put ( "create_operation", Integer.valueOf(19));
		m_opsHash.put ( "_get_is_custom", Integer.valueOf(20));
		m_opsHash.put ( "_get_containing_repository", Integer.valueOf(21));
		m_opsHash.put ( "create_module", Integer.valueOf(22));
		m_opsHash.put ( "move", Integer.valueOf(23));
		m_opsHash.put ( "create_abstract_interface", Integer.valueOf(24));
		m_opsHash.put ( "_set_base_value", Integer.valueOf(25));
		m_opsHash.put ( "_get_version", Integer.valueOf(26));
		m_opsHash.put ( "_set_supported_interfaces", Integer.valueOf(27));
		m_opsHash.put ( "create_value_member", Integer.valueOf(28));
		m_opsHash.put ( "create_constant", Integer.valueOf(29));
		m_opsHash.put ( "destroy", Integer.valueOf(30));
		m_opsHash.put ( "lookup_name", Integer.valueOf(31));
		m_opsHash.put ( "_get_type", Integer.valueOf(32));
		m_opsHash.put ( "_set_id", Integer.valueOf(33));
		m_opsHash.put ( "contents", Integer.valueOf(34));
		m_opsHash.put ( "create_local_interface", Integer.valueOf(35));
		m_opsHash.put ( "_get_def_kind", Integer.valueOf(36));
		m_opsHash.put ( "_get_abstract_base_values", Integer.valueOf(37));
		m_opsHash.put ( "_set_is_custom", Integer.valueOf(38));
		m_opsHash.put ( "create_value_box", Integer.valueOf(39));
		m_opsHash.put ( "_get_is_truncatable", Integer.valueOf(40));
		m_opsHash.put ( "_set_initializers", Integer.valueOf(41));
		m_opsHash.put ( "create_attribute", Integer.valueOf(42));
		m_opsHash.put ( "create_union", Integer.valueOf(43));
		m_opsHash.put ( "create_exception", Integer.valueOf(44));
		m_opsHash.put ( "_get_name", Integer.valueOf(45));
		m_opsHash.put ( "_get_supported_interfaces", Integer.valueOf(46));
		m_opsHash.put ( "describe_value", Integer.valueOf(47));
		m_opsHash.put ( "create_interface", Integer.valueOf(48));
		m_opsHash.put ( "_set_is_abstract", Integer.valueOf(49));
		m_opsHash.put ( "create_native", Integer.valueOf(50));
	}
	private String[] ids = {"IDL:omg.org/CORBA/ValueDef:1.0","IDL:omg.org/CORBA/Container:1.0","IDL:omg.org/CORBA/Contained:1.0","IDL:omg.org/CORBA/IDLType:1.0","IDL:omg.org/CORBA/IRObject:1.0"};
	public org.omg.CORBA.ValueDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.ValueDef __r = org.omg.CORBA.ValueDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.ValueDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.ValueDef __r = org.omg.CORBA.ValueDefHelper.narrow(__o);
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
			case 0: // create_value
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
			case 1: // _set_is_truncatable
			{
			_out = handler.createReply();
			is_truncatable(_input.read_boolean());
				break;
			}
			case 2: // _get_id
			{
			_out = handler.createReply();
			java.lang.String tmpResult633 = id();
_out.write_string( tmpResult633 );
				break;
			}
			case 3: // _get_absolute_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult634 = absolute_name();
_out.write_string( tmpResult634 );
				break;
			}
			case 4: // is_a
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				_out.write_boolean(is_a(_arg0));
				break;
			}
			case 5: // _set_abstract_base_values
			{
			_out = handler.createReply();
			abstract_base_values(org.omg.CORBA.ValueDefSeqHelper.read(_input));
				break;
			}
			case 6: // _get_initializers
			{
			_out = handler.createReply();
			org.omg.CORBA.InitializerSeqHelper.write(_out,initializers());
				break;
			}
			case 7: // create_struct
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.StructMember[] _arg3=org.omg.CORBA.StructMemberSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.StructDefHelper.write(_out,create_struct(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 8: // describe_contents
			{
				org.omg.CORBA.DefinitionKind _arg0=org.omg.CORBA.DefinitionKindHelper.read(_input);
				boolean _arg1=_input.read_boolean();
				int _arg2=_input.read_long();
				_out = handler.createReply();
				org.omg.CORBA.ContainerPackage.DescriptionSeqHelper.write(_out,describe_contents(_arg0,_arg1,_arg2));
				break;
			}
			case 9: // create_alias
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.AliasDefHelper.write(_out,create_alias(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 10: // create_enum
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				java.lang.String[] _arg3=org.omg.CORBA.EnumMemberSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.EnumDefHelper.write(_out,create_enum(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 11: // lookup
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.ContainedHelper.write(_out,lookup(_arg0));
				break;
			}
			case 12: // _get_base_value
			{
			_out = handler.createReply();
			org.omg.CORBA.ValueDefHelper.write(_out,base_value());
				break;
			}
			case 13: // _set_version
			{
			_out = handler.createReply();
			version(_input.read_string());
				break;
			}
			case 14: // create_ext_value
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
			case 15: // describe
			{
				_out = handler.createReply();
				org.omg.CORBA.ContainedPackage.DescriptionHelper.write(_out,describe());
				break;
			}
			case 16: // _set_name
			{
			_out = handler.createReply();
			name(_input.read_string());
				break;
			}
			case 17: // _get_is_abstract
			{
			_out = handler.createReply();
			_out.write_boolean(is_abstract());
				break;
			}
			case 18: // _get_defined_in
			{
			_out = handler.createReply();
			org.omg.CORBA.ContainerHelper.write(_out,defined_in());
				break;
			}
			case 19: // create_operation
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				org.omg.CORBA.OperationMode _arg4=org.omg.CORBA.OperationModeHelper.read(_input);
				org.omg.CORBA.ParameterDescription[] _arg5=org.omg.CORBA.ParDescriptionSeqHelper.read(_input);
				org.omg.CORBA.ExceptionDef[] _arg6=org.omg.CORBA.ExceptionDefSeqHelper.read(_input);
				java.lang.String[] _arg7=org.omg.CORBA.ContextIdSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.OperationDefHelper.write(_out,create_operation(_arg0,_arg1,_arg2,_arg3,_arg4,_arg5,_arg6,_arg7));
				break;
			}
			case 20: // _get_is_custom
			{
			_out = handler.createReply();
			_out.write_boolean(is_custom());
				break;
			}
			case 21: // _get_containing_repository
			{
			_out = handler.createReply();
			org.omg.CORBA.RepositoryHelper.write(_out,containing_repository());
				break;
			}
			case 22: // create_module
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.ModuleDefHelper.write(_out,create_module(_arg0,_arg1,_arg2));
				break;
			}
			case 23: // move
			{
				org.omg.CORBA.Container _arg0=org.omg.CORBA.ContainerHelper.read(_input);
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				move(_arg0,_arg1,_arg2);
				break;
			}
			case 24: // create_abstract_interface
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.AbstractInterfaceDef[] _arg3=org.omg.CORBA.AbstractInterfaceDefSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.AbstractInterfaceDefHelper.write(_out,create_abstract_interface(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 25: // _set_base_value
			{
			_out = handler.createReply();
			base_value(org.omg.CORBA.ValueDefHelper.read(_input));
				break;
			}
			case 26: // _get_version
			{
			_out = handler.createReply();
			java.lang.String tmpResult635 = version();
_out.write_string( tmpResult635 );
				break;
			}
			case 27: // _set_supported_interfaces
			{
			_out = handler.createReply();
			supported_interfaces(org.omg.CORBA.InterfaceDefSeqHelper.read(_input));
				break;
			}
			case 28: // create_value_member
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				short _arg4=_input.read_short();
				_out = handler.createReply();
				org.omg.CORBA.ValueMemberDefHelper.write(_out,create_value_member(_arg0,_arg1,_arg2,_arg3,_arg4));
				break;
			}
			case 29: // create_constant
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
			case 30: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 31: // lookup_name
			{
				java.lang.String _arg0=_input.read_string();
				int _arg1=_input.read_long();
				org.omg.CORBA.DefinitionKind _arg2=org.omg.CORBA.DefinitionKindHelper.read(_input);
				boolean _arg3=_input.read_boolean();
				_out = handler.createReply();
				org.omg.CORBA.ContainedSeqHelper.write(_out,lookup_name(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 32: // _get_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(type());
				break;
			}
			case 33: // _set_id
			{
			_out = handler.createReply();
			id(_input.read_string());
				break;
			}
			case 34: // contents
			{
				org.omg.CORBA.DefinitionKind _arg0=org.omg.CORBA.DefinitionKindHelper.read(_input);
				boolean _arg1=_input.read_boolean();
				_out = handler.createReply();
				org.omg.CORBA.ContainedSeqHelper.write(_out,contents(_arg0,_arg1));
				break;
			}
			case 35: // create_local_interface
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.InterfaceDef[] _arg3=org.omg.CORBA.InterfaceDefSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.LocalInterfaceDefHelper.write(_out,create_local_interface(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 36: // _get_def_kind
			{
			_out = handler.createReply();
			org.omg.CORBA.DefinitionKindHelper.write(_out,def_kind());
				break;
			}
			case 37: // _get_abstract_base_values
			{
			_out = handler.createReply();
			org.omg.CORBA.ValueDefSeqHelper.write(_out,abstract_base_values());
				break;
			}
			case 38: // _set_is_custom
			{
			_out = handler.createReply();
			is_custom(_input.read_boolean());
				break;
			}
			case 39: // create_value_box
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.ValueBoxDefHelper.write(_out,create_value_box(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 40: // _get_is_truncatable
			{
			_out = handler.createReply();
			_out.write_boolean(is_truncatable());
				break;
			}
			case 41: // _set_initializers
			{
			_out = handler.createReply();
			initializers(org.omg.CORBA.InitializerSeqHelper.read(_input));
				break;
			}
			case 42: // create_attribute
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.IDLType _arg3=org.omg.CORBA.IDLTypeHelper.read(_input);
				org.omg.CORBA.AttributeMode _arg4=org.omg.CORBA.AttributeModeHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.AttributeDefHelper.write(_out,create_attribute(_arg0,_arg1,_arg2,_arg3,_arg4));
				break;
			}
			case 43: // create_union
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
			case 44: // create_exception
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.StructMember[] _arg3=org.omg.CORBA.StructMemberSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.ExceptionDefHelper.write(_out,create_exception(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 45: // _get_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult636 = name();
_out.write_string( tmpResult636 );
				break;
			}
			case 46: // _get_supported_interfaces
			{
			_out = handler.createReply();
			org.omg.CORBA.InterfaceDefSeqHelper.write(_out,supported_interfaces());
				break;
			}
			case 47: // describe_value
			{
				_out = handler.createReply();
				org.omg.CORBA.ValueDefPackage.FullValueDescriptionHelper.write(_out,describe_value());
				break;
			}
			case 48: // create_interface
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				org.omg.CORBA.InterfaceDef[] _arg3=org.omg.CORBA.InterfaceDefSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CORBA.InterfaceDefHelper.write(_out,create_interface(_arg0,_arg1,_arg2,_arg3));
				break;
			}
			case 49: // _set_is_abstract
			{
			_out = handler.createReply();
			is_abstract(_input.read_boolean());
				break;
			}
			case 50: // create_native
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.NativeDefHelper.write(_out,create_native(_arg0,_arg1,_arg2));
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
