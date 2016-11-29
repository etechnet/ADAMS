package org.jacorb.imr;


/**
 * Generated from IDL interface "Registration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public abstract class RegistrationPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.jacorb.imr.RegistrationOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_imr_info", Integer.valueOf(0));
		m_opsHash.put ( "register_poa", Integer.valueOf(1));
		m_opsHash.put ( "set_server_down", Integer.valueOf(2));
		m_opsHash.put ( "register_host", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:org/jacorb/imr/Registration:1.0"};
	public org.jacorb.imr.Registration _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.imr.Registration __r = org.jacorb.imr.RegistrationHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.imr.Registration _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.imr.Registration __r = org.jacorb.imr.RegistrationHelper.narrow(__o);
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
			case 0: // get_imr_info
			{
				_out = handler.createReply();
				org.jacorb.imr.ImRInfoHelper.write(_out,get_imr_info());
				break;
			}
			case 1: // register_poa
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
			case 2: // set_server_down
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
			case 3: // register_host
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
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
