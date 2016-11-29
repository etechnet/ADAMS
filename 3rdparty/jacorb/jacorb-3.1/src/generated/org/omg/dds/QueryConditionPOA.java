package org.omg.dds;


/**
 * Generated from IDL interface "QueryCondition".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public abstract class QueryConditionPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.dds.QueryConditionOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_datareader", Integer.valueOf(0));
		m_opsHash.put ( "get_query_arguments", Integer.valueOf(1));
		m_opsHash.put ( "get_query_expression", Integer.valueOf(2));
		m_opsHash.put ( "get_sample_state_mask", Integer.valueOf(3));
		m_opsHash.put ( "get_view_state_mask", Integer.valueOf(4));
		m_opsHash.put ( "get_instance_state_mask", Integer.valueOf(5));
		m_opsHash.put ( "get_trigger_value", Integer.valueOf(6));
		m_opsHash.put ( "set_query_arguments", Integer.valueOf(7));
	}
	private String[] ids = {"IDL:omg.org/dds/QueryCondition:1.0","IDL:omg.org/dds/ReadCondition:1.0","IDL:omg.org/dds/Condition:1.0"};
	public org.omg.dds.QueryCondition _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.dds.QueryCondition __r = org.omg.dds.QueryConditionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.dds.QueryCondition _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.dds.QueryCondition __r = org.omg.dds.QueryConditionHelper.narrow(__o);
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
			case 0: // get_datareader
			{
				_out = handler.createReply();
				org.omg.dds.DataReaderHelper.write(_out,get_datareader());
				break;
			}
			case 1: // get_query_arguments
			{
				_out = handler.createReply();
				org.omg.dds.StringSeqHelper.write(_out,get_query_arguments());
				break;
			}
			case 2: // get_query_expression
			{
				_out = handler.createReply();
				java.lang.String tmpResult1111 = get_query_expression();
_out.write_string( tmpResult1111 );
				break;
			}
			case 3: // get_sample_state_mask
			{
				_out = handler.createReply();
				_out.write_ulong(get_sample_state_mask());
				break;
			}
			case 4: // get_view_state_mask
			{
				_out = handler.createReply();
				_out.write_ulong(get_view_state_mask());
				break;
			}
			case 5: // get_instance_state_mask
			{
				_out = handler.createReply();
				_out.write_ulong(get_instance_state_mask());
				break;
			}
			case 6: // get_trigger_value
			{
				_out = handler.createReply();
				_out.write_boolean(get_trigger_value());
				break;
			}
			case 7: // set_query_arguments
			{
				java.lang.String[] _arg0=org.omg.dds.StringSeqHelper.read(_input);
				_out = handler.createReply();
				_out.write_long(set_query_arguments(_arg0));
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
