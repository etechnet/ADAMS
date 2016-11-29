package org.jacorb.imr;


/**
 * Generated from IDL interface "Admin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public abstract class AdminPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.jacorb.imr.AdminOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "release_server", Integer.valueOf(0));
		m_opsHash.put ( "list_hosts", Integer.valueOf(1));
		m_opsHash.put ( "unregister_host", Integer.valueOf(2));
		m_opsHash.put ( "start_server", Integer.valueOf(3));
		m_opsHash.put ( "list_servers", Integer.valueOf(4));
		m_opsHash.put ( "shutdown", Integer.valueOf(5));
		m_opsHash.put ( "save_server_table", Integer.valueOf(6));
		m_opsHash.put ( "hold_server", Integer.valueOf(7));
		m_opsHash.put ( "get_server_info", Integer.valueOf(8));
		m_opsHash.put ( "edit_server", Integer.valueOf(9));
		m_opsHash.put ( "unregister_server", Integer.valueOf(10));
		m_opsHash.put ( "register_server", Integer.valueOf(11));
	}
	private String[] ids = {"IDL:org/jacorb/imr/Admin:1.0"};
	public org.jacorb.imr.Admin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.imr.Admin __r = org.jacorb.imr.AdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.imr.Admin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.imr.Admin __r = org.jacorb.imr.AdminHelper.narrow(__o);
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
			case 0: // release_server
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				release_server(_arg0);
			}
			catch(org.jacorb.imr.UnknownServerName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // list_hosts
			{
				_out = handler.createReply();
				org.jacorb.imr.HostInfoSeqHelper.write(_out,list_hosts());
				break;
			}
			case 2: // unregister_host
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				unregister_host(_arg0);
			}
			catch(org.jacorb.imr.AdminPackage.UnknownHostName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.AdminPackage.UnknownHostNameHelper.write(_out, _ex0);
			}
				break;
			}
			case 3: // start_server
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				start_server(_arg0);
			}
			catch(org.jacorb.imr.ServerStartupFailed _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.ServerStartupFailedHelper.write(_out, _ex0);
			}
			catch(org.jacorb.imr.UnknownServerName _ex1)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex1);
			}
				break;
			}
			case 4: // list_servers
			{
				_out = handler.createReply();
				org.jacorb.imr.ServerInfoSeqHelper.write(_out,list_servers());
				break;
			}
			case 5: // shutdown
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				shutdown(_arg0);
				break;
			}
			case 6: // save_server_table
			{
			try
			{
				_out = handler.createReply();
				save_server_table();
			}
			catch(org.jacorb.imr.AdminPackage.FileOpFailed _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.AdminPackage.FileOpFailedHelper.write(_out, _ex0);
			}
				break;
			}
			case 7: // hold_server
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				hold_server(_arg0);
			}
			catch(org.jacorb.imr.UnknownServerName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex0);
			}
				break;
			}
			case 8: // get_server_info
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				org.jacorb.imr.ServerInfoHelper.write(_out,get_server_info(_arg0));
			}
			catch(org.jacorb.imr.UnknownServerName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex0);
			}
				break;
			}
			case 9: // edit_server
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				edit_server(_arg0,_arg1,_arg2);
			}
			catch(org.jacorb.imr.UnknownServerName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex0);
			}
				break;
			}
			case 10: // unregister_server
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				unregister_server(_arg0);
			}
			catch(org.jacorb.imr.UnknownServerName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex0);
			}
				break;
			}
			case 11: // register_server
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				register_server(_arg0,_arg1,_arg2);
			}
			catch(org.jacorb.imr.AdminPackage.IllegalServerName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.AdminPackage.IllegalServerNameHelper.write(_out, _ex0);
			}
			catch(org.jacorb.imr.AdminPackage.DuplicateServerName _ex1)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.AdminPackage.DuplicateServerNameHelper.write(_out, _ex1);
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
