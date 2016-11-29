package org.omg.CosCollection;


/**
 * Generated from IDL interface "Operations".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class OperationsPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CosCollection.OperationsOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "compare", Integer.valueOf(0));
		m_opsHash.put ( "key_hash", Integer.valueOf(1));
		m_opsHash.put ( "hash", Integer.valueOf(2));
		m_opsHash.put ( "key_equal", Integer.valueOf(3));
		m_opsHash.put ( "key", Integer.valueOf(4));
		m_opsHash.put ( "_get_element_type", Integer.valueOf(5));
		m_opsHash.put ( "equal", Integer.valueOf(6));
		m_opsHash.put ( "check_element_type", Integer.valueOf(7));
		m_opsHash.put ( "_get_key_type", Integer.valueOf(8));
		m_opsHash.put ( "key_compare", Integer.valueOf(9));
		m_opsHash.put ( "destroy", Integer.valueOf(10));
		m_opsHash.put ( "check_key_type", Integer.valueOf(11));
	}
	private String[] ids = {"IDL:omg.org/CosCollection/Operations:1.0"};
	public org.omg.CosCollection.Operations _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.Operations __r = org.omg.CosCollection.OperationsHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.Operations _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.Operations __r = org.omg.CosCollection.OperationsHelper.narrow(__o);
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
			case 0: // compare
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				_out.write_long(compare(_arg0,_arg1));
				break;
			}
			case 1: // key_hash
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				int _arg1=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(key_hash(_arg0,_arg1));
				break;
			}
			case 2: // hash
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				int _arg1=_input.read_ulong();
				_out = handler.createReply();
				_out.write_ulong(hash(_arg0,_arg1));
				break;
			}
			case 3: // key_equal
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(key_equal(_arg0,_arg1));
				break;
			}
			case 4: // key
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_any(key(_arg0));
				break;
			}
			case 5: // _get_element_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(element_type());
				break;
			}
			case 6: // equal
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(equal(_arg0,_arg1));
				break;
			}
			case 7: // check_element_type
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(check_element_type(_arg0));
				break;
			}
			case 8: // _get_key_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(key_type());
				break;
			}
			case 9: // key_compare
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				org.omg.CORBA.Any _arg1=_input.read_any();
				_out = handler.createReply();
				_out.write_long(key_compare(_arg0,_arg1));
				break;
			}
			case 10: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 11: // check_key_type
			{
				org.omg.CORBA.Any _arg0=_input.read_any();
				_out = handler.createReply();
				_out.write_boolean(check_key_type(_arg0));
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
