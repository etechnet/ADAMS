package org.jacorb.imr;


/**
 * Generated from IDL interface "ServerStartupDaemon".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public abstract class ServerStartupDaemonPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.jacorb.imr.ServerStartupDaemonOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_system_load", Integer.valueOf(0));
		m_opsHash.put ( "start_server", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:org/jacorb/imr/ServerStartupDaemon:1.0"};
	public org.jacorb.imr.ServerStartupDaemon _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.imr.ServerStartupDaemon __r = org.jacorb.imr.ServerStartupDaemonHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.imr.ServerStartupDaemon _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.imr.ServerStartupDaemon __r = org.jacorb.imr.ServerStartupDaemonHelper.narrow(__o);
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
			case 0: // get_system_load
			{
				_out = handler.createReply();
				_out.write_long(get_system_load());
				break;
			}
			case 1: // start_server
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
