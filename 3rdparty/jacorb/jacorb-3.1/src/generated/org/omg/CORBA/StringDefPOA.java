package org.omg.CORBA;


/**
 * Generated from IDL interface "StringDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public abstract class StringDefPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.CORBA.StringDefOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_set_bound", Integer.valueOf(0));
		m_opsHash.put ( "_get_bound", Integer.valueOf(1));
		m_opsHash.put ( "_get_type", Integer.valueOf(2));
		m_opsHash.put ( "destroy", Integer.valueOf(3));
		m_opsHash.put ( "_get_def_kind", Integer.valueOf(4));
	}
	private String[] ids = {"IDL:omg.org/CORBA/StringDef:1.0","IDL:omg.org/CORBA/IDLType:1.0","IDL:omg.org/CORBA/IRObject:1.0"};
	public org.omg.CORBA.StringDef _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.StringDef __r = org.omg.CORBA.StringDefHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.StringDef _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.StringDef __r = org.omg.CORBA.StringDefHelper.narrow(__o);
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
			case 0: // _set_bound
			{
			_out = handler.createReply();
			bound(_input.read_ulong());
				break;
			}
			case 1: // _get_bound
			{
			_out = handler.createReply();
			_out.write_ulong(bound());
				break;
			}
			case 2: // _get_type
			{
			_out = handler.createReply();
			_out.write_TypeCode(type());
				break;
			}
			case 3: // destroy
			{
				_out = handler.createReply();
				destroy();
				break;
			}
			case 4: // _get_def_kind
			{
			_out = handler.createReply();
			org.omg.CORBA.DefinitionKindHelper.write(_out,def_kind());
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
