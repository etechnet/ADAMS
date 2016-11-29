package org.omg.ATLAS;


/**
 * Generated from IDL interface "AuthTokenDispenser".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public abstract class AuthTokenDispenserPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.omg.ATLAS.AuthTokenDispenserOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "get_my_authorization_token", Integer.valueOf(0));
		m_opsHash.put ( "translate_authorization_token", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:omg.org/ATLAS/AuthTokenDispenser:1.0"};
	public org.omg.ATLAS.AuthTokenDispenser _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.ATLAS.AuthTokenDispenser __r = org.omg.ATLAS.AuthTokenDispenserHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.ATLAS.AuthTokenDispenser _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.ATLAS.AuthTokenDispenser __r = org.omg.ATLAS.AuthTokenDispenserHelper.narrow(__o);
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
			case 0: // get_my_authorization_token
			{
			try
			{
				_out = handler.createReply();
				org.omg.ATLAS.AuthTokenDataHelper.write(_out,get_my_authorization_token());
			}
			catch(org.omg.ATLAS.IllegalTokenRequest _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.ATLAS.IllegalTokenRequestHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // translate_authorization_token
			{
			try
			{
				org.omg.CSI.IdentityToken _arg0=org.omg.CSI.IdentityTokenHelper.read(_input);
				org.omg.CSI.AuthorizationElement[] _arg1=org.omg.CSI.AuthorizationTokenHelper.read(_input);
				_out = handler.createReply();
				org.omg.ATLAS.AuthTokenDataHelper.write(_out,translate_authorization_token(_arg0,_arg1));
			}
			catch(org.omg.ATLAS.IllegalTokenRequest _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.ATLAS.IllegalTokenRequestHelper.write(_out, _ex0);
			}
			catch(org.omg.ATLAS.TokenOkay _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.ATLAS.TokenOkayHelper.write(_out, _ex1);
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
