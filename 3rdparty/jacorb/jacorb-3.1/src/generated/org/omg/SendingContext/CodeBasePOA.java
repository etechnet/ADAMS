package org.omg.SendingContext;


/**
 * Generated from IDL interface "CodeBase".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public abstract class CodeBasePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.SendingContext.CodeBaseOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_ir", Integer.valueOf(0));
		m_opsHash.put ( "bases", Integer.valueOf(1));
		m_opsHash.put ( "meta", Integer.valueOf(2));
		m_opsHash.put ( "implementations", Integer.valueOf(3));
		m_opsHash.put ( "implementation", Integer.valueOf(4));
		m_opsHash.put ( "implementationx", Integer.valueOf(5));
		m_opsHash.put ( "metas", Integer.valueOf(6));
	}
	private String[] ids = {"IDL:omg.org/SendingContext/CodeBase:1.0","IDL:omg.org/SendingContext/RunTime:1.0"};
	public org.omg.SendingContext.CodeBase _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.SendingContext.CodeBase __r = org.omg.SendingContext.CodeBaseHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.SendingContext.CodeBase _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.SendingContext.CodeBase __r = org.omg.SendingContext.CodeBaseHelper.narrow(__o);
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
			case 0: // get_ir
			{
				_out = handler.createReply();
				org.omg.CORBA.RepositoryHelper.write(_out,get_ir());
				break;
			}
			case 1: // bases
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.RepositoryIdSeqHelper.write(_out,bases(_arg0));
				break;
			}
			case 2: // meta
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.omg.CORBA.ValueDefPackage.FullValueDescriptionHelper.write(_out,meta(_arg0));
				break;
			}
			case 3: // implementations
			{
				java.lang.String[] _arg0=org.omg.CORBA.RepositoryIdSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.SendingContext.CodeBasePackage.URLSeqHelper.write(_out,implementations(_arg0));
				break;
			}
			case 4: // implementation
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				java.lang.String tmpResult1012 = implementation(_arg0);
_out.write_string( tmpResult1012 );
				break;
			}
			case 5: // implementationx
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				java.lang.String tmpResult1013 = implementationx(_arg0);
_out.write_string( tmpResult1013 );
				break;
			}
			case 6: // metas
			{
				java.lang.String[] _arg0=org.omg.CORBA.RepositoryIdSeqHelper.read(_input);
				_out = handler.createReply();
				org.omg.SendingContext.CodeBasePackage.ValueDescSeqHelper.write(_out,metas(_arg0));
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
