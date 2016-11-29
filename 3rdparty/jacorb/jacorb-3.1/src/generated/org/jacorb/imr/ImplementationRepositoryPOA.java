package org.jacorb.imr;


/**
 * Generated from IDL interface "ImplementationRepository".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public abstract class ImplementationRepositoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.jacorb.imr.ImplementationRepositoryOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "register_poa", Integer.valueOf(0));
		m_opsHash.put ( "release_server", Integer.valueOf(1));
		m_opsHash.put ( "list_hosts", Integer.valueOf(2));
		m_opsHash.put ( "unregister_host", Integer.valueOf(3));
		m_opsHash.put ( "start_server", Integer.valueOf(4));
		m_opsHash.put ( "list_servers", Integer.valueOf(5));
		m_opsHash.put ( "shutdown", Integer.valueOf(6));
		m_opsHash.put ( "save_server_table", Integer.valueOf(7));
		m_opsHash.put ( "hold_server", Integer.valueOf(8));
		m_opsHash.put ( "get_imr_info", Integer.valueOf(9));
		m_opsHash.put ( "get_server_info", Integer.valueOf(10));
		m_opsHash.put ( "register_server", Integer.valueOf(11));
		m_opsHash.put ( "unregister_server", Integer.valueOf(12));
		m_opsHash.put ( "edit_server", Integer.valueOf(13));
		m_opsHash.put ( "register_host", Integer.valueOf(14));
		m_opsHash.put ( "set_server_down", Integer.valueOf(15));
	}
	private String[] ids = {"IDL:org/jacorb/imr/ImplementationRepository:1.0","IDL:org/jacorb/imr/Registration:1.0","IDL:org/jacorb/imr/Admin:1.0"};
	public org.jacorb.imr.ImplementationRepository _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.imr.ImplementationRepository __r = org.jacorb.imr.ImplementationRepositoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.imr.ImplementationRepository _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.imr.ImplementationRepository __r = org.jacorb.imr.ImplementationRepositoryHelper.narrow(__o);
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
			case 0: // register_poa
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				int _arg3=_input.read_ulong();
				_out = handler.createReply();
				register_poa(_arg0,_arg1,_arg2,_arg3);
			}
			catch(org.jacorb.imr.RegistrationPackage.IllegalPOAName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.RegistrationPackage.IllegalPOANameHelper.write(_out, _ex0);
			}
			catch(org.jacorb.imr.RegistrationPackage.DuplicatePOAName _ex1)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.RegistrationPackage.DuplicatePOANameHelper.write(_out, _ex1);
			}
			catch(org.jacorb.imr.UnknownServerName _ex2)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex2);
			}
				break;
			}
			case 1: // release_server
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
			case 2: // list_hosts
			{
				_out = handler.createReply();
				org.jacorb.imr.HostInfoSeqHelper.write(_out,list_hosts());
				break;
			}
			case 3: // unregister_host
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
			case 4: // start_server
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
			case 5: // list_servers
			{
				_out = handler.createReply();
				org.jacorb.imr.ServerInfoSeqHelper.write(_out,list_servers());
				break;
			}
			case 6: // shutdown
			{
				boolean _arg0=_input.read_boolean();
				_out = handler.createReply();
				shutdown(_arg0);
				break;
			}
			case 7: // save_server_table
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
			case 8: // hold_server
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
			case 9: // get_imr_info
			{
				_out = handler.createReply();
				org.jacorb.imr.ImRInfoHelper.write(_out,get_imr_info());
				break;
			}
			case 10: // get_server_info
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
			case 12: // unregister_server
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
			case 13: // edit_server
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
			case 14: // register_host
			{
			try
			{
				org.jacorb.imr.HostInfo _arg0=org.jacorb.imr.HostInfoHelper.read(_input);
				_out = handler.createReply();
				register_host(_arg0);
			}
			catch(org.jacorb.imr.RegistrationPackage.InvalidSSDRef _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.RegistrationPackage.InvalidSSDRefHelper.write(_out, _ex0);
			}
			catch(org.jacorb.imr.RegistrationPackage.IllegalHostName _ex1)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.RegistrationPackage.IllegalHostNameHelper.write(_out, _ex1);
			}
				break;
			}
			case 15: // set_server_down
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				set_server_down(_arg0);
			}
			catch(org.jacorb.imr.UnknownServerName _ex0)
			{
				_out = handler.createExceptionReply();
				org.jacorb.imr.UnknownServerNameHelper.write(_out, _ex0);
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
