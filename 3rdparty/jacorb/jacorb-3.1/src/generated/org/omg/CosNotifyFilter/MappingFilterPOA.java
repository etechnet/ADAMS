package org.omg.CosNotifyFilter;


/**
 * Generated from IDL interface "MappingFilter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class MappingFilterPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosNotifyFilter.MappingFilterOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "add_mapping_constraints", Integer.valueOf(0));
		m_opsHash.put ( "match_typed", Integer.valueOf(1));
		m_opsHash.put ( "_get_constraint_grammar", Integer.valueOf(2));
		m_opsHash.put ( "modify_mapping_constraints", Integer.valueOf(3));
		m_opsHash.put ( "_get_value_type", Integer.valueOf(4));
		m_opsHash.put ( "_get_default_value", Integer.valueOf(5));
		m_opsHash.put ( "remove_all_mapping_constraints", Integer.valueOf(6));
		m_opsHash.put ( "destroy", Integer.valueOf(7));
		m_opsHash.put ( "get_mapping_constraints", Integer.valueOf(8));
		m_opsHash.put ( "match_structured", Integer.valueOf(9));
		m_opsHash.put ( "get_all_mapping_constraints", Integer.valueOf(10));
		m_opsHash.put ( "match", Integer.valueOf(11));
	}
	private String[] ids = {"IDL:omg.org/CosNotifyFilter/MappingFilter:1.0"};
	public org.omg.CosNotifyFilter.MappingFilter _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyFilter.MappingFilter __r = org.omg.CosNotifyFilter.MappingFilterHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyFilter.MappingFilter _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyFilter.MappingFilter __r = org.omg.CosNotifyFilter.MappingFilterHelper.narrow(__o);
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
			case 0: // add_mapping_constraints
			{
			try
			{
				org.omg.CosNotifyFilter.MappingConstraintPair[] _arg0=org.omg.CosNotifyFilter.MappingConstraintPairSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_out,add_mapping_constraints(_arg0));
			}
			catch(org.omg.CosNotifyFilter.InvalidValue _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.InvalidValueHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNotifyFilter.InvalidConstraint _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.InvalidConstraintHelper.write(_out, _ex1);
			}
				break;
			}
			case 1: // match_typed
			{
			try
			{
				org.omg.CosNotification.Property[] _arg0=org.omg.CosNotification.PropertySeqHelper.read(_input);
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(match_typed(_arg0,_arg1));
				_out.write_any(_arg1.value);
			}
			catch(org.omg.CosNotifyFilter.UnsupportedFilterableData _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // _get_constraint_grammar
			{
			_out = handler.createReply();
			java.lang.String tmpResult1151 = constraint_grammar();
_out.write_string( tmpResult1151 );
				break;
			}
			case 3: // modify_mapping_constraints
			{
			try
			{
				int[] _arg0=org.omg.CosNotifyFilter.ConstraintIDSeqHelper.read(_input);
				org.omg.CosNotifyFilter.MappingConstraintInfo[] _arg1=org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.read(_input);
				_out = handler.createReply();
				modify_mapping_constraints(_arg0,_arg1);
			}
			catch(org.omg.CosNotifyFilter.ConstraintNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.ConstraintNotFoundHelper.write(_out, _ex0);
			}
			catch(org.omg.CosNotifyFilter.InvalidValue _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.InvalidValueHelper.write(_out, _ex1);
			}
			catch(org.omg.CosNotifyFilter.InvalidConstraint _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.InvalidConstraintHelper.write(_out, _ex2);
			}
				break;
			}
			case 4: // _get_value_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(value_type());
				break;
			}
			case 5: // _get_default_value
			{
			_out = handler.createReply();
			_out.write_any(default_value());
				break;
			}
			case 6: // remove_all_mapping_constraints
			{
				_out = handler.createReply();
				remove_all_mapping_constraints();
				break;
			}
			case 7: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 8: // get_mapping_constraints
			{
			try
			{
				int[] _arg0=org.omg.CosNotifyFilter.ConstraintIDSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_out,get_mapping_constraints(_arg0));
			}
			catch(org.omg.CosNotifyFilter.ConstraintNotFound _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.ConstraintNotFoundHelper.write(_out, _ex0);
			}
				break;
			}
			case 9: // match_structured
			{
			try
			{
				org.omg.CosNotification.StructuredEvent _arg0=org.omg.CosNotification.StructuredEventHelper.read(_input);
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(match_structured(_arg0,_arg1));
				_out.write_any(_arg1.value);
			}
			catch(org.omg.CosNotifyFilter.UnsupportedFilterableData _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_out, _ex0);
			}
				break;
			}
			case 10: // get_all_mapping_constraints
			{
				_out = handler.createReply();
				org.omg.CosNotifyFilter.MappingConstraintInfoSeqHelper.write(_out,get_all_mapping_constraints());
				break;
			}
			case 11: // match
			{
			try
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CORBA.AnyHolder _arg1= new org.omg.CORBA.AnyHolder();
				_out = handler.createReply();
				_out.write_boolean(match(_arg0,_arg1));
				_out.write_any(_arg1.value);
			}
			catch(org.omg.CosNotifyFilter.UnsupportedFilterableData _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosNotifyFilter.UnsupportedFilterableDataHelper.write(_out, _ex0);
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
